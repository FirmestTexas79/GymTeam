package com.gymos.web.service;

import com.gymos.web.dto.EventDto;

import java.util.List;

public interface EventService {
    //Logika a provádění operace s daty

    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void updateEvent(EventDto eventDto);

    void deleteEvent(long eventId);
}
