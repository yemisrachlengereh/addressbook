package com.example.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ContactRepository contactRepository;
  

    @RequestMapping("/")
    public String listContact(Model model){
        model.addAttribute("contacts",contactRepository.findAll());
        return "addresslist";
    }

    @GetMapping("/add")
    public String course(Model model){
        model.addAttribute("contact",new Contact());
        return "contactform";

    }
    @PostMapping("/process")
    public String processForm(@Valid Contact contact, BindingResult result)
    {System.out.println(result.toString());
        if(result.hasFieldErrors()){
            return "contactform";
        }
        contactRepository.save(contact);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showContact(@PathVariable("id")long id, Model model){
        model.addAttribute("contact",contactRepository.findOne(id));
        return "Show";
    }


    @RequestMapping("/update/{id}")
    public String updateContact(@PathVariable("id")long id, Model model) {
        model.addAttribute("contact", contactRepository.findOne(id));
        return "contactform";
    }


    @RequestMapping("/delete/{id}")
    public String delContact(@PathVariable("id") long id) {
        contactRepository.delete(id);
        return "redirect:/";
    }




}
