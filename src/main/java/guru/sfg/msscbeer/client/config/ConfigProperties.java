//: guru.sfg.msscbeer.client.config.ConfigProperties.java


package guru.sfg.msscbeer.client.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class ConfigProperties {

    private String apiHost;

}///:~