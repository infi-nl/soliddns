package nl.infi.soliddns.cli;


import com.google.common.collect.ImmutableList;
import nl.infi.soliddns.core.DNSRecord;
import nl.infi.soliddns.exporters.Exporter;
import nl.infi.soliddns.importer.importers.Importer;
import nl.infi.soliddns.importer.importers.ImporterException;

import java.util.Optional;
import java.util.Properties;

public class CliCsvApplication {

    private Importer importer;
    private Exporter exporter;
    private Properties properties;

    public CliCsvApplication(Importer importer, Exporter exporter, Properties properties) {
        this.importer = importer;
        this.exporter = exporter;
        this.properties = properties;
    }

    public void start() {

        Optional<ImmutableList<DNSRecord>> dnsRecords = Optional.empty();

        try {
            dnsRecords = Optional.of(this.importer.load());
        } catch (ImporterException e) {
            System.out.println("Something went wrong while importing the provided csv");
        }

        dnsRecords.ifPresent(exporter::pushDnsRecordsToProvider);
    }


}
