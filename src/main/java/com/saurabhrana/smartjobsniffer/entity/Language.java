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
public class Language {
    private String code;
    @Column(name = "language_label")

    private String label;
    private String labelNative;
}
