//: guru.sfg.msscbeer.client.config.BlockingRestTemplateCustomizer.java


package guru.sfg.msscbeer.client.config;


import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/*
 * PoolingHttpClientConnectionManager
 *   - Maintains a pool of HttpClientConnections and is able to service
 *     connection requests from multiple execution threads
 *   - Connections are pooled on a per route basis
 *   - A request for a route which already the manager has persistent
 *     connections for available in the pool will be services by leasing a
 *     connection from the pool rather than creating a brand new connection
 *   - Maintains a maximum limit of connection on a per route basis and in total
 *   - Per default this implementation will create no more than than 2 concurrent
 *     connections per given route and no more 20 connections in total
 *   - For many real-world applications these limits may prove too constraining,
 *     especially if they use HTTP as a transport protocol for their services
 *   - Connection limits, however, can be adjusted using ConnPoolControl methods
 *   - Total time to live (TTL) set at construction time defines maximum life
 *     span of persistent connections regardless of their expiration setting
 *       - No persistent connection will be re-used past its TTL value
 *
 * Requests are routed based on two pieces of information:
 *   - The HTTP request method
 *   - The request path
 * A route refers to an HTTP method, path, and handler combination
 * Routes are created and added to the server before it starts listening for
 * requests
 *
 */
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    static final int MAX_TOTAL_OPEN_CONNECTIONS = 100;
    static final int MAX_PER_ROUTE = 20;

    static final int HTTP_CONNECTION_TIMEOUT = 5000;
    static final int HTTP_SOCKET_TIMEOUT = 5000;

    private ClientHttpRequestFactory clientHttpRequestFactory() {

        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager();

        // Set the maximum number of total open connections
        connectionManager.setMaxTotal(MAX_TOTAL_OPEN_CONNECTIONS);

        /*
         * Set the maximum number of concurrent connections per route,
         * which is 2 by default.
         */
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        HttpHost breweryHost = new HttpHost("localhost", 8081);

        /*
         * Set the total number of concurrent connections to a specific route,
         * which is 2 by default.
         */
        connectionManager.setMaxPerRoute(new HttpRoute(breweryHost),
                MAX_PER_ROUTE / 2);

        /*
         * The Connection Timeout (http.connection.timeout)
         *   – the time to establish the connection with the remote host
         *
         * The Socket Timeout (http.socket.timeout)
         *   – the time waiting for data – after establishing the connection;
         *   - maximum time of inactivity between two data packets
         *
         * The Connection Manager Timeout (http.connection-manager.timeout)
         *   – the time to wait for a connection from the connection manager/pool
         */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(HTTP_CONNECTION_TIMEOUT)
                .setSocketTimeout(HTTP_SOCKET_TIMEOUT)
                .build();

        /*
         * onnectionKeepAliveStrategy
         *   - Interface for deciding how long a connection can remain idle
         *     before being reused
         * DefaultConnectionKeepAliveStrategy
         *   - Default implementation of a strategy deciding duration that a
         *     connection can remain idle. The default implementation looks
         *     solely at the 'Keep-Alive' header's timeout token
         */
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        /*
         * ClientHttpRequestFactory
         *   - Ues Apache HttpComponents HttpClient to create requests
         *   - Allows to use a pre-configured HttpClient instance
         *     - potentially with authentication, HTTP connection pooling, etc.
         *     - NOTE: Requires Apache HttpComponents 4.3 or higher, as of Spring 4.0.
         */
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

}///:~