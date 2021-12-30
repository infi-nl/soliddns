package nl.infi.soliddns.cli;

import nl.infi.soliddns.core.DnsProviders;
import nl.infi.soliddns.exporters.Exporter;
import nl.infi.soliddns.exporters.ExporterFactory;
import nl.infi.soliddns.importer.domain.DnsCsvProviders;
import nl.infi.soliddns.importer.importers.Importer;
import nl.infi.soliddns.importer.importers.ImporterFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CliCsvApplicationBootstrap {

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            InputStream input = new FileInputStream(args[0]);
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Importer importer = ImporterFactory.create(DnsCsvProviders.getByName(properties.getProperty("csvprovidername")), properties);

        Exporter exporter = ExporterFactory.create(DnsProviders.getProviderName(properties.getProperty("dnsprovidername")), properties);

        CliCsvApplication app = new CliCsvApplication(importer, exporter, properties);

        app.start();
    }
}
