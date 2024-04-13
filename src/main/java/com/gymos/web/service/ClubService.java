package com.gymos.web.service;

import com.gymos.web.dto.ClubDto;
import com.gymos.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(Club club);
    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);
}
