package com.saurabhrana.smartjobsniffer.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "job_postings")
public class JobPostingDTO {


    @Id
    @Column(length = 50)
    private String id;
    private String offset;
    private Integer limit;

    private Integer totalFound;

    @Embedded
    private Content[] content;

}
@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Content {
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
}

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Company {
    private String identifier;
//    private String name;
    private String logo;
}

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Location {

    private String city;
    private String region;
    private String country;
    private Boolean remote;

}