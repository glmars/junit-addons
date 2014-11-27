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
import junitx.framework.ComparableAssert;

/**
 * Extend me in order to test a class's functional compliance with the
 * {@link java.lang.Comparable Comparable} interface.
 * <p>
 * Override my {@link #createLessInstance() createLessInstance},
 * {@link #createEqualInstance() createEqualInstance}, and
 * {@link #createGreaterInstance() createGreaterInstance} methods to
 * provide me with objects to test against.  These methods should return
 * objects that are of the same class.
 *
 * @see java.lang.Comparable
 * @version $Revision: 1.4 $ $Date: 2003/05/03 03:41:12 $
 * @author <a href="mailto:pholser@yahoo.com">Paul Holser</a>
 */
public abstract class ComparabilityTestCase
        extends TestCase {

    private Comparable less;
    private Comparable equal1;
    private Comparable equal2;
    private Comparable greater;

    /**
     * Creates a new test.
     *
     * @param  name  name of the test
     */
    public ComparabilityTestCase(String name) {
        super(name);
    }

    /**
     * Creates and returns an instance of the class under test.
     *
     * @return  a new instance of the class under test; each object returned
     * from this method should compare less than the objects returned from
     * {@link #createEqualInstance() createEqualInstance()}
     * @throws  Exception
     */
    protected abstract Comparable createLessInstance()
            throws Exception;

    /**
     * Creates and returns an instance of the class under test.
     *
     * @return  a new instance of the class under test; each object returned
     * from this method should compare equal to each other
     * @throws  Exception
     */
    protected abstract Comparable createEqualInstance()
            throws Exception;

    /**
     * Creates and returns an instance of the class under test.
     *
     * @return  a new instance of the class under test; each object returned
     * from this method should compare greater than the objects returned from
     * {@link #createEqualInstance() createEqualInstance()}
     * @throws  Exception
     */
    protected abstract Comparable createGreaterInstance()
            throws Exception;

    /**
     * Sets up the test fixture.
     *
     * @throws  Exception
     */
    protected void setUp()
            throws Exception {
        super.setUp();

        less = createLessInstance();
        equal1 = createEqualInstance();
        equal2 = createEqualInstance();
        greater = createGreaterInstance();

        // We want these assertions to yield errors, not failures.
        try {
            assertNotNull("createLessInstance() returned null", less);
            assertNotNull("createEqualInstance() returned null", equal1);
            assertNotNull("2nd createEqualInstance() returned null", equal2);
            assertNotNull("createGreaterInstance() returned null", greater);

            assertEquals("less and equal1 of different classes",
                    less.getClass(),
                    equal1.getClass());
            assertEquals("less and equal2 of different classes",
                    less.getClass(),
                    equal2.getClass());
            assertEquals("less and greater of different classes",
                    less.getClass(),
                    greater.getClass());

            checkForEquality(equal1, equal2);
        } catch (AssertionFailedError ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Override as a no-op if you do not require that
     * {@link #createEqualInstance() createEqualInstance()} return distinct but
     * equivalent objects.
     */
    protected void checkForEquality(Comparable c1,
                                      Comparable c2) {
        Assert.assertNotSame("1st equal instance same as 2nd", c1, c2);
        assertEquals("1st equal not equal to 2nd", c1, c2);
    }

    /**
     * Tests whether <code>sgn(x.compareTo(y)) == -sgn(y.compareTo(x))</code>
     * for all <code>x</code> and <code>y</code> given to this test.
     */
    public final void testForReverseSigns() {
        assertEquals(
                "less vs. equal1",
                sgn(less.compareTo(equal1)),
                -sgn(equal1.compareTo(less)));
        assertEquals(
                "less vs. equal2",
                sgn(less.compareTo(equal2)),
                -sgn(equal2.compareTo(less)));
        assertEquals(
                "less vs. greater",
                sgn(less.compareTo(greater)),
                -sgn(greater.compareTo(less)));
        assertEquals(
                "equal1 vs. equal2",
                sgn(equal1.compareTo(equal2)),
                -sgn(equal2.compareTo(equal1)));
        assertEquals(
                "equal1 vs. greater",
                sgn(equal1.compareTo(greater)),
                -sgn(greater.compareTo(equal1)));
        assertEquals(
                "equal2 vs. greater",
                sgn(equal2.compareTo(greater)),
                -sgn(greater.compareTo(equal2)));
    }

    /**
     * Tests whether <code>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</code>
     * for all <code>z</code> when <code>x.compareTo(y) == 0</code>.
     */
    public final void testForSameSigns() {
        assertEquals(
                "equal vs. less",
                sgn(equal1.compareTo(less)),
                sgn(equal2.compareTo(less)));
        assertEquals(
                "equal vs. greater",
                sgn(equal1.compareTo(greater)),
                sgn(equal2.compareTo(greater)));
    }

    /**
     * Tests for sensible return values from the class under test's
     * <code>compareTo</code> method.  Doing so effectively tests the
     * transitivity of <code>compareTo</code> also--
     * <code>(x.compareTo(y)>0 && y.compareTo(z)>0)</code> implies
     * <code>x.compareTo(z)>0</code>.
     */
    public final void testReturnValues() {
        ComparableAssert.assertLesser(equal1, less);
        ComparableAssert.assertLesser(equal2, less);
        ComparableAssert.assertGreater(less, greater);
        ComparableAssert.assertEquals(equal1, equal2);
        ComparableAssert.assertGreater(equal1, greater);
        ComparableAssert.assertGreater(equal2, greater);
    }

    /**
     * Tests whether <code>compareTo</code> throws a ClassCastException when
     * appropriate.
     */
    public final void testForClassCastException() throws Exception {
        try {
            less.compareTo(new Object());
        } catch (ClassCastException success) {
            return;
        }
        fail("should have thrown ClassCastException");
    }

    private int sgn(int x) {
        return (x == 0) ? 0 : (x / Math.abs(x));
    }

}
