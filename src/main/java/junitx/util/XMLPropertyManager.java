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

import org.jaxen.jdom.JDOMXPath;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.FileInputStream;
import java.util.List;

/**
 * <p>The <code>XMLPropertyManager</code> class represents a persistent set of
 * properties loaded from an XML property file.</p>
 *
 * <h4>Usage</h4>
 * <p>The location of the property file loaded by the <code>XMLPropertyManager</code>
 * class is defined by the <code>XMLPropertyManager.file</code> JVM environment
 * variable. For example, to initialize the <code>XMLPropertyManager</code>
 * with a file located at <code>c:\project\props.xml</code>:</p>
 *
 * <pre>
 *    java -DXMLPropertyManager.file=c:\project\props.xml junit.textui.TestRunner AllTests
 * </pre>
 *
 * <h4>Format of the file</h4>
 * <pre>
 *    &lt;?xml version="1.0"?>
 *    &lt;properties>
 *        &lt;property name="propname" value="propvalue" />
 *    &lt;/properties>
 * </pre>
 *
 * <h4>Notes</h4>
 * <ul>
 * <li>Nested properties are not supported</li>
 * </ul>
 *
 * <h4>Required libraries</h4>
 * <ul>
 * <li>jdom.jar (available at <a href="http://jdom.org">jdom.org</a>)</li>
 * <li>jaxen.jar (available at <a href="http://jaxen.org">jaxen.org</a>)</li>
 * <li>saxpath.jar (available at <a href="http://saxpath.org">saxpath.org</a>)</li>
 * <li>xercesImpl.jar [ requires also xmlParserAPIs.jar as of Xerces version 2.1.0 ] (available at <a href="http://xml.apache.org">xml.apache.org</a>)</li>
 * </ul>
 *
 * @version $Revision: 1.4 $ $Date: 2003/02/06 20:43:55 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class XMLPropertyManager {

    private static Document props;

    static {
        try {
            String filename = System.getProperty("XMLPropertyManager.file");
            FileInputStream is = new FileInputStream(filename);
            props = new SAXBuilder(false).build(is);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Don't let anyone instantiate this class
     */
    private XMLPropertyManager() {
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

        String value = null;
        try {
            JDOMXPath path = new JDOMXPath("/properties/property[@name = \"" + key + "\"]");
            List res = path.selectNodes(props);
            if (res != null && res.size() != 0) {
                value = ((Element) res.get(0)).getAttribute("value").getValue();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return value == null ? defaultValue : value;
    }

}
