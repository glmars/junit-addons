/*
 * The JUnit-addons Software License, Version 1.0
 *     (based on the Apache Software License, Version 1.1)
 *
 * Copyright (c) 2002-2003 Vladimir R. Bossicard.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by Vladimir R.
 *        Bossicard as well as other contributors
 *        (http://junit-addons.sourceforge.net/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The name "JUnit-addons" must not be used to endorse or promote
 *    products derived from this software without prior written
 *    permission. For written permission, please contact
 *    vbossica@users.sourceforge.net.
 *
 * 5. Products derived from this software may not be called "JUnit-addons"
 *    nor may "JUnit-addons" appear in their names without prior written
 *    permission of the project managers.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ======================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals.  For more information on the JUnit-addons Project, please
 * see <http://junit-addons.sourceforge.net/>.
 */

package junitx.util;

import junit.framework.Test;
import junit.framework.TestSuite;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Extracts the testcases from a zip or jar achive.  Use the 
 * <tt>TestFilter</tt> to specify the pattern of the classes to load.
 *
 * <h4>Note</h4>
 * The <tt>jar</tt> archive is handled like a <tt>zip</tt> archive: information
 * located in the manifest (like a classpath) are ignored.
 *
 * <h4>Examples</h4>
 *
 * Returns a suite containing all classes matching the pattern "*Test.class":
 * <p>
 * <pre>
 *     ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();
 *     Test suite = builer.suite("myarchive.zip");
 * </pre>
 *
 * Returns a suite containing <tt>AllTests.class</tt> classes:<p>
 * <pre>
 *     ArchiveSuiteBuilder builder = new ArchiveSuiteBuilder();
 *     builder.setFilter(new TestFilter() {
 *
 *          public boolean include(String classpath) {
 *              return super.include(classpath) && 
 *                  SimpleTestFilter.getClassName(classpath).equals("AllTests");
 *          }
 * 
 *     });
 * 
 *     Test suite = builer.suite("myjar.jar");
 * </pre>
 *
 * @version $Revision: 1.7 $ $Date: 2003/05/11 02:29:01 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 * @author <a href="mailto:pbnaidu@users.sourceforge.net">Naidu Purushotham</a>
 */
public class ArchiveSuiteBuilder
        extends AbstractSuiteBuilder {

    /**
     * Constructs an empty <tt>ArchiveSuiteBuilder</tt> object.
     */
    public ArchiveSuiteBuilder() {
        this(null);
    }
    
    /**
     * Constructs an <tt>ArchiveSuiteBuilder</tt> object with the given filter.
     */
    public ArchiveSuiteBuilder(TestFilter filter) {
        super(filter);
    }    
    
    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from the
     * given <tt>file</tt> archive.
     */
    public Test suite(ZipFile file)
            throws Exception {
        return suite(file.getName(), file);
    }

    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from the
     * given <tt>file</tt> archive and assigns the given name to it.
     */
    public Test suite(String name, ZipFile file)
            throws Exception {
        TestSuite suite = new TestSuite(name);

        List classnames = browse(file);
        merge(classnames, suite);

        return suite;
    }

    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from an
     * archive specified by the <tt>filename</tt>.
     */
    public Test suite(String filename)
            throws Exception {
        return suite(filename, filename);
    }

    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from an
     * archive specified by the <tt>filename</tt> and assigns the given name 
     * to it.
     */
    public Test suite(String name, String filename)
            throws Exception {
        ZipFile file = new ZipFile(filename);
        Test result;

        try {
            result = suite(name, file);
        } finally {
            file.close();
        }

        return result;
    }

    /**
     * Returns the list of test classes contained in the <tt>ZipFile</tt>.
     * Method defined for testing only: no support guarantee.
     *
     * @see ArchiveSuiteBuilder#isTestClass
     */
    protected List browse(ZipFile file) {
        List result = new Vector();
        Enumeration entries = file.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (!entry.isDirectory()) {
                if (isTestClass(entry.getName())) {
                    /* we extract the classname from the path, by removing the '.class' and
                     * replacing the file separator by a '.'. */
                    String classname = entry.getName().replace('/', '.');
                    classname = classname.substring(0, classname.length() - 6);
                    result.add(classname);
                }
            }
        }
        return result;
    }

}
