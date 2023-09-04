package com.saurabhrana.smartjobsniffer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInformation {
    @Column(name = "experience_title")
    private String title;
    @Column(name = "additionalInformation_text")
    private String text;
}
