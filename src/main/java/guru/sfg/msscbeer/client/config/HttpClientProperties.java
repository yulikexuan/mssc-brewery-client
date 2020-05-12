//: guru.sfg.msscbeer.client.config.HttpClientProperties.java


package guru.sfg.msscbeer.client.config;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HttpClientProperties {

    private int maxTotalOpen;
    private int maxPerRoute;
    private int connectionTimeout;
    private int socketTimeout;

}///:~