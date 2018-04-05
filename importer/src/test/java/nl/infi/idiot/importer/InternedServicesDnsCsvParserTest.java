/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package nl.infi.idiot.importer;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;
import nl.infi.idiot.importer.importers.internedservices.InternedServicesDnsCsvParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Tag("fast")
class InternedServicesDnsCsvParserTest {


    @Test
    @DisplayName("TTL is parsed with default of 300 in case of empty string")
    void ttlIsParsedWithDefaultOf300InCaseOfEmptyString() {
        DNSRecord record = InternedServicesDnsCsvParser.parseCsvLine.apply(this.getARecordWithTtl(""));
        Assertions.assertEquals(new Integer(300), record.getTtl());
    }

    @Test
    @DisplayName("TTL is parsed with default of 300 in case of lower than 300 value")
    void ttlIsParsedWithDefaultOf300WhenValueIsLowerThan300() {
        DNSRecord record = InternedServicesDnsCsvParser.parseCsvLine.apply(this.getARecordWithTtl("60"));
        Assertions.assertEquals(new Integer(300), record.getTtl());
    }

    @Test
    @DisplayName("Preference is empty when not available")
    void preferenceIsNotPresentWhenNotProvided() {
        DNSRecord record = InternedServicesDnsCsvParser.parseCsvLine.apply(this.getARecordWithTtl("60"));
        Assertions.assertEquals(false, record.getPreference().isPresent());
    }

    @Test
    @DisplayName("Preference is set correctly when provided")
    void preferenceIsSetWhenAvailable() {
        DNSRecord record = InternedServicesDnsCsvParser.parseCsvLine.apply(this.getMXRecordWithPreference("60"));

        Assertions.assertEquals(true, record.getPreference().isPresent());
        Assertions.assertEquals(new Integer(60), record.getPreference().orElse((60)));
    }

    @Test
    @DisplayName("List is parsed in full")
    void listIsParsedIn() {
        List<String[]> list = new ArrayList<>();
        list.add(this.getARecordWithTtl("300"));
        list.add(this.getARecordWithTtl("3600"));
        list.add(this.getARecordWithTtl(""));

        ImmutableList<DNSRecord> records = InternedServicesDnsCsvParser.parseList(list);
        Assertions.assertEquals(3, records.size());
    }

    private String[] getMXRecordWithPreference(String s) {
        return new String[]{"@", "MX", "mail.google.com", s, "300"};
    }


    private String[] getARecordWithTtl(String ttl) {
        return new String[]{"account", "A", "255.255.255.255", "", ttl};
    }
}