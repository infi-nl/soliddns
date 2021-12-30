package nl.infi.soliddns.rest;

import nl.infi.soliddns.core.DnsProviders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DnsRestApiFactoryTests {
    @Test
    void test() {
        RestApiConfig config = new RestApiConfig("test.nl", Optional.of("zoneId"), Optional.of("apikey"), Optional.of("userEmail"));
        DNSRestApi api = DNSRestApiFactory.create(DnsProviders.DnsProviderName.Cloudflare, config);
        Assertions.assertEquals(CloudflareApi.class, api.getClass());
    }
}
