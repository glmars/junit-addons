package junitx.framework;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junitx.util.PropertyManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;

/**
 * @version $Revision: 1.7 $ $Date: 2003/05/22 02:24:15 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class FileAssertTest
        extends TestCase {

    public FileAssertTest(String name) {
        super(name);
    }

    public void testSucceed()
            throws Exception {
        String input = "line1\n" +
                "line2\n" +
                "line3\n" +
                "line4\n" +
                "line5\n";
        FileAssert.assertEquals(null, new StringReader(input), new StringReader(input));
    }

    public void testFailDiffer()
            throws Exception {
        String expected = "line1\n" +
                "line2\n" +
                "line3\n" +
                "line4\n" +
                "line5\n";
        String actual = "line1\n" +
                "line2\n" +
                "line10\n" +
                "line4\n" +
                "line5\n";
        try {
            FileAssert.assertEquals(null, new StringReader(expected), new StringReader(actual));
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailLonger()
            throws Exception {
        String expected = "line1\n" +
                "line2\n" +
                "line3\n" +
                "line4\n" +
                "line5\n";
        String actual = "line1\n" +
                "line2\n" +
                "line3\n" +
                "line4\n" +
                "line5\n" +
                "line6\n";
        try {
            FileAssert.assertEquals(null, new StringReader(expected), new StringReader(actual));
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailShorter()
            throws Exception {
        String expected = "line1\n" +
                "line2\n" +
                "line3\n" +
                "line4\n" +
                "line5\n";
        String actual = "line1\n" +
                "line2\n" +
                "line3\n" +
                "line4\n";
        try {
            FileAssert.assertEquals(null, new StringReader(expected), new StringReader(actual));
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSucceedFile()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.file"));
        File actual = new File(PropertyManager.getProperty("normal.file"));

        FileAssert.assertEquals(expected, actual);
    }

    public void testFailFileDiffer()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.file"));
        File actual = new File(PropertyManager.getProperty("differ.file"));

        try {
            FileAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailFileLonger()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.file"));
        File actual = new File(PropertyManager.getProperty("long.file"));

        try {
            FileAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testFailFileShorter()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.file"));
        File actual = new File(PropertyManager.getProperty("short.file"));

        try {
            FileAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void testSucceedBinaryFile()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.binary.file"));
        File actual = new File(PropertyManager.getProperty("normal.binary.file"));

        FileAssert.assertBinaryEquals(expected, actual);
    }

    public void testFailBinaryFileDiffer()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.binary.file"));
        File actual = new File(PropertyManager.getProperty("differ.binary.file"));

        try {
            FileAssert.assertBinaryEquals(expected, actual);
        } catch (AssertionFailedError e) {
            ThrowableAssert.assertSimilar(new AssertionFailedError("files differ at byte 151"), e);
            return;
        }
        fail();
    }

    public void testFailBinaryFileLonger()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.binary.file"));
        File actual = new File(PropertyManager.getProperty("long.binary.file"));

        try {
            FileAssert.assertBinaryEquals(expected, actual);
        } catch (AssertionFailedError e) {
            ThrowableAssert.assertSimilar(new AssertionFailedError("actual file is longer"), e);
            return;
        }
        fail();
    }

    public void testFailBinaryFileShorter()
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected.binary.file"));
        File actual = new File(PropertyManager.getProperty("short.binary.file"));

        try {
            FileAssert.assertBinaryEquals(expected, actual);
        } catch (AssertionFailedError e) {
            ThrowableAssert.assertSimilar(new AssertionFailedError("actual file is shorter"), e);
            return;
        }
        fail();
    }

    public void testSucceedFileLastLine() 
            throws Exception {
        File expected = new File(PropertyManager.getProperty("expected-lastline.file"));
        File actual = new File(PropertyManager.getProperty("expected-lastline.file"));

        FileAssert.assertEquals(expected, actual);
    }

    public void testFailFileLastLine() {
        File expected = new File(PropertyManager.getProperty("expected-lastline.file"));
        File actual = new File(PropertyManager.getProperty("differ-lastline.file"));

        try {
            FileAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    public void create()
            throws Exception {
        File input = new File(PropertyManager.getProperty("expected.binary.file"));

        FileInputStream inputis = new FileInputStream(input);
        byte[] buff = new byte[400];
        inputis.read(buff, 0, 400);

        FileOutputStream out;

        out = new FileOutputStream(new File("expected.bin"));
        out.write(buff, 0, 300);
        out.close();

        out = new FileOutputStream(new File("normal.bin"));
        out.write(buff, 0, 300);
        out.close();

        out = new FileOutputStream(new File("long.bin"));
        out.write(buff, 0, 400);
        out.close();

        out = new FileOutputStream(new File("short.bin"));
        out.write(buff, 0, 200);
        out.close();

        out = new FileOutputStream(new File("differ.bin"));
        buff[150] ^= (byte) 0xFF;
        out.write(buff, 0, 300);
        out.close();

        inputis.close();
    }

}
