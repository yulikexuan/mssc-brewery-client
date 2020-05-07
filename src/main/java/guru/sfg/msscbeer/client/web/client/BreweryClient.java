//: guru.sfg.msscbeer.client.web.client.BreweryClient.java


package guru.sfg.msscbeer.client.web.client;


import guru.sfg.msscbeer.client.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;


@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    static final String BEER_PATH_V1 = "/api/v1/beer";

    private final RestTemplate restTemplate;

    private String apiHost;

    @Autowired
    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id) {
        return this.restTemplate.getForObject(getBeerByIdUrl(id.toString()),
                BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beer) {
        return this.restTemplate.postForLocation(getSaveBeerUrl(), beer);
    }

    public void updateBeer(UUID id, BeerDto beer) {
        this.restTemplate.put(getBeerByIdUrl(id.toString()), beer);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    private String getBeerByIdUrl(String id) {
        return String.format("%s%s/%s", this.apiHost, BEER_PATH_V1, id);
    }

    private String getSaveBeerUrl() {
        return this.apiHost + BEER_PATH_V1;
    }

}///:~