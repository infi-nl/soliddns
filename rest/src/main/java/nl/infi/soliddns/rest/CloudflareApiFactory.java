package nl.infi.soliddns.rest;


import java.util.Properties;

public final class CloudflareApiFactory {
    private CloudflareApiFactory() {
    }

    public static DNSRestApi create(RestApiConfig config) {
        String zoneId = config.zoneId.get();
        String apiKey = config.apiKey.get();
        String email = config.userEmail.get();
        return new CloudflareApi(zoneId, apiKey, email);
    }

    public static DNSRestApi create(Properties properties) {
        String zoneId = properties.getProperty("cloudflarezoneid");
        String apiKey = properties.getProperty("cloudflareapikey");
        String email = properties.getProperty("useremail");

        return new CloudflareApi(zoneId, apiKey, email);
    }
}
