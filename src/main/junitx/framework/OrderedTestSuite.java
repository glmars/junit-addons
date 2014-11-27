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

package junitx.framework;

import junit.framework.Test;

/**
 * Constructs an alphabetically ordered suite of tests.<p>
 *
 * <b>Warning</b>: if you are tempted to use this class, think twice before
 * doing it. Ideally the tests should be independant from one another so first
 * try to refactor them and use the <tt>TestSetup</tt> class to initialize them.
 * But if you still want to use this class... it's up to you.
 *
 * @version $Revision: 1.5 $ $Date: 2003/03/30 19:02:57 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class OrderedTestSuite
        extends TestSuite {

    /**
     * Creates an <tt>OrderedTestSuite</tt> with no name.
     */
    public OrderedTestSuite() {
        super();
    }

    /**
     * Constructs a <tt>OrderedTestSuite</tt> from the given class. Adds all
     * the methods starting with "test" as test cases to the suite.<p>
     * The method calls <tt>addTest</tt> for each insertion of test methods
     * found so the sorting is done for each method, and not at the end.
     */
    public OrderedTestSuite(final Class theClass) {
        super(theClass);
    }

    /**
     * Creates an <tt>OrderedTestSuite</tt> with the given name.
     */
    public OrderedTestSuite(String name) {
        super(name);
    }

    /**
     * Adds a test to the suite. The elements are sorted after beeing inserted
     * into the vector.
     */
    public void addTest(Test test) {
        fTests.add(test);
        sortTests(0, fTests.size() - 1);
    }

    protected void sortTests(int left, int right) {
        int oleft = left;
        int oright = right;
        String mid = ((Test) this.fTests.get((left + right) / 2)).toString();
        do {
            while ((((Test) this.fTests.get(left)).toString()).compareTo(mid) < 0) {
                left++;
            }
            while (mid.compareTo(((Test) this.fTests.get(right)).toString()) < 0) {
                right--;
            }
            if (left <= right) {
                Object tmp = this.fTests.get(left);
                this.fTests.set(left, this.fTests.get(right));
                this.fTests.set(right, tmp);

                left++;
                right--;
            }
        } while (left <= right);

        if (oleft < right) {
            sortTests(oleft, right);
        }
        if (left < oright) {
            sortTests(left, oright);
        }
    }

}
