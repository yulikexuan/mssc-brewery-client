//: guru.sfg.msscbeer.client.web.model.BeerDto.java


package guru.sfg.msscbeer.client.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
public class BeerDto {

    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;

}///:~