package nl.infi.soliddns.core;

import java.util.Optional;

public class DNSRecord {
    private DNSRecord(String name, String type, String value, Optional<Integer> preference, Integer ttl) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.preference = preference;
        this.ttl = ttl;
    }

    public String getType() {
        return type;
    }

    public Integer getTtl() {
        return ttl;
    }

    public String getName() {
        return name;
    }

    public Optional<Integer> getPreference() {
        return preference;
    }

    public String getValue() {
        return value;
    }

    public static class Builder {
        private String type;
        private String name;
        private String value;
        private Integer ttl;
        private Optional<Integer> preference;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setPreference(Optional<Integer> preference) {
            this.preference = preference;
            return this;
        }

        public Builder setTtl(Integer ttl) {
            this.ttl = ttl;
            return this;
        }

        public DNSRecord build() {
            //Name;Type;Value;Pref;TTL
            return new DNSRecord(this.name, this.type, this.value, this.preference, this.ttl);
        }
    }

    private String name;
    private String type;
    private String value;
    private Optional<Integer> preference;
    private Integer ttl;
}
