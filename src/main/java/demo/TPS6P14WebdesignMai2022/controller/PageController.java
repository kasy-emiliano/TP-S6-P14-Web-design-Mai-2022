/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.TPS6P14WebdesignMai2022.controller;

import demo.TPS6P14WebdesignMai2022.model.Econnect;
import demo.TPS6P14WebdesignMai2022.model.Information;
import com.example.demo.model.*;
import demo.TPS6P14WebdesignMai2022.generic.GenericDAO;
import demo.TPS6P14WebdesignMai2022.model.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cedrick
 */
@Controller

public class PageController {

      @RequestMapping("/index")
    public String index()
    {
        return "index";
    }
    
     @RequestMapping("/details")
    public String det(@RequestParam("param") String param)
    {
        return "details";
    }
   
    
      @RequestMapping("/pageInsertion-des-nouveaux-information-sur-les-IA")
    public String pageInsertion()
    {
        return "insertion";
    }
    
    @RequestMapping("/login-Information-sur-les-IA")
    public String auth()
    {
        return "login";
    }
    
   @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request,HttpSession session, HttpServletResponse response) throws IOException, Exception {
        PrintWriter out = response.getWriter();
        String nom = request.getParameter("name");
        String mdp = request.getParameter("mdp");
        ArrayList<Admin> admin = GenericDAO.findBySql(new Admin(), "select * from admin where nom='" + nom + "' and mdp='" + mdp + "'", new Econnect().connexion());

        if (admin.isEmpty()) {
           return "login";
        } else {
         int idAdmin=admin.get(0).getId();
        System.out.println("aaaaaaa"+ idAdmin);
          session.setAttribute("idAdmin", idAdmin);
          return "redirect:/liste-de-tout-les-info-de-IA"; 
        }
    }
    
    @GetMapping("/deconnexion")
    public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
        session.invalidate();
    }
    return "redirect:/login-Information-sur-les-IA";
}
    
   
    @RequestMapping(value="/insertInfo")
    public String insertInfo(Model model,@RequestParam String titre,@RequestParam String body,@RequestParam(value="file",required=false) MultipartFile file )throws IOException, Exception
    {
        System.out.println("file = " + file.getOriginalFilename());
          
          byte[] bytes = file.getBytes();
          System.out.println("sdfgsdfg"+ bytes);
        String photo = Base64.getEncoder().encodeToString(bytes);
        GenericDAO.save(new Information(titre, body, photo), new Econnect().connexion());
        
  
  
      
         
        //model.addAttribute("name", titre);
      return "redirect:/liste-de-tout-les-info-de-IA";
    }
    
     @RequestMapping("/liste-de-tout-les-info-de-IA")
    public String liste(Model model,HttpSession session,
        HttpServletResponse response) throws IOException, Exception {
        PrintWriter out = response.getWriter();
        ArrayList<Information> listeInform = new ArrayList<>();
        listeInform = GenericDAO.findBySql(new Information(), "select*from information", new Econnect().connexion());
         for (Information information : listeInform) {
             information.setUrl();
         }
         int idAdmin = Integer.parseInt(session.getAttribute("idAdmin").toString());
          model.addAttribute("listeInform", listeInform);
          System.out.println("aaaaaaonaaa" +idAdmin );
          session.setAttribute("idAdmin", idAdmin);
       
        return "liste";
    }
      @RequestMapping("/liste-de-tout-les-info-de-IA-front")
    public String listefront(Model model,HttpSession session,
        HttpServletResponse response) throws IOException, Exception {
        PrintWriter out = response.getWriter();
        ArrayList<Information> listeInform = new ArrayList<>();
        listeInform = GenericDAO.findBySql(new Information(), "select*from information", new Econnect().connexion());
         for (Information information : listeInform) {
             information.setUrl();
         }
    
          model.addAttribute("listeInform", listeInform);
         
       
        return "listefront";
    }
    
     
  @GetMapping("/supprimer/{id}")
public String supprimer(@PathVariable("id") int id) throws Exception {
    Information det = new Information();
    det.delete(id);
    return "redirect:/liste-de-tout-les-info-de-IA";
}
    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModification(@PathVariable("id") int id, Model model) throws Exception {
    Information info = new Information().findById(id);
    model.addAttribute("info", info);
    return "update";
}
@RequestMapping("/update/{titre}-{id}")
    public String uptdate(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        PrintWriter out = response.getWriter();
        
        String titre=(request.getParameter("titre"));
        String body=(request.getParameter("body"));
        String photo=(request.getParameter("photo"));
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id aaaaa" + id);
       
        Information det = new Information();
        det.update(id,titre,body,photo);
        model.addAttribute("id", id);
        return "redirect:/liste-de-tout-les-info-de-IA";

    }
     @GetMapping("/details/{titre}-{id}")
    public String details(@PathVariable("id") int id, Model model,RedirectAttributes redirectAttributes) throws Exception {
    Information info = new Information().findById(id);
   
    model.addAttribute("info", info);
    
    return "details";

    }
}
    

