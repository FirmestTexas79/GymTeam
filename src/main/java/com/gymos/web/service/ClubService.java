package com.gymos.web.service;

import com.gymos.web.dto.ClubDto;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
}
