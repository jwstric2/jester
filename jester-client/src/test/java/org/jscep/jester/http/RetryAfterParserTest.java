package org.jscep.jester.http;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.tz.CachedDateTimeZone;
import org.joda.time.tz.DateTimeZoneBuilder;
import org.joda.time.tz.FixedDateTimeZone;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.*;

public class RetryAfterParserTest {
    @Test
    public void testParseDateRfc1123() {
        DateTime date = RetryAfterParser.parse("Sun, 06 Nov 1994 08:49:37 GMT");

        assertEquals(6, date.getDayOfMonth());
        assertEquals(11, date.getMonthOfYear());
        assertEquals(1994, date.getYear());
        assertEquals(8, date.getHourOfDay());
        assertEquals(49, date.getMinuteOfHour());
        assertEquals(37, date.getSecondOfMinute());
        assertEquals(CachedDateTimeZone.UTC, date.getZone());
    }

    @Test
    public void testParseDateRfc850() {
        DateTime date = RetryAfterParser.parse("Sunday, 06-Nov-94 08:49:37 GMT");

        assertEquals(6, date.getDayOfMonth());
        assertEquals(11, date.getMonthOfYear());
        assertEquals(1994, date.getYear());
        assertEquals(8, date.getHourOfDay());
        assertEquals(49, date.getMinuteOfHour());
        assertEquals(37, date.getSecondOfMinute());
        assertEquals(CachedDateTimeZone.UTC, date.getZone());
    }

    @Test
    public void testParseDateAscTime() {
        DateTime date = RetryAfterParser.parse("Sun Nov 6 08:49:37 1994");

        assertEquals(6, date.getDayOfMonth());
        assertEquals(11, date.getMonthOfYear());
        assertEquals(1994, date.getYear());
        assertEquals(8, date.getHourOfDay());
        assertEquals(49, date.getMinuteOfHour());
        assertEquals(37, date.getSecondOfMinute());
        assertEquals(CachedDateTimeZone.UTC, date.getZone());
    }

    @Test
    public void testParseDeltaSeconds() {
        DateTime actual = RetryAfterParser.parse("120");
        DateTime now = new DateTime();
        DateTime expected = now.withDurationAdded(120000, 1);

        assertEquals(expected, actual);
    }
}
