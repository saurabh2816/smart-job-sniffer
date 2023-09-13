package com.saurabhrana.smartjobsniffer.service;

import com.saurabhrana.smartjobsniffer.entity.Jobs;
import com.saurabhrana.smartjobsniffer.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SlackService {

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    private final RestTemplate restTemplate;

    private final JobsRepository jobsRepository;

    public SlackService(RestTemplateBuilder restTemplateBuilder, JobsRepository jobsRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.jobsRepository = jobsRepository;
    }

    // 4 hr = 14400000
    // 1 hr =  3600000
    // 4.2 hrs = 15400000
    @Scheduled(fixedRate = 15400000)
    public void sendDailyJobUpdates() {
        // Fetch jobs that haven't been notified yet
        List<Jobs> jobsToNotify = jobsRepository.findByNotifiedFalse();

        // Convert these jobs to a message format Slack understands
        String slackMessage = formatJobsToSlackMessage(jobsToNotify);

        // Send the message to Slack (implement your Slack API/Webhook call here)
        if(!slackMessage.isEmpty()) {
            sendMessage(slackMessage);
        }

        // Mark these jobs as notified
        jobsToNotify.forEach(job -> {
            job.setNotified(true);
            jobsRepository.save(job);
        });
    }

    private String formatJobsToSlackMessage(List<Jobs> jobsToNotify) {

        String message = "";
        for(var job: jobsToNotify) {
            message += "*" + job.getName() + "*" + "\n" + job.getCompany().getName() + " - " + job.getLocation().getCity() + "\n"  + "<" +job.getPostingUrl()+ "| Link>" + "\n\n";
        }

        return message;
    }
    public void sendMessage(String message) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> payload = Collections.singletonMap("text", message);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

            restTemplate.postForEntity(slackWebhookUrl, request, String.class);
        } catch (Exception e) {
            // Log error or take appropriate actions
            e.printStackTrace();
        }
    }
}
