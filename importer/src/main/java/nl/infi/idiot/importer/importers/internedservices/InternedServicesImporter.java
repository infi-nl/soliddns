package nl.infi.idiot.importer.importers.internedservices;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;
import nl.infi.idiot.importer.importers.Importer;
import nl.infi.idiot.importer.importers.ImporterException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.Properties;

public class InternedServicesImporter implements Importer {

    private Properties properties;

    public InternedServicesImporter(Properties properties) {
        this.properties = properties;
    }

    public ImmutableList<DNSRecord> load() throws ImporterException {
        try {
            final CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();

            CSVReader reader = new CSVReaderBuilder(new FileReader(this.properties.getProperty("csvpath")))
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();

            return InternedServicesDnsCsvParser.parseList(reader.readAll());

        } catch (Exception e) {
            throw new ImporterException();
        }
    }


}
