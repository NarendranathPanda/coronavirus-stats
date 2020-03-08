package io.n4b.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.beans.BeanProperty;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

@Service
public class CSVCollectorService {
    private static Logger logger = LoggerFactory.getLogger(CSVCollectorService.class);
    @Value("${csvUrl}")
    private String url;
    public static Date LAST_FETCH_TIME;

    private static String result = "None";

    public String fetchCsv() throws IOException, InterruptedException {
        logger.trace("Fetch CSV from Url : {} ", url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        result = response.body().replace('"', ' ');
        logger.trace("Http Client got a successful response from url:{}", url);
        logger.trace("File Content : \n {} \n", result);
        LAST_FETCH_TIME = new Date();
        return result;
    }


}
