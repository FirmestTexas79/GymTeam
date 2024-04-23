package com.gymos.web.controller;

import com.gymos.web.dto.ClubDto;
import com.gymos.web.models.Club;
import com.gymos.web.models.UserEntity;
import com.gymos.web.security.SecurityUtil;
import com.gymos.web.service.ClubService;
import com.gymos.web.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ClubController {
    private ClubService clubService;
    private UserService userService;

    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }


    @GetMapping("/clubs")
    public String listClubs(Model model){
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if(username!= null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }



    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model){
        // Metoda detailKlubu() zpracovává GET požadavky na detail konkrétního klubu.
        // Parametr klubId je získán z URL cesty a používá se k identifikaci klubu.
        UserEntity user = new UserEntity();
        // Vytvoření nové instance UserEntity pro případ, že uživatel není přihlášen.
        ClubDto clubDto = clubService.findClubById(clubId);
        // Získání detailů klubu pomocí služby klubService na základě poskytnutého klubId.
        String username = SecurityUtil.getSessionUser();
        // Získání uživatelského jména aktuálně přihlášeného uživatele.
        if(username!= null){
            // Pokud je uživatel přihlášen, načtou se jeho informace z databáze.
            user = userService.findByUsername(username);
            // Načtení uživatelských dat pomocí uživatelského jména.
            model.addAttribute("user", user);
            // Přidání uživatelských dat do modelu pro předání do šablonového souboru.
        }
        model.addAttribute("user", user);
        // Přidání uživatelských dat do modelu pro předání do šablonového souboru.
        model.addAttribute("club", clubDto);
        // Přidání detailů klubu do modelu pro předání do šablonového souboru.
        return "clubs-detail";
    }


    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }



    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club")ClubDto clubDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("club", club);
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
}
