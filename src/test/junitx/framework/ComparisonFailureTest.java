package junitx.framework;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.2 $ $Date: 2003/05/07 04:35:47 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ComparisonFailureTest extends TestCase {

    private String description;
    private String message;
    private String expected;
    private String actual;
    private String display;

    public ComparisonFailureTest(String description,
                                 String message,
                                 String expected,
                                 String actual,
                                 String display) {
        super("execute");

        this.description = description;
        this.message = message;
        this.expected = expected;
        this.actual = actual;
        this.display = display;
    }

    public static Test suite() {
        String expected = "12345678901234 12345678 12345678901234";

        TestSuite suite = new TestSuite();
        suite.addTest(new ComparisonFailureTest("StartSmall", null, expected, "aaaa5678901234 12345678 12345678901234", "expected:<1234567890...> but was:<aaaa567890...> ['aaaa']"));
        suite.addTest(new ComparisonFailureTest("StartLong", null, expected, "aaaaaaaaaaaa34 12345678 12345678901234", "expected:<123456789012...> but was:<aaaaaaaaaaaa...>"));
        suite.addTest(new ComparisonFailureTest("EndSmall", null, expected, "12345678901234 12345678 1234567890aaaa", "expected:<...5678901234> but was:<...567890aaaa> ['aaaa']"));
        suite.addTest(new ComparisonFailureTest("EndLong", null, expected, "12345678901234 12345678 12aaaaaaaaaaaa", "expected:<...345678901234> but was:<...aaaaaaaaaaaa>"));

        suite.addTest(new ComparisonFailureTest("SameStartSmall-1", null, expected, "12aaaa78901234 12345678 12345678901234", "expected:<1234567890...> but was:<12aaaa7890...> ['aaaa']"));
        suite.addTest(new ComparisonFailureTest("SameStartLong", null, expected, "12aaaaaaaaaa34 12345678 12345678901234", "expected:<...3456789012...> but was:<...aaaaaaaaaa...>"));
        suite.addTest(new ComparisonFailureTest("SameStartSmall-2", null, expected, "12345678aaaa34 12345678 12345678901234", "expected:<...3456789012...> but was:<...345678aaaa...> ['aaaa']"));
    
        suite.addTest(new ComparisonFailureTest("SameEndSmall-1", null, expected, "12345678901234 12345678 12345678aaaa34", "expected:<...3456789012...> but was:<...345678aaaa...> ['aaaa']"));
        suite.addTest(new ComparisonFailureTest("SameEndLong", null, expected, "12345678901234 12345678 12aaaaaaaaaa34", "expected:<...3456789012...> but was:<...aaaaaaaaaa...>"));
        suite.addTest(new ComparisonFailureTest("SameEndSmall-2", null, expected, "12345678901234 12345678 12aaaa78901234", "expected:<... 123456789...> but was:<... 12aaaa789...> ['aaaa']"));
    
        suite.addTest(new ComparisonFailureTest("MiddleSmall-1", null, expected, "12345678901234 1aaaa678 12345678901234", "expected:<... 12345678 ...> but was:<... 1aaaa678 ...> ['aaaa']"));
        suite.addTest(new ComparisonFailureTest("MiddleLong", null, expected, "12345678901234 aaaaaaaaaa 12345678901234", "expected:<...12345678...> but was:<...aaaaaaaaaa...>"));
        suite.addTest(new ComparisonFailureTest("MiddleSmall-2", null, expected, "12345678901234 123aaaa8 12345678901234", "expected:<... 12345678 ...> but was:<... 123aaaa8 ...> ['aaaa']"));

        suite.addTest(new ComparisonFailureTest("ActualNull", null, expected, null, "expected:<12345678901234 12345678 12345678901234> but was:<null>"));
        suite.addTest(new ComparisonFailureTest("ExpectedNull", null, null, expected, "expected:<null> but was:<12345678901234 12345678 12345678901234>"));

        suite.addTest(new ComparisonFailureTest("Bug good reason", null, "Good reason", "reason", "expected:<Good ...> but was:<...> ['Good ']"));
        
        /* adding the JUnit tests just to verify */
        suite.addTest(new ComparisonFailureTest("Message", "a", "b", "c", "a expected:<b> but was:<c>"));
        suite.addTest(new ComparisonFailureTest("StartSame", null, "ba", "bc", "expected:<ba> but was:<bc> ['c']"));
        suite.addTest(new ComparisonFailureTest("EndSame", null, "ab", "cb", "expected:<ab> but was:<cb> ['c']"));
        suite.addTest(new ComparisonFailureTest("Same", null, "ab", "ab", "expected:<ab> but was:<ab>"));
        suite.addTest(new ComparisonFailureTest("StartAndEndSame", null, "abc", "adc", "expected:<abc> but was:<adc> ['d']"));
        suite.addTest(new ComparisonFailureTest("StartSameComplete", null, "ab", "abc", "expected:<ab> but was:<abc> ['c']"));
        suite.addTest(new ComparisonFailureTest("EndSameComplete", null, "bc", "abc", "expected:<bc> but was:<abc> ['a']"));
        suite.addTest(new ComparisonFailureTest("OverlapingMatches", null, "abc", "abbc", "expected:<abc> but was:<abbc> ['b']"));
        suite.addTest(new ComparisonFailureTest("OverlapingMatches2", null, "abcdde", "abcde", "expected:<abcdde> but was:<abcde> ['d']"));
        suite.addTest(new ComparisonFailureTest("OverlapingMatches3", null, "abcde", "abcdde", "expected:<abcde> but was:<abcdde> ['d']"));
        
        return suite;
    }

    public void execute() {
        String msg = ComparisonFailure.createMessage(message, expected, actual);
        assertEquals(display, msg);
    }

    public String getName() {
        return this.description;
    }

}
