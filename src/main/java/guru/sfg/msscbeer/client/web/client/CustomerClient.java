//: guru.sfg.msscbeer.client.web.client.CustomerClient.java


package guru.sfg.msscbeer.client.web.client;


import guru.sfg.msscbeer.client.config.HttpClientLoggerProperties;
import guru.sfg.msscbeer.client.config.SfgBreweryProperties;
import guru.sfg.msscbeer.client.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;


@Component
public class CustomerClient {

    static final String CUSTOMER_PATH_V1 = "/api/v1/customer";

    private final RestTemplate restTemplate;

    private String apiHost;
    private String logHttp;

    @Value("${test.prop}")
    private String testProp;

    @Autowired
    public CustomerClient(RestTemplateBuilder restTemplateBuilder,
                          SfgBreweryProperties sfgBreweryProperties,
                          HttpClientLoggerProperties serverProperties) {

        this.restTemplate = restTemplateBuilder.build();
        this.apiHost = sfgBreweryProperties.getApiHost();
        this.logHttp = serverProperties.getHttp();
    }

    public CustomerDto getCustomerById(UUID id) {
        return this.restTemplate.getForObject(getCustomerByIdUrl(id),
                CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customer) {
        return this.restTemplate.postForLocation(this.getSaveCustomerUri(),
                customer);
    }

    public void updateCustomer(UUID id, CustomerDto customer) {
        this.restTemplate.put(this.getCustomerByIdUrl(id), customer);
    }

    public void deleteCustomer(UUID id) {
        this.restTemplate.delete(id.toString());
    }

    String getCustomerByIdUrl(UUID id) {
        return String.format("%s%s/%s", this.apiHost, CUSTOMER_PATH_V1,
                id.toString());
    }

    String getSaveCustomerUri() {
        return this.apiHost + CUSTOMER_PATH_V1;
    }

    String getLogHttp() {
        return this.logHttp;
    }

    String getTestProp() {
        return this.testProp;
    }

}///:~