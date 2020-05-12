//: guru.sfg.msscbeer.client.config.ConfigProperties.java


package guru.sfg.msscbeer.client.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigProperties {

    @Bean
    @ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
    public SfgBreweryProperties sfgBrewery() {
        return new SfgBreweryProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "logging.level.org.apache",
            ignoreUnknownFields = false)
    public HttpClientLoggerProperties serverProperties() {
        return new HttpClientLoggerProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "httpclient.connection",
            ignoreUnknownFields = false)
    public HttpClientProperties httpClientProperties() {
        return new HttpClientProperties();
    }

}///:~