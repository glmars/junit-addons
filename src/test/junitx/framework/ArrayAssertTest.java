package junitx.framework;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.5 $ $Date: 2003/01/20 10:44:50 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ArrayAssertTest
        extends TestCase {

    public ArrayAssertTest(String name) {
        super(name);
    }

    public void testSuccessAssertEquals1() {
        Object a[] = null;
        Object b[] = null;
        ArrayAssert.assertEquals(a, b);
    }

    public void testSuccessAssertEquals2() {
        Object a[] = {new Integer(1), new Integer(2)};
        Object b[] = {new Integer(1), new Integer(2)};
        ArrayAssert.assertEquals(a, b);
    }

    public void testSuccessAssertEquals3() {
        Object a[] = {new Integer(1), null, new Integer(2)};
        Object b[] = {new Integer(1), null, new Integer(2)};
        ArrayAssert.assertEquals(a, b);
    }

    public void testSuccessAssertEquals4() {
        Object a[] = {null};
        Object b[] = {null};
        ArrayAssert.assertEquals(a, b);
    }

    public void testFailAssertEquals1() {
        Object a[] = null;
        Object b[] = {new Integer(2)};
        try {
            ArrayAssert.assertEquals(a, b);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEquals2() {
        Object a[] = {new Integer(1)};
        Object b[] = null;
        try {
            ArrayAssert.assertEquals(a, b);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEquals3() {
        Object a[] = {new Integer(1)};
        Object b[] = {new Integer(2)};
        try {
            ArrayAssert.assertEquals(a, b);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEquals4() {
        Object a[] = {new Integer(1)};
        Object b[] = {new Integer(1), new Integer(1)};
        try {
            ArrayAssert.assertEquals(a, b);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEquals5() {
        Object a[] = {new Integer(1), null};
        Object b[] = {new Integer(1), new Integer(1)};
        try {
            ArrayAssert.assertEquals(a, b);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEquals6() {
        Object a[] = {new Integer(1), new Integer(1)};
        Object b[] = {new Integer(1), null};
        try {
            ArrayAssert.assertEquals(a, b);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEquals7() {
        Object a[] = {new Integer(1), new Integer(1), new Integer(2)};
        Object b[] = {new Integer(1), new Integer(2), new Integer(1)};
        try {
            ArrayAssert.assertEquals(a, b);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSuccessAssertEqualsDouble() {
        double[] expected = new double[]{3.2, 1.3, 4.5, 5.63};
        double[] actual = new double[]{3.2, 1.32, 4.6, 5.63};

        ArrayAssert.assertEquals(expected, expected, 0);
        ArrayAssert.assertEquals(expected, actual, .1);
    }

    public void testFailAssertEqualsDouble() {
        double[] expected = new double[]{3.2, 1.3, 4.5, 5.63};
        double[] actual = new double[]{3.2, 5.3, 4.5, 5.63};

        try {
            ArrayAssert.assertEquals(expected, actual, 0);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEqualsDouble2() {
        double[] expected = new double[]{3.2, 1.3, 4.5, 5.63};
        double[] actual = new double[]{3.2, 5.3, 4.5, 5.63};

        try {
            ArrayAssert.assertEquals(expected, actual, 0);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSuccessAssertEqualsFloat() {
        float[] expected = new float[]{3.2f, 1.3f, 4.5f, 5.63f};
        float[] actual = new float[]{3.2f, 1.32f, 4.6f, 5.63f};

        ArrayAssert.assertEquals(expected, expected, 0);
        ArrayAssert.assertEquals(expected, actual, 0.1f);
    }

    public void testFailAssertEqualsFloat() {
        float[] expected = new float[]{3.2f, 1.3f, 4.5f, 5.63f};
        float[] actual = new float[]{3.2f, 5.3f, 4.5f, 5.63f};

        try {
            ArrayAssert.assertEquals(expected, actual, 0);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailAssertEqualsFloat2() {
        float[] expected = new float[]{3.2f, 1.3f, 4.5f, 5.63f};
        float[] actual = new float[]{3.2f, 5.3f, 4.5f, 5.63f};

        try {
            ArrayAssert.assertEquals(expected, actual, 0.1f);
            fail();
        } catch (AssertionFailedError e) {
            return;
        }
    }

    public void testSuccessAssertEqualsLong() {
        long[] expected = new long[]{32, 10393, -483092, 39898};
        long[] actual = new long[]{32, 10393, -483092, 39898};

        ArrayAssert.assertEquals(expected, actual);
    }

    public void testFailAssertEqualsLong() {
        long[] expected = new long[]{32, 10393, -483092, 39898};
        long[] notExpected = new long[]{31, 10394, 483092, 39898};

        try {
            ArrayAssert.assertEquals(expected, notExpected);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSuccessAssertEqualsInt() {
        int[] expected = new int[]{32, 10393, -483092, 39898};
        int[] actual = new int[]{32, 10393, -483092, 39898};

        ArrayAssert.assertEquals(expected, actual);
    }

    public void testFaileAssertEqualsInt() {
        int[] expected = new int[]{32, 10393, -483092, 39898};
        int[] notExpected = new int[]{31, 10394, 483092, 39898};

        try {
            ArrayAssert.assertEquals(expected, notExpected);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSuccessAssertEqualsShort() {
        short[] expected = new short[]{32, 10393, -48, 398};
        short[] actual = new short[]{32, 10393, -48, 398};

        ArrayAssert.assertEquals(expected, actual);
    }

    public void testFailAssertEqualsShort() {

        short[] expected = new short[]{32, 10393, -48, 398};
        short[] notExpected = new short[]{31, 10394, 48, 398};

        try {
            ArrayAssert.assertEquals(expected, notExpected);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSuccessAssertEqualsChar() {
        char[] expected = new char[]{'a', 'b', 'c', 'z'};
        char[] actual = new char[]{'a', 'b', 'c', 'z'};

        ArrayAssert.assertEquals(expected, actual);
    }

    public void testFailAssertEqualsChar() {
        char[] expected = new char[]{'a', 'b', 'c', 'z'};
        char[] notExpected = new char[]{'a', 'b', 'c', 'y'};

        try {
            ArrayAssert.assertEquals(expected, notExpected);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSuccessAssertEqualsBoolean() {
        boolean[] expected = new boolean[]{true, false, false, true};
        boolean[] actual = new boolean[]{true, false, false, true};

        ArrayAssert.assertEquals(expected, actual);
    }

    public void testSuccessFailEqualsBoolean() {
        boolean[] expected = new boolean[]{true, false, false, true};
        boolean[] notExpected = new boolean[]{true, true, false, true};

        try {
            ArrayAssert.assertEquals(expected, notExpected);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSuccessAssertEqualsByte()
            throws Exception {
        String s = "This is the test";
        byte[] expected = s.getBytes("UTF8");
        byte[] actual = s.getBytes("UTF8");

        ArrayAssert.assertEquals(expected, actual);
    }

    public void testFailAssertEqualsByte()
            throws Exception {
        String s = "This is the test";
        byte[] expected = s.getBytes("UTF8");
        String notS = " This is not the test";
        byte[] notExpected = notS.getBytes("UTF8");

        try {
            ArrayAssert.assertEquals(expected, notExpected);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testMissingAssertEquivalence() {
        String[] actual = {"Bird", "Cat"};
        try {
            ArrayAssert.assertEquivalenceArrays("Test1", new String[]{"Cat", "Dog"}, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testMissingAssertEquivalence2() {
        String[] actual = {"Bird", "Cat"};
        try {
            ArrayAssert.assertEquivalenceArrays(new String[]{"Cat", "Dog"}, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testExtraAssertEquivalence() {
        String[] actual = {"Bird", "Cat"};
        try {
            ArrayAssert.assertEquivalenceArrays("Test1", new String[]{"Cat"}, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

}

