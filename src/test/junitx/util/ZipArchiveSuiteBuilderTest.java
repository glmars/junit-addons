package junitx.util;

import java.util.List;

/**
 * @version $Revision: 1.3 $ $Date: 2003/05/22 03:06:26 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ZipArchiveSuiteBuilderTest
        extends AbstractArchiveSuiteBuilderTest {

    public ZipArchiveSuiteBuilderTest(String name) {
        super(name);
    }

    protected String getFilename() {
        return PropertyManager.getProperty("tests.zip");
    }

    public void testBrowse()
            throws Exception {
        ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();

        List classes = builder.browse(this.m_file);

        assertEquals(2, classes.size());
        assertTrue(classes.contains("junitx.example.packageA.SampleAZipTest"));
        assertTrue(classes.contains("junitx.example.packageA.packageB.SampleBZipTest"));
    }

}
