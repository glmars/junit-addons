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

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junitx.framework.Assert;

/**
 * Extend me in order to test a class's functional compliance with the
 * <code>equals</code> and <code>hashCode</code> contract.
 * <p>
 * Override my {@link #createInstance() createInstance} and
 * {@link #createNotEqualInstance() createNotEqualInstance} methods to
 * provide me with objects to test against.  Both methods should return
 * objects that are of the same class.
 * <p>
 * <b>WARNING</b>: Extend me only if your class overrides
 * <code>equals</code> to test for equivalence.  If your class's
 * <code>equals</code> tests for identity or preserves the behavior from
 * <code>Object</code>, I'm not interested, because I expect
 * <code>createInstance</code> to return equivalent but distinct objects.
 *
 * @see java.lang.Object#equals(Object)
 * @see java.lang.Object#hashCode()
 * @version $Revision: 1.4 $ $Date: 2004/07/07 05:44:04 $
 * @author <a href="mailto:pholser@yahoo.com">Paul Holser</a>
 */
public abstract class EqualsHashCodeTestCase
        extends TestCase {

    private Object eq1;
    private Object eq2;
    private Object eq3;
    private Object neq;
    private static final int NUM_ITERATIONS = 20;

    /**
     * Creates a new test.
     *
     * @param  name  name of the test
     */
    public EqualsHashCodeTestCase(String name) {
        super(name);
    }

    /**
     * Creates and returns an instance of the class under test.
     *
     * @return  a new instance of the class under test; each object returned
     * from this method should compare equal to each other.
     * @throws  Exception
     */
    protected abstract Object createInstance() throws Exception;

    /**
     * Creates and returns an instance of the class under test.
     *
     * @return a new instance of the class under test; each object returned
     * from this method should compare equal to each other, but not to the
     * objects returned from {@link #createInstance() createInstance}.
     * @throws Exception
     */
    protected abstract Object createNotEqualInstance() throws Exception;

    /**
     * Sets up the test fixture.
     *
     * @throws Exception
     */
    protected void setUp() throws Exception {
        super.setUp();

        eq1 = createInstance();
        eq2 = createInstance();
        eq3 = createInstance();
        neq = createNotEqualInstance();

        // We want these assertions to yield errors, not failures.
        try {
            assertNotNull("createInstance() returned null", eq1);
            assertNotNull("2nd createInstance() returned null", eq2);
            assertNotNull("3rd createInstance() returned null", eq3);
            assertNotNull("createNotEqualInstance() returned null", neq);

            Assert.assertNotSame(eq1, eq2);
            Assert.assertNotSame(eq1, eq3);
            Assert.assertNotSame(eq1, neq);
            Assert.assertNotSame(eq2, eq3);
            Assert.assertNotSame(eq2, neq);
            Assert.assertNotSame(eq3, neq);

            assertEquals(
                    "1st and 2nd equal instances of different classes",
                    eq1.getClass(),
                    eq2.getClass());
            assertEquals(
                    "1st and 3rd equal instances of different classes",
                    eq1.getClass(),
                    eq3.getClass());
            assertEquals(
                    "1st equal instance and not-equal instance of different classes",
                    eq1.getClass(),
                    neq.getClass());
        } catch (AssertionFailedError ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Tests whether <code>equals</code> holds up against a new
     * <code>Object</code> (should always be <code>false</code>).
     */
    public final void testEqualsAgainstNewObject() {
        Object o = new Object();

        Assert.assertNotEquals(eq1, o);
        Assert.assertNotEquals(eq2, o);
        Assert.assertNotEquals(eq3, o);
        Assert.assertNotEquals(neq, o);
    }

    /**
     * Tests whether <code>equals</code> holds up against <code>null</code>.
     */
    public final void testEqualsAgainstNull() {
        Assert.assertNotEquals("1st vs. null", eq1, null);
        Assert.assertNotEquals("2nd vs. null", eq2, null);
        Assert.assertNotEquals("3rd vs. null", eq3, null);
        Assert.assertNotEquals("not-equal vs. null", neq, null);
    }

    /**
     * Tests whether <code>equals</code> holds up against objects that should
     * not compare equal.
     */
    public final void testEqualsAgainstUnequalObjects() {
        Assert.assertNotEquals("1st vs. not-equal", eq1, neq);
        Assert.assertNotEquals("2nd vs. not-equal", eq2, neq);
        Assert.assertNotEquals("3rd vs. not-equal", eq3, neq);

        Assert.assertNotEquals("not-equal vs. 1st", neq, eq1);
        Assert.assertNotEquals("not-equal vs. 2nd", neq, eq2);
        Assert.assertNotEquals("not-equal vs. 3rd", neq, eq3);
    }

    /**
     * Tests whether <code>equals</code> is <em>consistent</em>.
     */
    public final void testEqualsIsConsistentAcrossInvocations() {
        for (int i = 0; i < NUM_ITERATIONS; ++i) {
            testEqualsAgainstNewObject();
            testEqualsAgainstNull();
            testEqualsAgainstUnequalObjects();
            testEqualsIsReflexive();
            testEqualsIsSymmetricAndTransitive();
        }
    }

    /**
     * Tests whether <code>equals</code> is <em>reflexive</em>.
     */
    public final void testEqualsIsReflexive() {
        assertEquals("1st equal instance", eq1, eq1);
        assertEquals("2nd equal instance", eq2, eq2);
        assertEquals("3rd equal instance", eq3, eq3);
        assertEquals("not-equal instance", neq, neq);
    }

    /**
     * Tests whether <code>equals</code> is <em>symmetric</em> and
     * <em>transitive</em>.
     */
    public final void testEqualsIsSymmetricAndTransitive() {
        assertEquals("1st vs. 2nd", eq1, eq2);
        assertEquals("2nd vs. 1st", eq2, eq1);

        assertEquals("1st vs. 3rd", eq1, eq3);
        assertEquals("3rd vs. 1st", eq3, eq1);

        assertEquals("2nd vs. 3rd", eq2, eq3);
        assertEquals("3rd vs. 2nd", eq3, eq2);
    }

    /**
     * Tests the <code>hashCode</code> contract.
     */
    public final void testHashCodeContract() {
        assertEquals("1st vs. 2nd", eq1.hashCode(), eq2.hashCode());
        assertEquals("1st vs. 3rd", eq1.hashCode(), eq3.hashCode());
        assertEquals("2nd vs. 3rd", eq2.hashCode(), eq3.hashCode());
    }

    /**
     * Tests the consistency of <code>hashCode</code>.
     */
    public final void testHashCodeIsConsistentAcrossInvocations() {
        int eq1Hash = eq1.hashCode();
        int eq2Hash = eq2.hashCode();
        int eq3Hash = eq3.hashCode();
        int neqHash = neq.hashCode();

        for (int i = 0; i < NUM_ITERATIONS; ++i) {
            assertEquals("1st equal instance", eq1Hash, eq1.hashCode());
            assertEquals("2nd equal instance", eq2Hash, eq2.hashCode());
            assertEquals("3rd equal instance", eq3Hash, eq3.hashCode());
            assertEquals("not-equal instance", neqHash, neq.hashCode());
        }
    }

}
