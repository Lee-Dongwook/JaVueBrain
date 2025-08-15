package com.example.backend.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BatchScheduler {
    private final JobLauncher jobLauncher;
    private final Job chatLogArchiveJob;
    
    @Scheduled(cron = "0 0 2 * * *")
    public void runArchiveJob() throws Exception {
        jobLauncher.run(chatLogArchiveJob,
                new JobParametersBuilder()
                    .addString("time", LocalDateTime.now().toString())
                        .toJobParameters()
        );
    }
}
