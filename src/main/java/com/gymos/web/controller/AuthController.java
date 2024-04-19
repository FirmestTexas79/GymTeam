package com.gymos.web.controller;

import com.gymos.web.dto.RegistrationDto;
import com.gymos.web.dto.UserEntityDto;
import com.gymos.web.models.UserEntity;
import com.gymos.web.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        // Zde získáme přihlášeného uživatele a ověříme, zda má roli ADMIN
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            List<UserEntityDto> userEntities = userService.findAllUserEntities();
            model.addAttribute("userEntities", userEntities);

            return "admin"; // Pokud uživatel má roli ADMIN, může přistoupit na stránku /admin
        } else {
            return "redirect:/admin-kontrola"; // Pokud uživatel nemá roli ADMIN, přesměrujeme ho na jinou stránku
        }
    }

    @GetMapping("/admin-kontrola")
    public String adminKontrola() {
        return "admin-kontrola"; // Vrátí název šablony HTML pro domovskou stránku
    }

    @GetMapping("/info")
    public String info() {
        return "info"; // Vrátí název šablony HTML pro domovskou stránku
    }



    @GetMapping("/userEntity/{userId}/delete")
    public String deleteUserEntity(@PathVariable("userId") Long userId){
        userService.delete(userId);
        return "redirect:/index";
    }



    @GetMapping("/login")
    public String loginPage(){
        return"login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result, Model model){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }
}
