package com.saurabhrana.smartjobsniffer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "Jobs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "localId", nullable = false)
    private Long localId;

    @Column(name="id")
    private String id;

    @Column(name = "job_name")
    private String name;

    @Column(name = "uuid")
    private String uuid;

    @Embedded
    private Company company;

    @Embedded
    private Location location;

    @Column(name = "released_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releasedDate;

    @Column(name = "posting_url")
    private String postingUrl;

    @Embedded
    public JobAd jobAd;

    @Column(name = "active")
    private Boolean active;

    @Embedded
    private ExperienceLevel experienceLevel;

    @Embedded
    private TypeOfEmployment typeOfEmployment;

    @Embedded
    private Language language;
}

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Company {
    private String identifier;
    @Column(name = "company_name")

    private String name;
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
    private String address;
    private String postalCode;
    private Boolean remote;
}

