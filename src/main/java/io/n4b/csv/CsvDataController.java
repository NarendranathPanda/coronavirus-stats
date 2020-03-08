
package io.n4b.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
public class CsvDataController {

    private static Logger logger = LoggerFactory.getLogger(CsvDataController.class);

    @Value("${columnOne}")
    private String columnOne;

    @Value("${columnTwo}")
    private String columnTwo;

    @Value("${columnThree}")
    private String columnThree;

    @Value("${columnFour}")
    private String columnFour;

    @Value("${title}")
    private String title;

    @Value("${description}")
    private String description;

    @Value("${csvUrl}")
    private Object url;

    @Autowired
    CsvProcessingService service;



    @GetMapping(path = "/")
    public String show(Model model) throws IOException {
        logger.trace("Show Page called");
        List<CsVObject> csVObjects = service.getIssueList();
        int totalNoOfCases=csVObjects.stream().mapToInt(csv -> csv.getColumnThree()).sum();
        int totalNoOfIncreasedCases=csVObjects.stream().mapToInt(csv -> csv.getColumnFour()).sum();
        Collections.sort(csVObjects,Collections.reverseOrder());
        model.addAttribute("csvObjects", csVObjects);
        model.addAttribute("noOfcsvObjects", totalNoOfCases);
        model.addAttribute("totalNoOfIncreasedCases", totalNoOfIncreasedCases);
        model.addAttribute("url", url);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("columnOne", columnOne);
        model.addAttribute("columnTwo", columnTwo);
        model.addAttribute("columnThree", columnThree);
        model.addAttribute("columnFour", columnFour);
        model.addAttribute("lastFetchTime", CSVCollectorService.LAST_FETCH_TIME);

        logger.trace("Show Page returned");
        return "home";
    }
}
