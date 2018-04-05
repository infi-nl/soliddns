package nl.infi.idiot.exporters;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;

public interface Exporter {
    void pushDnsRecordsToProvider(ImmutableList<DNSRecord> dnsRecords);
}
