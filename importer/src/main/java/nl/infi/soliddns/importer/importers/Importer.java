package nl.infi.soliddns.importer.importers;

import com.google.common.collect.ImmutableList;
import nl.infi.soliddns.core.DNSRecord;

public interface Importer {
    ImmutableList<DNSRecord> load() throws ImporterException;
}
