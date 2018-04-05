package nl.infi.idiot.api.dns.records;

import nl.infi.idiot.core.DnsProviders.DnsProviderName;
import nl.infi.idiot.rest.DNSRestApi;
import nl.infi.idiot.rest.RestApiConfig;

public interface RecordsService {
    DNSRestApi getRestApi(DnsProviderName providerName, RestApiConfig config) throws Exception;
}
