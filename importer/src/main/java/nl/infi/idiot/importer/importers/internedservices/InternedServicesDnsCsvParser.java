package nl.infi.idiot.importer.importers.internedservices;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class InternedServicesDnsCsvParser {
    private static Integer parseTtl(String s) {
        if (s.isEmpty()) {
            return new Integer(300);
        }
        Integer ttl = Integer.valueOf(s);

        if (ttl < 300) {
            return new Integer(300);
        }

        return ttl;

    }

    private static Optional<Integer> parsePreference(String s) {
        if (s.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(Integer.valueOf(s));
    }

    public static ImmutableList<DNSRecord> parseList (List<String[]> entries ){
        Stream<String[]> dnsStream = entries.stream();

        return dnsStream
                .map(InternedServicesDnsCsvParser.parseCsvLine)
                .collect(ImmutableList.toImmutableList());
    };

    public static Function<String[], DNSRecord> parseCsvLine = line -> new DNSRecord.Builder()
            .setName(line[0])
            .setType(line[1])
            .setValue(line[2])
            .setPreference(parsePreference(line[3]))
            .setTtl(parseTtl(line[4]))
            .build();

}
