package com.saurabhrana.smartjobsniffer.DTO;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actions {
    private String details;
}
