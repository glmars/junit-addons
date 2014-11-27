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

import java.io.FileInputStream;
import java.util.Properties;

/**
 * <p>The <code>PropertyManager</code> class represents a persistent set of
 * properties loaded from a standard Property file.</p>
 *
 * <h4>Usage</h4>
 * <p>The location of the property file loaded by the <code>PropertyManager</code>
 * class is defined by the <code>PropertyManager.file</code> JVM environment
 * variable. For example, to initialize the <code>PropertyManager</code>
 * with a file located at <code>c:\project\props.properties</code>:</p>
 *
 * <pre>
 *    java -DPropertyManager.file=c:\project\props.properties junit.textui.TestRunner AllTests
 * </pre>
 *
 * <h4>Format of the file</h4>
 * <pre>
 *    propname=propvalue
 * </pre>
 *
 * @version $Revision: 1.5 $ $Date: 2003/05/03 03:42:07 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class PropertyManager {

    private static Properties props = null;

    static {
        try {
            props = new Properties();
            props.load(new FileInputStream(System.getProperty("PropertyManager.file")));
        } catch (Exception e) {
            // do nothing
        }
    }

    /**
     * Don't let anyone instantiate this class
     */
    private PropertyManager() {
    }

    /**
	 * Maps the specified key to the specified value in this property list.
     * 
     * @return the previous value of the specified key in this property list, or
     * null if it did not have one.
     * @param key the hashtable key.
     * @param value a value.
     */
    public static Object setProperty(String key,
                                       String value) 
            throws NullPointerException {
        if (props == null) {
            return null;
        }
        return props.setProperty(key, value);
    }
                                     
    /**
     * Searches for the property with the specified key in this property list.
     * The method returns <code>null</code> if the property is not found.
     *
     * @param key the hashtable key.
     * @return the value in this property list with the specified key value.
     * @see #getProperty(String, String)
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    /**
     * Searches for the property with the specified key in this property list.
     * The method returns the default value argument if the property is not
     * found.
     *
     * @param key the hashtable key.
     * @param defaultValue a default value.
     * @return the value in this property list with the specified key value.
     */
    public static String getProperty(String key,
                                     String defaultValue) {
        if (props == null) {
            return null;
        }
        return props.getProperty(key, defaultValue);
    }

}
