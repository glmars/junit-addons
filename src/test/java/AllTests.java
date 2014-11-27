import junit.framework.Test;
import junit.framework.TestSuite;
import junitx.util.BasicTestFilter;
import junitx.util.DirectorySuiteBuilder;

/**
 * @version $Revision: 1.14 $ $Date: 2003/05/22 02:25:18 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class AllTests {

    public static Test suite() throws Exception {
        TestSuite suite = new TestSuite();

        suite.addTestSuite(junitx.util.BasicTestFilterTest.class);
        suite.addTestSuite(junitx.util.MockedSuiteBuilderTest.class);
        suite.addTestSuite(junitx.util.DirectorySuiteBuilderTest.class);
        suite.addTestSuite(junitx.util.JarArchiveSuiteBuilderTest.class);
        suite.addTestSuite(junitx.util.ZipArchiveSuiteBuilderTest.class);

        DirectorySuiteBuilder builder = new DirectorySuiteBuilder(new BasicTestFilter() {
            public boolean include(String classpath) {
                String classname = getClassName(classpath);
                return super.include(classpath)
                    && !classname.equals("BasicTestFilterTest")
                    && (classname.indexOf("SuiteBuilderTest") <= 0);
            }
        });
        suite.addTest(builder.suite("build/classes-test"));

        return suite;
    }

}
