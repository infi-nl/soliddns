package nl.infi.soliddns.rest;

import java.util.Optional;

public class RestApiConfig {
    String domain;
    Optional<String> zoneId;
    Optional<String> apiKey;
    Optional<String> userEmail;

    public RestApiConfig(String domain,
                         Optional<String> zoneId,
                         Optional<String> apiKey,
                         Optional<String> userEmail
    ) {
        this.domain = domain;
        this.zoneId = zoneId;
        this.apiKey = apiKey;
        this.userEmail = userEmail;
    }
}
