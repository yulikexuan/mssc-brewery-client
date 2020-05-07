//: guru.sfg.msscbeer.client.web.client.CustomerClient.java


package guru.sfg.msscbeer.client.web.client;


import guru.sfg.msscbeer.client.config.ConfigProperties;
import guru.sfg.msscbeer.client.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CustomerClient(RestTemplateBuilder restTemplateBuilder,
                          ConfigProperties configProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiHost = configProperties.getApiHost();
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

    private String getCustomerByIdUrl(UUID id) {
        return String.format("%s%s/%s", this.apiHost, CUSTOMER_PATH_V1,
                id.toString());
    }

    private String getSaveCustomerUri() {
        return this.apiHost + CUSTOMER_PATH_V1;
    }

}///:~