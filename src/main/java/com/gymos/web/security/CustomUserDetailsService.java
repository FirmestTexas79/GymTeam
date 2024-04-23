package com.gymos.web.security;

import com.gymos.web.models.Role;
import com.gymos.web.models.UserEntity;
import com.gymos.web.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Metoda pro načtení uživatele podle uživatelského jména
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Hledání uživatele v databázi podle uživatelského jména
        UserEntity user = userRepository.findFirstByUsername(username);
        // Pokud je uživatel nalezen
        if(user != null){
            // Vytvoření objektu UserDetails pro autentizaci
            User authUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles())); // Přiřazení rolí uživatele k oprávněním
            return authUser;
        }else {
            // Pokud uživatel není nalezen, vyhoď výjimku UsernameNotFoundException
            throw new UsernameNotFoundException("Neplatné jméno nebo heslo");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        // Procházení rolí uživatele a vytvoření seznamu oprávnění
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
