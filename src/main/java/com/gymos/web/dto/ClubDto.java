package com.gymos.web.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;


@Data
@Builder
public class ClubDto {

    private Long id;
    @NotEmpty(message = "Název klubu nesmí být prázdný")
    private String title;
    @NotEmpty(message = "Obrázek klubu nesmí být prázdný")
    private String photoUrl;
    @NotEmpty(message = "Popis klubu nesmí být prázdný")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
