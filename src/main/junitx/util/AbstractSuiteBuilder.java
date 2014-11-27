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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @version $Revision: 1.6 $ $Date: 2003/05/22 02:24:15 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
abstract class AbstractSuiteBuilder {

    protected TestFilter m_filter;

    public AbstractSuiteBuilder(TestFilter filter) {
        m_filter = filter;
    }

    /**
     * Sets the <tt>filter</tt> defining the test classes to be included.
     */
    public void setFilter(TestFilter filter) {
        m_filter = filter;
    }

    /**
     * Returns <tt>true</tt> if the given <tt>path</tt> refers a test class.
     */
    protected boolean isTestClass(String path) {
        boolean temp = path.endsWith(".class") && (path.indexOf("$") < 0);
        if (m_filter == null) {
            return temp;
        } else {
            return temp && m_filter.include(path);
        }
    }

    /**
     * Populates the <tt>suite</tt> with test cases extracted from the
     * <tt>classnames</tt> list.
     */
    protected void merge(List classenames, TestSuite suite) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        for (int i = 0; i < classenames.size(); i++) {
            String classname = (String) classenames.get(i);
            Class cls = Class.forName(classname);

            if (junit.framework.TestCase.class.isAssignableFrom(cls) && ((m_filter == null) || (m_filter.include(cls)))) {
                /* Because a 'suite' method doesn't always exist in a TestCase,
                 * we need to use the try/catch so that tests can also be
                 * automatically extracted */
                try {
                    Method suiteMethod = cls.getMethod("suite", new Class[0]);
                    if (!Modifier.isPublic(suiteMethod.getModifiers())) {
                        suite.addTest(warning("Method 'suite' should be public (class " + cls.getName() + ")"));
                    } else if (!Modifier.isStatic(suiteMethod.getModifiers())) {
                        suite.addTest(warning("Method 'suite' should be static (class " + cls.getName() + ")"));
                    } else if (!Test.class.isAssignableFrom(suiteMethod.getReturnType())) {
                        suite.addTest(warning("Method 'suite' should have a Test return type (class " + cls.getName() + ")"));
                    } else if (suiteMethod.getParameterTypes().length != 0) {
                        suite.addTest(warning("Method 'suite' should have no arguments (class " + cls.getName() + ")"));
                    } else {
                        Test test = (Test) suiteMethod.invoke(null, new Class[0]);
                        suite.addTest(test);
                    }
                } catch (NoSuchMethodException e) {
                    suite.addTest(new TestSuite(cls));
                }
            }
        }
    }

    /**
     * Returns a test which will fail and log a warning message.
     */
    private static Test warning(final String message) {
        return new TestCase("warning") {
            protected void runTest() {
                fail(message);
            }
        };
    }

}