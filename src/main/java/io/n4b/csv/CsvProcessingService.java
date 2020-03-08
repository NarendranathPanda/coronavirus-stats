package io.n4b.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvProcessingService {
    private static List<CsVObject> issues = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(CsvProcessingService.class);


    @Autowired
    CSVCollectorService collectorService;


    @PostConstruct
    public int processCsv() throws IOException, InterruptedException {
        logger.trace("process csv");
        Reader in = new StringReader(collectorService.fetchCsv());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        List<CsVObject> currentIssues = new ArrayList<>();
        try {
            for (CSVRecord record : records) {
                CsVObject issue = getCsVObject(record);
                currentIssues.add(issue);
            }
        } catch (Exception e) {
            logger.error("Invalid issue ", e);
        }
        issues = currentIssues;
        return currentIssues.size();
    }

    private CsVObject getCsVObject(CSVRecord record) {
        CsVObject csv = new CsVObject();


        if (record.size() >= 3) {
            String province = "".equals(record.get(0)) ? "NA" : record.get(0);
            String country = "".equals(record.get(1)) ? "NA" : record.get(1);

            csv.setColumnOne("NA".equals(province) ? country : province);
            csv.setColumnTwo(country);
            // Corona Virus
            try {
                int noOfReportedLast = Integer.parseInt(record.get(record.size() - 1));
                int noOfReportedEarlier = Integer.parseInt(record.get(record.size() - 2));
                csv.setColumnThree(noOfReportedLast);
                csv.setColumnFour(noOfReportedLast - noOfReportedEarlier);
            } catch (Exception e) {
                logger.error("Record No : " + record.getRecordNumber(), e);
            }
        }
        logger.trace(csv.toString());
        return csv;
    }

    public List<CsVObject> getIssueList() {
        return issues;
    }
}
