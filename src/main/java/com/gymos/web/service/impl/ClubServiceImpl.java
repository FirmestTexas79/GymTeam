package com.gymos.web.service.impl;

import com.gymos.web.dto.ClubDto;
import com.gymos.web.models.Club;
import com.gymos.web.models.UserEntity;
import com.gymos.web.repository.ClubRepository;
import com.gymos.web.repository.UserRepository;
import com.gymos.web.security.SecurityUtil;
import com.gymos.web.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.gymos.web.mapper.ClubMapper.mapToClub;
import static com.gymos.web.mapper.ClubMapper.mapToClubDto;


@Service
public class ClubServiceImpl implements ClubService {

    //Konkrétní implementace toho z Service
    private ClubRepository clubRepository;
    private UserRepository userRepository;

    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    public List<ClubDto> findAllClubs(){
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

}
