package com.saurabhrana.smartjobsniffer.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @Column(length = 50)
    private String id;
    private String name;
    @Embedded
    private Company company;
    @Temporal(TemporalType.TIMESTAMP)
    private Date releasedDate;
    @Embedded
    private Location location;
    @Embedded
    public Actions actions;

    private String applyUrl;
    private String shortLocation;
    private String releasedAgo;

}
