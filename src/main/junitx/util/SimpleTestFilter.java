/*
 * The JUnit-addons Software License, Version 1.0
 *     (based on the Apache Software License, Version 1.1)
 *
 * Copyright (c) 2003 Vladimir R. Bossicard.  All rights reserved.
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

import java.io.File;
import java.lang.reflect.Modifier;

/**
 * Simple filter for test classes (ending with 'Test').  Also provides
 * convenient methods to extract the name and package of a class.
 * 
 * @deprecated use BasicTestFilter instead
 * 
 * @version $Revision: 1.9 $ $Date: 2004/07/07 05:37:01 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class SimpleTestFilter implements TestFilter {

    /**
     * Constructs an empty <tt>SimpleTestFilter</tt> object.
     */
    public SimpleTestFilter() {
    }

    public void setRoot(String root) {
    }

    /**
     * Returns <tt>true</tt> if:
     * <ul>
     * <li>the name of the class ends with 'Test'
     * </ul>
     */
    public boolean include(String classpath) {
        return classpath.endsWith("Test.class");
    }

    /**
     * Returns <tt>true</tt> if:
     * <ul>
     * <li>the class is not an interface 
     * <li>the class is not abstract
     * </ul>
     */
    public boolean include(Class cls) {
        int modifiers = cls.getModifiers();
        return !Modifier.isAbstract(modifiers) && !Modifier.isInterface(modifiers);
    }

    /**
     * Returns the name of a given class.
     */
    public static String getClassName(Class cls) {
        int pos = cls.getName().lastIndexOf('.');
        if (pos != -1) {
            return cls.getName().substring(pos + 1);
        } else {
            return cls.getName();
        }
    }

    /**
     * Returns the package name of a given class or an empty string if the class
     * wasn't in a package.
     */
    public static String getPackageName(Class cls) {
        int pos = cls.getName().lastIndexOf('.');
        if (pos != -1) {
            return cls.getName().substring(0, pos);
        } else {
            return "";
        }
    }

    /**
     * Returns the name of a given class.
     */
    public static String getClassName(String classpath) {
        int pos = classpath.lastIndexOf(File.separatorChar);
        if (pos > 0) {
            return classpath.substring(pos + 1, classpath.length() - 6);
        }
        return classpath.substring(0, classpath.length() - 6);
    }

    /**
     * Returns the path and package name of a given class or <tt>null</tt> if 
     * the class wasn't in a package.  Note that the patch is also included 
     * into the classpath!
     */
    public static String getPackageName(String classpath) {
        int pos = classpath.lastIndexOf(File.separatorChar);
        if (pos > 0) {
            return classpath.substring(0, pos).replace(File.separatorChar, '.');
        }
        return "";
    }

}
