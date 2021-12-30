package nl.infi.soliddns.api.dns.records;

import nl.infi.soliddns.core.DnsProviders.DnsProviderName;
import nl.infi.soliddns.rest.DNSRestApi;
import nl.infi.soliddns.rest.RestApiConfig;

public interface RecordsService {
    DNSRestApi getRestApi(DnsProviderName providerName, RestApiConfig config) throws Exception;
}
