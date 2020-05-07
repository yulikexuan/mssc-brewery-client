//: guru.sfg.msscbeer.client.web.client.BreweryClient.java


package guru.sfg.msscbeer.client.web.client;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private static final String BEER_PATH_V1 = "/api/v1/beer";

    private String apiHost;

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

}///:~