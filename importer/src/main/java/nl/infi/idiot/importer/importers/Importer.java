package nl.infi.idiot.importer.importers;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;

public interface Importer {
    ImmutableList<DNSRecord> load() throws ImporterException;
}
