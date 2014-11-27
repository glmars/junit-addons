package junitx.util;

import java.util.zip.ZipFile;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.7 $ $Date: 2003/05/22 03:14:42 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public abstract class AbstractArchiveSuiteBuilderTest extends TestCase {

    protected ZipFile m_file;

    public AbstractArchiveSuiteBuilderTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        m_file = new ZipFile(getFilename());
    }

    public void tearDown() throws Exception {
        m_file.close();
    }

    abstract protected String getFilename();

    /**
     * The Sample1Test class contains 1 method and no suite() method.
     */
    public void testBuildSampleA() throws Exception {
        ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleA");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(getFilename());
        assertNotNull(suite);
        assertEquals(1, suite.countTestCases());
    }

    public void testBuildSampleA_BasicTestFilter() throws Exception {
        ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleA");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(getFilename());
        assertNotNull(suite);
        assertEquals(1, suite.countTestCases());
    }

    /**
     * The Sample2Test class contains 3 test methods but its suite()
     * method only takes the first 2 into account.  The build suite
     * must contain 2 tests since the suite method has priority
     * vs. the reflection mechanism.
     */
    public void testBuildSampleB() throws Exception {
        ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleB");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(getFilename());
        assertNotNull(suite);
        assertEquals(2, suite.countTestCases());
    }

    public void testBuildSampleB_BasicTestFilter() throws Exception {
        ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleB");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(getFilename());
        assertNotNull(suite);
        assertEquals(2, suite.countTestCases());
    }

    public void testBuildPackageA()
            throws Exception {
        ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getPackageName(classpath).equals("junitx.example.packageA");
            }
        };
        builder.setFilter(filter);
        
        Test suite = builder.suite(getFilename());
        assertNotNull(suite);
        assertEquals(1, suite.countTestCases());
    }

    public void testBuildSamples() throws Exception {
        ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();

        Test suite = builder.suite(getFilename());
        assertNotNull(suite);
        assertEquals(3, suite.countTestCases());
    }

}
