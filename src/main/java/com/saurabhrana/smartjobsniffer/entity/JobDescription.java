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
public class JobDescription {
    @Column(name = "jobDescription_title")
    private String title;
    @Column(name = "JobDescription_text")
    private String text;
}
