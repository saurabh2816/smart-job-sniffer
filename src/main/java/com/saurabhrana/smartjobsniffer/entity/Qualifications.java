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
public class Qualifications {
    @Column(name = "qualification_title")

    private String title;
    @Column(name = "qualification_text")

    private String text;
}
