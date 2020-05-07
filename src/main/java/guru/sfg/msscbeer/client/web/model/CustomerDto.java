//: guru.sfg.msscbeer.client.web.model.CustomerDto.java


package guru.sfg.msscbeer.client.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerDto {

    private UUID id;
    private String name;

}///:~