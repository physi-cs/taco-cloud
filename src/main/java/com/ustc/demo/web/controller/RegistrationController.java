package com.ustc.demo.web.controller;

import com.ustc.demo.domain.RegistrationForm;
import com.ustc.demo.domain.User;
import com.ustc.demo.persitence.JPARepo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration( RegistrationForm form) {
        User user = form.toUser(passwordEncoder);
        userRepo.save(user);
        return "redirect:/login";
    }

    //@ModelAttribute("registrationForm")
    //public RegistrationForm RegistrationForm(){
    //    return new RegistrationForm();
    //}

}
