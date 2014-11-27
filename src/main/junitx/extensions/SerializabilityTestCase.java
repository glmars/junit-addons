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

package junitx.extensions;

import java.io.Serializable;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junitx.util.ObjectUtil;

/**
 * Extend me in order to test the serializability of a class.  Override my
 * {@link #createInstance() createInstance} methods to
 * provide me with an object to test against.  The object's class must
 * implement {@link java.io.Serializable Serializable}.
 *
 * @see java.io.Serializable
 * @version $Revision: 1.3 $ $Date: 2003/05/08 02:46:37 $
 * @author <a href="mailto:pholser@yahoo.com">Paul Holser</a>
 */
public abstract class SerializabilityTestCase
        extends TestCase {

    private Serializable obj;

    /**
     * Creates a new test.
     *
     * @param  name  name of the test
     */
    public SerializabilityTestCase(String name) {
        super(name);
    }

    /**
     * Creates and returns an instance of the class under test.
     *
     * @return  a new instance of the class under test
     * @throws  Exception
     */
    protected abstract Serializable createInstance() throws Exception;

    /**
     * Sets up the test fixture.
     *
     * @throws  Exception
     */
    protected void setUp()
            throws Exception {
        super.setUp();

        obj = createInstance();

        // We want these assertions to yield errors, not failures.
        try {
            assertNotNull("createInstance() returned null", obj);
        } catch (AssertionFailedError ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Verifies that an instance of the class under test can be serialized and
     * deserialized without error.
     */
    public final void testSerializability()
            throws Exception {
        byte[] serial = ObjectUtil.serialize(obj);
        Serializable deserial = (Serializable) ObjectUtil.deserialize(serial);

        checkThawedObject(obj, deserial);
    }

    /**
     * Template method--override this to perform checks on the deserialized
     * form of the object serialized in {@link #testSerializability}.  If not
     * overridden, this asserts that the pre-serialization and deserialized
     * forms of the object compare equal via
     * {@link java.lang.Object#equals(Object) equals}.
     *
     * @param  expected  the pre-serialization form of the object
     * @param  actual  the deserialized form of the object
     */
    protected void checkThawedObject(Serializable expected,
                                     Serializable actual)
            throws Exception {
        assertEquals("thawed object comparison", expected, actual);
    }

}
