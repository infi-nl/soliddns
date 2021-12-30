package nl.infi.soliddns.exporters;

import com.google.common.collect.ImmutableMap;
import nl.infi.soliddns.core.DnsProviders;
import nl.infi.soliddns.rest.CloudflareApiFactory;

import java.util.Properties;
import java.util.function.Function;

import static nl.infi.soliddns.core.DnsProviders.DnsProviderName.Cloudflare;

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
