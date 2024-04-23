package com.gymos.web;

import com.gymos.web.controller.ClubController;
import com.gymos.web.dto.ClubDto;
import com.gymos.web.models.UserEntity;
import com.gymos.web.service.ClubService;
import com.gymos.web.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClubControllerTest {

    @Mock
    private ClubService clubService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private ClubController clubController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Vytvoření mocku pro Authentication a nastavení jeho chování
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(null); // nebo jakoukoli jinou hodnotu, kterou očekáváte
    }

    @Test
    public void testListClubs() {
        List<ClubDto> clubs = new ArrayList<>();
        when(clubService.findAllClubs()).thenReturn(clubs);

        String result = clubController.listClubs(model);

        assertEquals("clubs-list", result);
        verify(clubService, times(1)).findAllClubs();
        verify(model, times(1)).addAttribute(eq("clubs"), anyList());
    }

    // Podobně můžete testovat i ostatní metody ve třídě ClubController
}
