package nl.infi.idiot.importer;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;
import nl.infi.idiot.importer.domain.DnsCsvProviders;
import nl.infi.idiot.importer.importers.Importer;
import nl.infi.idiot.importer.importers.ImporterException;
import nl.infi.idiot.importer.importers.ImporterFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Properties;

@Tag("fast")
class InternedServicesImporterTest {

    @Test
    void internedServicesCsvIsParsedWithSuccess() {
        Properties properties = new Properties();
        properties.setProperty("csvpath", "./src/test/resources/dns.csv");

        Importer importer = ImporterFactory.create(DnsCsvProviders.DnsCsvProviderName.InternedServices, properties);

        ImmutableList<DNSRecord> records = null;
        try {
            records = importer.load();
            Assertions.assertEquals(1, records.size());
        } catch (ImporterException e) {
            e.printStackTrace();
        }
        if (records == null) {
            Assertions.fail("No records parsed");
        }
    }

}
