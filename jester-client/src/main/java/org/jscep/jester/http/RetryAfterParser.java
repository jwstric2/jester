package org.jscep.jester.http;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public class RetryAfterParser {
    private static DateTimeFormatter rfc1123 = DateTimeFormat.forPattern("E, dd MMM yyyy HH:mm:ss z");
    private static DateTimeFormatter rfc850 = DateTimeFormat.forPattern("EEEEE, dd-MMM-yy HH:mm:ss z");
    private static DateTimeFormatter asctime = DateTimeFormat.forPattern("E MMM d HH:mm:ss yyyy");
    private static DateTimeFormatter formatter;

    static {
        DateTimeParser[] parsers = new DateTimeParser[] {rfc1123.getParser(), rfc850.getParser(), asctime.getParser()};
        formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter().withZoneUTC();
    }

    public static DateTime parse(String date) {
        try {
            return formatter.parseDateTime(date);
        } catch (IllegalArgumentException e) {
            int duration = Integer.parseInt(date);
            DateTime time = new DateTime();

            return time.withDurationAdded(duration * 1000, 1);
        }
    }
}
