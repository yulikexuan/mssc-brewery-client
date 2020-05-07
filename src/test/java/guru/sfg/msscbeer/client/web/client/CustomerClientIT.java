//: guru.sfg.msscbeer.client.web.client.CustomerClientIT.java


package guru.sfg.msscbeer.client.web.client;


import guru.sfg.msscbeer.client.web.model.CustomerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DisplayName("Customer Client Test - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerClientIT {

    @Autowired
    private CustomerClient customerClient;

    @Test
    void test_Given_Id_When_Calling_Get_Customer_Then_Have_Customer_Back() {

        // Given
        UUID id = UUID.randomUUID();

        // When
        CustomerDto customer = this.customerClient.getCustomerById(id);

        // Then
        assertThat(customer.getId()).isInstanceOf(UUID.class);
    }

    @Test
    void test_Given_CustomerDto_When_Saving_New_Customer_Then_Have_Location_Back() {

        // Given
        CustomerDto customer = CustomerDto.builder().id(UUID.randomUUID())
                .name("MilesGuo").build();

        // When
        URI location = this.customerClient.saveNewCustomer(customer);

        // Then
        assertThat(location.toString()).startsWith(CustomerClient.CUSTOMER_PATH_V1);
    }

}///:~