//: guru.sfg.msscbeer.client.web.client.BreweryClientIT.java


package guru.sfg.msscbeer.client.web.client;


import guru.sfg.msscbeer.client.web.model.BeerDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("CompletableFuture Test - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
class BreweryClientIT {

    @Autowired
    private BreweryClient breweryClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void test_Given_Beer_Id_When_Call_Beer_Api_Then_Get_BeerDto_Back() {

        // Given
        UUID id = UUID.randomUUID();

        // When
        BeerDto beer = this.breweryClient.getBeerById(id);

        // Then
        assertThat(beer).isNotNull();
    }

    @Test
    void test_Give_BeerDto_When_Save_Then_Get_URI_Back() {

        // Given
        BeerDto newBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Qingdao")
                .build();

        // When
        URI uri = this.breweryClient.saveNewBeer(newBeer);

        // Then
        assertThat(uri.toString()).startsWith(BreweryClient.BEER_PATH_V1);
    }

    @Test
    void test_Give_UUID_And_BeerDto_When_Update() {

        // Given
        BeerDto existingBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Qingdao")
                .build();

        // When
        this.breweryClient.updateBeer(existingBeer.getId(), existingBeer);
    }

}///:~