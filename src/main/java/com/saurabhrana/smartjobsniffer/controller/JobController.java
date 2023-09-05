package com.saurabhrana.smartjobsniffer.controller;

import com.saurabhrana.smartjobsniffer.service.JobPollingService;
import com.saurabhrana.smartjobsniffer.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JobController {

    private final JobPollingService jobPollingService;
    private final SlackService slackService;

    @Autowired
    public JobController(JobPollingService jobPollingService, SlackService slackService) {
        this.jobPollingService = jobPollingService;
        this.slackService = slackService;
    }

    @GetMapping("/jobfetcher")
    public ResponseEntity<String> fetchJobs() {
        jobPollingService.pollApi();
        return ResponseEntity.ok("Job fetching initiated.");
    }
    @GetMapping("/slack")
    public ResponseEntity<String> slackNotification() {
        slackService.sendDailyJobUpdates();
        return ResponseEntity.ok("Slack notification sent!!!");
    }
}
