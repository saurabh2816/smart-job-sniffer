package com.saurabhrana.smartjobsniffer.DTO;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String city;
    private String region;
    private String country;
    private Boolean remote;

}
