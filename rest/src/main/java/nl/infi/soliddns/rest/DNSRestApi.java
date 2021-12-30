package nl.infi.soliddns.rest;

import com.google.common.collect.ImmutableList;
import nl.infi.soliddns.core.DNSRecord;

public interface DNSRestApi {
    void addRecord(DNSRecord dnsRecord);
    ImmutableList<DNSRecord> getRecords();
}
