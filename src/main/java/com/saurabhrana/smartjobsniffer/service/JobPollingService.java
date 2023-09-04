package com.saurabhrana.smartjobsniffer.service;

import com.saurabhrana.smartjobsniffer.DTO.Actions;
import com.saurabhrana.smartjobsniffer.DTO.Content;
import com.saurabhrana.smartjobsniffer.DTO.JobPostingDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class JobPollingService {
    private final List<JobPostingDTO> resultsList = new CopyOnWriteArrayList<>();
    private final List<String> actionUrls = new ArrayList<>();

//    @Scheduled(fixedRate = 5000) // Poll every 5 seconds

    public void pollApi() {
        WebClient webClient = WebClient.create();

        String url = "https://jobs.smartrecruiters.com/sr-jobs/search?limit=100&keyword=software%20engineer";
        // Add result to list
        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JobPostingDTO.class)
                .doOnNext(resultsList::add)
                .doOnTerminate(this::extractActionsUrlForUnitedStates)  // Call after WebClient operation is done
                .subscribe();

        // iterate action URLs and

    }

    public void extractActionsUrlForUnitedStates() {

        for (JobPostingDTO jobPosting : resultsList) {
            if (jobPosting.getContent() != null) {
                for (Content content : jobPosting.getContent()) {
                    if("us".equals(content.getLocation().getCountry())) {
                        Actions actions = content.getActions();
                        if (actions != null) {
                            actionUrls.add(actions.getDetails());
                        }
                    }
                }
            }
        }
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