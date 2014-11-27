package junitx.util;

import junit.framework.Test;
import junit.framework.TestCase;

import java.io.File;
import java.util.List;

/**
 * @version $Revision: 1.8 $ $Date: 2003/05/22 03:06:26 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class DirectorySuiteBuilderTest
        extends TestCase {

    private final static String DIRECTORY = "build/classes-example";
    
    public DirectorySuiteBuilderTest(String name) {
        super(name);
    }

    /**
     * The Sample1Test class contains 1 method and no suite() method.
     */
    public void testBuildSampleA()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleAPath");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(DIRECTORY);
        assertNotNull(suite);
        assertEquals(1, suite.countTestCases());
    }    

    /**
     * The Sample2Test class contains 3 test methods but its suite()
     * method only takes the first 2 into account.  The build suite
     * must contain 2 tests since the suite method has priority
     * vs. the reflection mechanism.
     */
    public void testBuildSampleB()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleBPath");
            }
        };
        builder.setFilter(filter);
        
        Test suite = builder.suite(DIRECTORY);
        assertNotNull(suite);
        assertEquals(2, suite.countTestCases());
    }        

    public void testBuildSample()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).endsWith("PathTest");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(DIRECTORY);
        assertNotNull(suite);
        assertEquals(3, suite.countTestCases());
    }        

    public void testBrowse()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).endsWith("PathTest");
            }
        };
        builder.setFilter(filter);

        List classes = builder.browse(new File(DIRECTORY));

        assertEquals(2, classes.size());
        assertTrue(classes.contains("junitx.example.packageA.SampleAPathTest"));
        assertTrue(classes.contains("junitx.example.packageA.packageB.SampleBPathTest"));
    }

    public void testIsTestClass() {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).endsWith("PathTest");
            }
        };
        builder.setFilter(filter);

        assertTrue(builder.isTestClass("/build/classes/junitx/example/packageA/SampleAPathTest.class"));
        assertTrue(builder.isTestClass("/build/classes/junitx/example/packageA/packageB/SampleBPathTest.class"));
    }

    /**
     * The Sample1Test class contains 1 method and no suite() method.
     */
    public void testBuildSampleA_BasicTestFilter()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleAPath");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(DIRECTORY);
        assertNotNull(suite);
        assertEquals(1, suite.countTestCases());
    }    

    /**
     * The Sample2Test class contains 3 test methods but its suite()
     * method only takes the first 2 into account.  The build suite
     * must contain 2 tests since the suite method has priority
     * vs. the reflection mechanism.
     */
    public void testBuildSampleB_BasicTestFilter()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).startsWith("SampleBPath");
            }
        };
        builder.setFilter(filter);
        
        Test suite = builder.suite(DIRECTORY);
        assertNotNull(suite);
        assertEquals(2, suite.countTestCases());
    }        

    public void testBuildSample_BasicTestFilter()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).endsWith("PathTest");
            }
        };
        builder.setFilter(filter);

        Test suite = builder.suite(DIRECTORY);
        assertNotNull(suite);
        assertEquals(3, suite.countTestCases());
    }        

    public void testBrowse_BasicTestFilter()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).endsWith("PathTest");
            }
        };
        builder.setFilter(filter);

        List classes = builder.browse(new File(DIRECTORY));

        assertEquals(2, classes.size());
        assertTrue(classes.contains("junitx.example.packageA.SampleAPathTest"));
        assertTrue(classes.contains("junitx.example.packageA.packageB.SampleBPathTest"));
    }

    public void testBrowseByPackageName_BasicTestFilter()
            throws Exception {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).endsWith("PathTest") &&
                    getPackageName(classpath).equals("junitx.example.packageA");
            }
        };
        builder.setFilter(filter);

        List classes = builder.browse(new File(DIRECTORY));

        assertEquals(1, classes.size());
        assertTrue(classes.contains("junitx.example.packageA.SampleAPathTest"));
    }

    public void testIsTestClass_BasicTestFilter() {
        DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return super.include(classpath) && 
                    getClassName(classpath).endsWith("PathTest");
            }
        };
        builder.setFilter(filter);

        assertTrue(builder.isTestClass("/build/classes/junitx/example/packageA/SampleAPathTest.class"));
        assertTrue(builder.isTestClass("/build/classes/junitx/example/packageA/packageB/SampleBPathTest.class"));
    }

}