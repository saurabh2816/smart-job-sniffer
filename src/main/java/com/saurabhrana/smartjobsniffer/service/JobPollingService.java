package com.saurabhrana.smartjobsniffer.service;

import com.saurabhrana.smartjobsniffer.DTO.Actions;
import com.saurabhrana.smartjobsniffer.DTO.Content;
import com.saurabhrana.smartjobsniffer.DTO.JobPostingDTO;
import com.saurabhrana.smartjobsniffer.entity.Jobs;
import com.saurabhrana.smartjobsniffer.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class JobPollingService {

    private List<JobPostingDTO> resultsList = new CopyOnWriteArrayList<>();
    private final List<Jobs> jobsList = new CopyOnWriteArrayList<>();
    private final Set<String> actionUrls = new HashSet<>();

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    SlackService slackService;

    public JobPollingService(JobsRepository jobsRepository, SlackService slackService) {
        this.jobsRepository = jobsRepository;
        this.slackService = slackService;
    }

    // once every 2 hours
    @Scheduled(fixedRate = 7200000)
    public void pollApi() {
        WebClient webClient = WebClient.create();
        resultsList = new CopyOnWriteArrayList<>();

        String url = "https://jobs.smartrecruiters.com/sr-jobs/search?limit=100&keyword=software%20engineer";
        // Add result to list
        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JobPostingDTO.class)
                .doOnNext(resultsList::add)
                .doOnTerminate(this::extractActionsUrlForUnitedStates)  // Call after WebClient operation is done
                .subscribe();
    }

    private void saveJobPostings(List<String> actionUrls) {

        List<Mono<Jobs>> monos = new ArrayList<>();

        // iterate action URLs and save them to Job table
        WebClient webClient = WebClient.create();
        for(String url: actionUrls) {
            Mono<Jobs> mono = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Jobs.class)
                    .doOnNext(jobsList::add);
            monos.add(mono);

        }

        Mono.when(monos)
                .doOnTerminate(this::checkJobList)
                .subscribe();


    }

    private void checkJobList() {
        var javaJobs = jobsList.stream().filter(job -> job.getJobAd().getSections().getJobDescription().getText().contains("Java")).collect(Collectors.toList());
//        var texts = jobsList.stream().map(job -> job.getJobAd().getSections().getQualifications().getText()).collect(Collectors.toList());
        for(var job: jobsList) {
            System.out.println("job name: " + job.getName());
            System.out.println("job smart recruiter ID: " + job.getSmartRecruiterId());
//            System.out.println("qualification: " + job.getJobAd().getSections().getQualifications().getText());

            if( job.getJobAd().getSections().getQualifications().getText().contains("Java") || job.getJobAd().getSections().getJobDescription().getText().contains("Java")) {
                if(!(jobsRepository.findBySmartRecruiterId(job.getSmartRecruiterId()).isPresent())) {
                    job.setNotified(false);
                    jobsRepository.save(job);
                }
            }
        }

        // slackService.sendMessage("Hi Handsome! I'm thinking of you. Who am I? I am your Job Fetcher Service <3");

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

        saveJobPostings(actionUrls.stream().toList());
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
