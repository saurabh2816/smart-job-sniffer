package com.saurabhrana.smartjobsniffer.DTO;

import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class JobPostingDTO {

    @Id
    @Column(length = 50)
    private String id;
    private String offset;
    private Integer limit;

    private Integer totalFound;

    @Embedded
    public Content[] content;

}

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Company {
    private String identifier;
    private String name;
    private String logo;
}

