package nl.infi.soliddns.rest;

public final class RestConfigValidator {
    public static boolean cloudflareConfigIsValid(RestApiConfig config) {
        return config.zoneId.isPresent() && config.apiKey.isPresent() && config.userEmail.isPresent();
    }
}
