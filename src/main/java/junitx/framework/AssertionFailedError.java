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

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Similar to the official class but supports chained exceptions.
 * 
 * <h4>Usage</h4>
 * To use this new class, you'll have to invoke the <tt>fail</tt> method of the
 * <tt>junitx.framework.Assert</tt> class:
 *
 * <pre>
 *    junitx.framework.Assert.fail(exception);
 * </pre>
 * 
 * @version  $Revision: 1.2 $ $Date: 2004/07/06 03:56:16 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class AssertionFailedError extends junit.framework.AssertionFailedError {

    private Throwable m_cause;

    /**
     * Constructs a new throwable with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
     * contains the class and detail message of <tt>cause</tt>).<p>
     * 
     * @param cause the cause (which is saved for later retrieval by the
     * getCause() method). (A null value is permitted, and indicates that the
     * cause is nonexistent or unknown.)
     */
    public AssertionFailedError(Throwable cause) {
        this(cause == null ? null : cause.toString(), cause);
    }

    /**
     * Constructs a new throwable with the specified detail message and cause.
     *
     * Note that the detail message associated with cause is not automatically
     * incorporated in this throwable's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by
     * the getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the
     * getCause() method). (A null value is permitted, and indicates that the
     * cause is nonexistent or unknown.)
     */
    public AssertionFailedError(String message,
                                Throwable cause) {
        super(message);
        m_cause = cause;
    }

    /**
     * Returns  the cause of this throwable or null if the cause is nonexistent
     * or unknown. (The cause is the throwable that caused this throwable to get
     * thrown.)
     */
    public Throwable getCause() {
        return m_cause;
    }

    /**
     * Prints this throwable and its backtrace to the standard error stream. This
     * method prints a stack trace for this Throwable object on the error output
     * stream that is the value of the field System.err. The first line of output
     * contains the result of the toString() method for this object. Remaining lines
     * represent data previously recorded by the method fillInStackTrace().
     * 
     * @see java.lang.Throwable#printStackTrace()
     */
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * Prints this throwable and its backtrace to the specified print stream.
     * s - PrintStream to use for output
     * @see java.lang.Throwable#printStackTrace(PrintStream)
     */
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        if (m_cause != null) {
            s.print("Caused by: ");
            m_cause.printStackTrace(s);
        }
    }

    /**
     * Prints this throwable and its backtrace to the specified print writer.
     *
     * @param s <tt>PrintWriter</tt> to use for output
     * @see java.lang.Throwable#printStackTrace(PrintWriter)
     */
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        if (m_cause != null) {
            s.print("Caused by: ");
            m_cause.printStackTrace(s);
        }
    }

}
