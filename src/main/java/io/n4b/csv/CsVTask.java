package io.n4b.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class CsVTask {

    private static Logger logger = LoggerFactory.getLogger(CSVCollectorService.class);

    @Autowired
    CsvProcessingService service;

    @Scheduled(cron = "30 * * * * *")
    public void processGitIssue() throws IOException, InterruptedException {
        int noOfRecord = service.processCsv();
        logger.info("No of Records: " + noOfRecord);
    }
}
