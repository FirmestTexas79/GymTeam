package com.gymos.web.service;

import com.gymos.web.dto.ClubDto;
import com.gymos.web.models.Club;

import java.util.List;

public interface ClubService {
    //Logika a provádění operace s daty

    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);
    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);

    void delete(Long clubId);
    List<ClubDto> searchClubs(String query);
}
