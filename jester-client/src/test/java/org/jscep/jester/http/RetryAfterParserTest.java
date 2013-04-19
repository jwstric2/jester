package org.jscep.jester.http;

import org.joda.time.DateTime;

import java.util.Date;
import static org.junit.Assert.*;

public class RetryAfterParserTest {
    public void testParseDate() {
        DateTime date = RetryAfterParser.parse("Fri, 31 Dec 1999 23:59:59 GMT");

        assertEquals(31, date.getDayOfMonth());
    }
}
