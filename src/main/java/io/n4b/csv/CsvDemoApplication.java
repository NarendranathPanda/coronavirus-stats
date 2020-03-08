package io.n4b.csv;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class CsvDemoApplication {

    private static Logger logger = LoggerFactory.getLogger(CsvDemoApplication.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CsvDemoApplication.class, args);
        logger.info("Application Started...");
    }

}
