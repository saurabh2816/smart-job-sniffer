package com.saurabhrana.smartjobsniffer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localId", nullable = false)
    private Long localId;

    @Column(name="notified")
    private Boolean notified;

    @JsonProperty(value="id")
    private String smartRecruiterId;

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

