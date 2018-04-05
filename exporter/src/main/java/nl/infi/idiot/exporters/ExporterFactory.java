package nl.infi.idiot.exporters;

import com.google.common.collect.ImmutableMap;
import nl.infi.idiot.core.DnsProviders;
import nl.infi.idiot.rest.CloudflareApiFactory;

import java.util.Properties;
import java.util.function.Function;

import static nl.infi.idiot.core.DnsProviders.DnsProviderName.Cloudflare;

public final class ExporterFactory {
    private static ImmutableMap<DnsProviders.DnsProviderName, Function<Properties, Exporter>> PROVIDER_EXPORTER_MAPPING = ImmutableMap.of(
            Cloudflare, ExporterRepository.cloudFlare
    );

    public static Exporter create(DnsProviders.DnsProviderName outgoingProviderName, Properties properties) {
        Function<Properties, Exporter> fun = PROVIDER_EXPORTER_MAPPING.get(outgoingProviderName);
        return fun.apply(properties);
    }

    private static class ExporterRepository {
        static Function<Properties, Exporter> cloudFlare = properties -> new CloudflareDnsExporter(CloudflareApiFactory.create(properties));
    }
}
