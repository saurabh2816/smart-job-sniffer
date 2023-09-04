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
public class TypeOfEmployment {
    @Column(name = "typeOfEmployment_id")
    private String id;
    @Column(name = "typeOfEmployment_label")

    private String label;
}
