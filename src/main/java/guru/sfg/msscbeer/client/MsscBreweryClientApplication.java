//: guru.sfg.msscbeer.client.MsscBreweryClientApplication.java


package guru.sfg.msscbeer.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan("guru.sfg.msscbeer.client.config")
public class MsscBreweryClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBreweryClientApplication.class, args);
    }

}///:~