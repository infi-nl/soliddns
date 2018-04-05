package nl.infi.idiot.exporters;

import nl.infi.idiot.core.DnsProviders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Properties;

@Tag("fast")
public class ExporterFactoryTest {

    @Test
    void ExporterFactoryReturnsCorrectExporterForCloudflare() {
        Properties properties = new Properties();
        Exporter exporter  = ExporterFactory.create(DnsProviders.DnsProviderName.Cloudflare, properties);
        Assertions.assertTrue(exporter instanceof CloudflareDnsExporter);
    }

}
