package com.gymos.web.service.impl;

import com.gymos.web.dto.EventDto;
import com.gymos.web.models.Club;
import com.gymos.web.models.Event;
import com.gymos.web.repository.ClubRepository;
import com.gymos.web.repository.EventRepository;
import com.gymos.web.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;
    private ClubRepository clubRepository;


    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    private Event mapToEvent(EventDto eventDto){
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
    }
}
