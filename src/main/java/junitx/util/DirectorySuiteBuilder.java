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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Extracts the testcases from a directory.  Use the <tt>TestFilter</tt> to 
 * specify the pattern of the classes to load.
 *
 * <h4>Example</h4>
 *
 * Returns a suite containing all classes matching the pattern "*Test.
 * class" from the directory <tt>/home/project/myproject/tests</tt>:<p>
 * <pre>
 *     DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
 *     Test suite = builer.suite("/home/project/myproject/tests");
 * </pre>
 *
 * Returns a suite containing all tests from files starting with "Sample" and 
 * ending with "Test".
 * 
 * <tt>/home/project/myproject/tests</tt><p>
 * <pre>
 *     DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
 *     builder.setFilter(new TestFilter() {
 *
 *          public boolean include(String classpath) {
 *              return super.include(classpath) && 
 *                  SimpleTestFilter.getClassName(classpath).startsWith("Sample");
 *          }
 * 
 *     });
 *
 *     Test suite = builder.suite("/home/project/myproject/tests");
 * </pre>
 *
 * @version $Revision: 1.9 $ $Date: 2003/05/22 03:06:38 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 * @author <a href="mailto:pbnaidu@users.sourceforge.net">Naidu Purushotham</a>
 */
public class DirectorySuiteBuilder
        extends AbstractSuiteBuilder {

    static final int SUFFIX_LENGTH = ".class".length();

    /**
     * Constructs an empty <tt>DirectorySuiteBuilder</tt> object.
     */
    public DirectorySuiteBuilder() {
        this(null);
    }
    
    /**
     * Constructs an <tt>DirectorySuiteBuilder</tt> object with the given filter.
     */
    public DirectorySuiteBuilder(TestFilter filter) {
        super(filter);
    }    
    
    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from the
     * given <tt>directory</tt>.
     */
    public Test suite(File directory)
            throws Exception {
        return suite(directory.getName(), directory);
    }

    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from the
     * given <tt>directory</tt> and assignes the given name to it.
     */
    public Test suite(String name, File directory)
            throws Exception {
        TestSuite suite = new TestSuite(name);

        List classnames = browse(directory);
        merge(classnames, suite);

        return suite;
    }

    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from the
     * directory of the given <tt>directoryName</tt>.
     */
    public Test suite(String directoryName)
            throws Exception {
        return suite(directoryName, directoryName);
    }

    /**
     * Constructs a <tt>TestSuite</tt> by extracting all test classes from the
     * directory of the given <tt>directoryName</tt> and assigns the given name
     * to it.
     */
    public Test suite(String name, String directoryName)
            throws Exception {
        return suite(name, new File(directoryName));
    }

    /**
     * Returns the list of test classes contained in the <tt>directory</tt>.
     * Method defined for testing only: no support guarantee.
     *
     * @see DirectorySuiteBuilder#isTestClass
     */
    protected List browse(File directory)
            throws Exception {
        List result = new Vector();

        if (m_filter != null) {
            m_filter.setRoot(directory.getPath());
        }

        gatherFiles(directory, result);

        return result;
    }

    private void gatherFiles(File root,
                            List result)
            throws IOException {
        String path = root.getCanonicalPath();
        gather(root, path.length() + 1, result);
    }

    private void gather(File root,
                        int prefix,
                        List result)
            throws IOException {
        File[] files = root.listFiles();
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                if (file.canRead()) {
                    gather(file, prefix, result);
                }
            } else {
                if (isTestClass(file.getPath())) {
                    result.add(getClassName(file.getCanonicalPath().substring(prefix)));
                }
            }
        }
    }

    private String getClassName(String filename) {
        return filename.replace(File.separatorChar, '.').substring(0, filename.length() - SUFFIX_LENGTH);
    }

}
