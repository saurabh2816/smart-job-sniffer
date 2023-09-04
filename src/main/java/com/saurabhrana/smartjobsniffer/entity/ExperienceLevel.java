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
public class ExperienceLevel {
    @Column(name = "experience_id")
    private String id;
    @Column(name = "experience_label")

    private String label;
}
