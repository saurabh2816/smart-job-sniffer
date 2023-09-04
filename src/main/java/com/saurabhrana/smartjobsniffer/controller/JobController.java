package com.saurabhrana.smartjobsniffer.controller;

import com.saurabhrana.smartjobsniffer.service.JobPollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JobController {

    private final JobPollingService jobPollingService;

    @Autowired
    public JobController(JobPollingService jobPollingService) {
        this.jobPollingService = jobPollingService;
    }

    @GetMapping("/jobfetcher")
    public ResponseEntity<String> fetchJobs() {
        jobPollingService.pollApi();
        return ResponseEntity.ok("Job fetching initiated.");
    }
}
