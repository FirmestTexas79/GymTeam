package com.gymos.web.service;

import com.gymos.web.dto.EventDto;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
