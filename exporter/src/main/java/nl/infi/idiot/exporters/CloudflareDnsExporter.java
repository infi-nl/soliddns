package nl.infi.idiot.exporters;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;
import nl.infi.idiot.rest.DNSRestApi;

import static java.lang.Thread.sleep;

public class CloudflareDnsExporter implements Exporter {
    private static final int SLEEP_BEFORE_PUSH_IN_MS = 250;
    private DNSRestApi api;

    CloudflareDnsExporter(DNSRestApi api) {
        this.api = api;
    }

    @Override
    public void pushDnsRecordsToProvider(ImmutableList<DNSRecord> dnsRecords) {
        dnsRecords.forEach(dnsRecord -> {
            try {
                sleep(SLEEP_BEFORE_PUSH_IN_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            api.addRecord(dnsRecord);
        });
    }
}
