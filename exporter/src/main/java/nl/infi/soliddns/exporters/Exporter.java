package nl.infi.soliddns.exporters;

import com.google.common.collect.ImmutableList;
import nl.infi.soliddns.core.DNSRecord;

public interface Exporter {
    void pushDnsRecordsToProvider(ImmutableList<DNSRecord> dnsRecords);
}
