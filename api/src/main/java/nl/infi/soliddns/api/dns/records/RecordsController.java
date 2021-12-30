package nl.infi.soliddns.api.dns.records;


import nl.infi.soliddns.core.DNSRecord;
import nl.infi.soliddns.core.DnsProviders;
import nl.infi.soliddns.core.DnsProviders.DnsProviderName;
import nl.infi.soliddns.rest.DNSRestApi;
import nl.infi.soliddns.rest.RestApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/records")
public class RecordsController {

    private RecordsService recordsService;

    @Autowired
    public void setRecordsService(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @RequestMapping("/{domain:.+}/{dnsProvider:.+}")
    public List<DNSRecord> Index(
            @PathVariable("domain") String domain,
            @PathVariable("dnsProvider") String dnsProvider,
            @RequestParam("zone") Optional<String> zoneId,
            @RequestParam("apiKey") Optional<String> apiKey,
            @RequestParam("userEmail") Optional<String> userEmail

    ) {
        RestApiConfig config = buildConfigForApi(domain, zoneId, apiKey, userEmail);
        DnsProviderName providerName = DnsProviders.getProviderName(dnsProvider);
        DNSRestApi api;
        try {
            api = recordsService.getRestApi(providerName, config);
        } catch (Exception e) { // ML TODO: ExternalApiConstructionException?
            return null; // ML TODO: return something appropriate
        }
        return api.getRecords();
    }

    private RestApiConfig buildConfigForApi(String domain, Optional<String> zoneId, Optional<String> apiKey, Optional<String> userEmail) {
        return new RestApiConfig(domain, zoneId, apiKey, userEmail);
    }


}
