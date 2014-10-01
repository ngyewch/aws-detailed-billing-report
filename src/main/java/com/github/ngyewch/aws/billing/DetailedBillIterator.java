package com.github.ngyewch.aws.billing;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DetailedBillIterator
        implements Iterator<LineItem>, Closeable {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final String TAG_PREFIX = "user:";
    private final Reader reader;
    private final CSVParser parser;
    private final Iterator<CSVRecord> iterator;
    private final Set<String> tagNames = new HashSet<String>();

    public DetailedBillIterator(InputStream istrm)
            throws IOException {
        super();
        reader = new InputStreamReader(istrm, "ISO-8859-1");
        parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        for (String tagName : parser.getHeaderMap().keySet()) {
            if (tagName.startsWith(TAG_PREFIX)) {
                tagNames.add(tagName.substring(TAG_PREFIX.length()));
            }
        }
        iterator = parser.iterator();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public LineItem next() {
        LineItem lineItem = new LineItem();
        next(lineItem);
        return lineItem;
    }

    public void next(LineItem lineItem) {
        CSVRecord record = iterator.next();
        lineItem.setInvoiceId(record.get("InvoiceID"));
        lineItem.setPayerAccountId(toLong(record.get("PayerAccountId")));
        lineItem.setLinkedAccountId(toLong(record.get("LinkedAccountId")));
        lineItem.setRecordType(record.get("RecordType"));
        lineItem.setRecordId(toBigInteger(record.get("RecordId")));
        lineItem.setProductName(record.get("ProductName"));
        lineItem.setRateId(toLong(record.get("RateId")));
        lineItem.setSubscriptionId(toLong(record.get("SubscriptionId")));
        lineItem.setPricingPlanId(toLong(record.get("PricingPlanId")));
        lineItem.setUsageType(record.get("UsageType"));
        lineItem.setOperation(record.get("Operation"));
        lineItem.setAvailabilityZone(record.get("AvailabilityZone"));
        lineItem.setReservedInstance(toBoolean(record.get("ReservedInstance")));
        lineItem.setItemDescription(record.get("ItemDescription"));
        lineItem.setUsageStartDate(toDate(record.get("UsageStartDate")));
        lineItem.setUsageEndDate(toDate(record.get("UsageEndDate")));
        lineItem.setUsageQuantity(toDouble(record.get("UsageQuantity")));
        lineItem.setBlendedRate(toDouble(record.get("BlendedRate")));
        lineItem.setBlendedCost(toDouble(record.get("BlendedCost")));
        lineItem.setUnBlendedRate(toDouble(record.get("UnBlendedRate")));
        lineItem.setUnBlendedCost(toDouble(record.get("UnBlendedCost")));
        lineItem.setResourceId(record.get("ResourceId"));
        if (tagNames.isEmpty()) {
            lineItem.setTags(Collections.EMPTY_MAP);
        } else {
            Map<String, String> tags = new HashMap<String, String>();
            for (String tagName : tagNames) {
                tags.put(tagName, record.get(TAG_PREFIX + tagName));
            }
            lineItem.setTags(tags);
        }
    }

    private Long toLong(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return new Long(s);
    }

    private Double toDouble(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return new Double(s);
    }

    private BigInteger toBigInteger(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return new BigInteger(s);
    }

    private Boolean toBoolean(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (s.equals("Y")) {
            return Boolean.TRUE;
        } else if (s.equals("N")) {
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("Invalid boolean value: " + s);
    }

    private Date toDate(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return DATE_TIME_FORMATTER.parseLocalDateTime(s)
                .toDateTime(DateTimeZone.UTC).toDate();
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void close()
            throws IOException {
        parser.close();
        reader.close();
    }
}
