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

import java.util.Hashtable;
import java.util.Map;

/**
 * Utility class that manages resources and allows separate tests to share them.
 *
 * <h4>Example</h4>
 *
 * The AllTests class registers all external resourses by calling the
 * <tt>addResource</tt> method of the ResourceManager:<p>
 *
 * <pre>
 *    public class AllTests {
 *
 *       public static Test suite() {
 *          TestSuite suite = new TestSuite();
 *          suite.addTestSuite(MyClassTest.class);
 *
 *          return new TestSetup(suite) {
 *              public void setUp()
 *                    throws Exception {
 *                 ResourceManager.addResource("RESOURCE_ID", new Object());
 *              }
 *          };
 *       }
 *    }
 * </pre>
 *
 * The test class simply gets the resource from the ResourceManager during the
 * set up process:<p>
 *
 * <pre>
 *    public class MyClassTest extends TestCase {
 *
 *       private Object res;
 *
 *       public void setUp() {
 *          res = ResourceManager.getResource("RESOURCE_ID");
 *       }
 *
 *       public void testXYZ() {
 *          res.XYZ();
 *       }
 *    }
 * </pre>
 *
 * To share a resource whithin the same <tt>TestCase</tt>:
 * 
 * <pre>
 *    public class MyClassTest extends TestCase {
 * 
 *       private Object res;
 *
 *       public void setUp() {
 *          if (!ResourceManager.contains("RESOURCE_ID")) {
 *             ResourceManager.addResource("RESOURCE_ID", new Object());
 *          }
 *          res = ResourceManager.getResource("RESOURCE_ID");
 *       }
 *
 *       public void testXYZ() {
 *          res.XYZ();
 *       }
 *    }
 * </pre>
 *
 * @version $Revision: 1.4 $ $Date: 2003/03/23 01:25:24 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ResourceManager {

    private static Map resources = new Hashtable();

    /**
     * Don't let anyone have access to this constructor.
     */
    private ResourceManager() {
    }

    /**
     * Associates the resource to the key in the manager. Neither the key nor
     * the value can be null.  The value can be retrieved by calling the
     * getResource method with a key that is equal to the original key.
     *
     * @param key the resource key.
     * @param value the resource.
     * @throws IllegalArgumentException if the key is already used.
     */
    public static void addResource(String key,
                                   Object value)
            throws IllegalArgumentException {
        if (resources.containsKey(key)) {
            throw new IllegalArgumentException("Resource with key '" + key + "' already exists");
        }
        resources.put(key, value);
    }

    /**
     * Returns the resource to which the specified key is mapped in this
     * ResourceManager or <tt>null</tt> if the resource was not found.
     *
     * @param key a key in the ResourceManager.
     * @return Object the resource to which the key is mapped in this hashtable.
     * @throws NullPointerException if the key is null.
     */
    public static Object getResource(String key)
            throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("Invalid key <null>");
        }
        return resources.get(key);
    }

    /**
     * Returns <tt>true</tt> if the ResourceManager contains a resource with
     * the specified key.
     */
    public static boolean containsResource(String key) {
        return resources.containsKey(key);
    }
    
    /**
     * Removes the key (and its corresponding resource) from this
     * ResourceManager.
     *
     * @param key the key that needs to be removed.
     */
    public static void removeResource(String key) {
        resources.remove(key);
    }

}
