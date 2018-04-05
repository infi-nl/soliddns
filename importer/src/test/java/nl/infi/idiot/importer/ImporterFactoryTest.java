package nl.infi.idiot.importer;

import nl.infi.idiot.importer.domain.DnsCsvProviders;
import nl.infi.idiot.importer.importers.Importer;
import nl.infi.idiot.importer.importers.ImporterFactory;
import nl.infi.idiot.importer.importers.internedservices.InternedServicesImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

public class ImporterFactoryTest {

    @Test
    void importerFactoryReturnsCorrectImporterForInternedServices () {
        Properties properties = new Properties();
        Importer importer = ImporterFactory.create(DnsCsvProviders.DnsCsvProviderName.InternedServices, properties);
        Assertions.assertTrue(importer instanceof InternedServicesImporter);
    }

}
