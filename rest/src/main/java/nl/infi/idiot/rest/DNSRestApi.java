package nl.infi.idiot.rest;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;

public interface DNSRestApi {
    void addRecord(DNSRecord dnsRecord);
    ImmutableList<DNSRecord> getRecords();
}
