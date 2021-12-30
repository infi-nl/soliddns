package nl.infi.soliddns.core;


public class DnsProviders {
    public static DnsProviderName getProviderName(String dnsProvider) {
        return DnsProviderName.valueOf(dnsProvider);
    }

    public enum DnsProviderName {
        Cloudflare
    }
}
