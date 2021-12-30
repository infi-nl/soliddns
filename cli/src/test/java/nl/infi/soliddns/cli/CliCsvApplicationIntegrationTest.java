package nl.infi.soliddns.cli;


import com.google.common.collect.ImmutableList;
import nl.infi.soliddns.core.DNSRecord;
import nl.infi.soliddns.exporters.CloudflareDnsExporter;
import nl.infi.soliddns.exporters.Exporter;
import nl.infi.soliddns.importer.importers.Importer;
import nl.infi.soliddns.importer.importers.ImporterException;
import nl.infi.soliddns.importer.importers.internedservices.InternedServicesImporter;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CliCsvApplicationIntegrationTest {

    @Test
    public void doesTheApplicationCallPushDnsRecordsToProvider() {
        Exporter mockedExporter = mock(CloudflareDnsExporter.class);
        Importer mockedImporter = mock(InternedServicesImporter.class);

        Properties properties = new Properties();

        DNSRecord dnsRecord = new DNSRecord.Builder().build();

        ImmutableList<DNSRecord> dnsRecords = ImmutableList.of(dnsRecord);

        try {
            when(mockedImporter.load()).thenReturn(dnsRecords);
        } catch (ImporterException e) {
            e.printStackTrace();
        }

        CliCsvApplication app = new CliCsvApplication(mockedImporter, mockedExporter, properties);

        app.start();



        try {
            verify(mockedImporter, times(1)).load();
        } catch (ImporterException e) {
            e.printStackTrace();
        }

        verify(mockedExporter).pushDnsRecordsToProvider(dnsRecords);
    }

}
