package com.saurabhrana.smartjobsniffer.service;

import com.saurabhrana.smartjobsniffer.DTO.JobPostingDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JobPollingService {
//    @Autowired
//    private WebClient webClient;

//    @Scheduled(fixedRate = 5000) // Poll every 5 seconds
    public void pollApi() {
        WebClient webClient = WebClient.create();

        String url = "https://jobs.smartrecruiters.com/sr-jobs/search?limit=100&keyword=software%20engineer";
        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JobPostingDTO.class)
                .subscribe(result -> {
                    // Send result to Slack
//                    sendToSlack(Objects.requireNonNull(result));
                    System.out.println(result);
                });


        // Send result to Slack
//        sendToSlack(result);
    }

//    private void sendToSlack(String message) {
//        String slackUrl = "https://slack.com/api/chat.postMessage";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer YOUR_SLACK_BOT_TOKEN");
//        HttpEntity<String> request = new HttpEntity<>(headers);
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("channel", "CHANNEL_ID");
//        params.put("text", message);
//
//        restTemplate.postForObject(slackUrl, request, String.class, params);
//    }

}
