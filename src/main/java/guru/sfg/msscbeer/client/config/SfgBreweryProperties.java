//: guru.sfg.msscbeer.client.config.SfgBreweryProperties.java


package guru.sfg.msscbeer.client.config;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class SfgBreweryProperties {

    @NotBlank
    private String apiHost;

    @NotBlank
    private String hostName;

    @Min(1025)
    @Max(8080)
    private int port;

}///:~