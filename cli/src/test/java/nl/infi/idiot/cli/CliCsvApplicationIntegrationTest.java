package nl.infi.idiot.cli;


import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;
import nl.infi.idiot.exporters.CloudflareDnsExporter;
import nl.infi.idiot.exporters.Exporter;
import nl.infi.idiot.importer.importers.Importer;
import nl.infi.idiot.importer.importers.ImporterException;
import nl.infi.idiot.importer.importers.internedservices.InternedServicesImporter;
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
