package com.gymos.web.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    // Metoda pro získání aktuálně přihlášeného uživatele
    public static String getSessionUser(){
        // Získání aktuální autentizace z kontextu zabezpečení
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Kontrola, zda uživatel není anonymní
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            // Pokud není anonymní, získání uživatelského jména
            String currentUsername = authentication.getName();
            return currentUsername;
        }
        return null;
    }

}
