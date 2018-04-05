package nl.infi.idiot.importer.importers;

import com.google.common.collect.ImmutableMap;
import nl.infi.idiot.importer.domain.DnsCsvProviders;
import nl.infi.idiot.importer.importers.internedservices.InternedServicesImporter;

import java.util.Properties;
import java.util.function.Function;

public final class ImporterFactory {
    private static ImmutableMap<DnsCsvProviders.DnsCsvProviderName, Function<Properties, Importer>> PROVIDER_IMPORTER_MAPPING = ImmutableMap.of(
            DnsCsvProviders.DnsCsvProviderName.InternedServices, ImporterRepository.internedServices
    );

    private ImporterFactory() {

    }

    public static Importer create(DnsCsvProviders.DnsCsvProviderName providerName, Properties properties) {
        Function<Properties, Importer> fun = PROVIDER_IMPORTER_MAPPING.get(providerName);
        return fun.apply(properties);
    }

    private static class ImporterRepository {
        static Function<Properties, Importer> internedServices = properties -> new InternedServicesImporter(properties);
    }
}
