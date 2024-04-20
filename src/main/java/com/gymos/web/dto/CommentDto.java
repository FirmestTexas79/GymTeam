package com.gymos.web.dto;


import com.gymos.web.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {

    private Long id;
    @NotEmpty(message = "Nesmí být prázné")
    private String comment;
    private LocalDateTime createdOn;

    private UserEntity commentedBy;
}
