package nl.infi.soliddns.rest;

import com.google.common.collect.ImmutableMap;
import nl.infi.soliddns.core.DnsProviders.DnsProviderName;

import java.util.function.Function;


public final class DNSRestApiFactory {
    private static ImmutableMap<DnsProviderName, Function<RestApiConfig, DNSRestApi>> PROVIDER_RESTAPI_MAPPING = ImmutableMap.of(
            DnsProviderName.Cloudflare, RestApiRepository.cloudFlare
    );
    public static DNSRestApi create(DnsProviderName providerName, RestApiConfig config) {
        return PROVIDER_RESTAPI_MAPPING.get(providerName).apply(config);
    }

    private static class RestApiRepository {
        static Function<RestApiConfig, DNSRestApi> cloudFlare = config -> CloudflareApiFactory.create(config);
    }
}
