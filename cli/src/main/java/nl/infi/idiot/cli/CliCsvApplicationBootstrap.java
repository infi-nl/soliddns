package nl.infi.idiot.cli;

import nl.infi.idiot.core.DnsProviders;
import nl.infi.idiot.exporters.Exporter;
import nl.infi.idiot.exporters.ExporterFactory;
import nl.infi.idiot.importer.domain.DnsCsvProviders;
import nl.infi.idiot.importer.importers.Importer;
import nl.infi.idiot.importer.importers.ImporterFactory;

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
