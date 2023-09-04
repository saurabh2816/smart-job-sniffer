package com.saurabhrana.smartjobsniffer.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobAd {
    @Embedded
    private Sections sections;
}
