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
public class Sections {
    @Embedded
    private JobDescription jobDescription;
    @Embedded
    private Qualifications qualifications;
    @Embedded
    private AdditionalInformation additionalInformation;
}
