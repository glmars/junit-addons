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

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

/**
 * A set of assert methods specially targetted to asserting files.
 *
 * @version $Revision: 1.9 $ $Date: 2003/05/03 03:50:36 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 * @author <a href="mailto:dchalker@users.sourceforge.net">Dean Chalker</a>
 */
public class FileAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private FileAssert() {
    }

    /**
     * Asserts that two files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.<p>
     *
     * <b>Note</b>: This assertion method rely on the standard
     * <tt>junit.framework.Assert(String expected, String actual)</tt> method
     * to compare the lines of the files.  JUnit > 3.8 provides a nicer way to
     * display differences between two strings but since only lines are
     * compared (and not entire paragraphs) you can still use JUnit 3.7.
     */
	public static void assertTextEquals(String message,
									File expected,
									File actual,
									String charsetName) {
        Assert.assertNotNull(expected);
        Assert.assertNotNull(actual);

        Assert.assertTrue("File does not exist [" + expected.getAbsolutePath() + "]", expected.exists());
        Assert.assertTrue("File does not exist [" + actual.getAbsolutePath() + "]", actual.exists());

        Assert.assertTrue("Expected file not readable", expected.canRead());
        Assert.assertTrue("Actual file not readable", actual.canRead());

        FileInputStream eis = null;
        FileInputStream ais = null;

        try {
            try {
                eis = new FileInputStream(expected);
                ais = new FileInputStream(actual);
    
                BufferedReader expData = new BufferedReader(new InputStreamReader(eis, createCharsetDecoder(charsetName)));
                BufferedReader actData = new BufferedReader(new InputStreamReader(ais, createCharsetDecoder(charsetName)));
    
                Assert.assertNotNull(message, expData);
                Assert.assertNotNull(message, actData);

				assertTextEquals(message, expData, actData);
            } finally {
                eis.close();
                ais.close();
            }
        } catch (IOException e) {
            throw new ExecutionError(e);
        }
    }

    /**
     * Asserts that two files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     */
    public static void assertTextEquals(File expected,
                                    File actual,
									String charsetName) {
		assertTextEquals(null, expected, actual, charsetName);
	}

    /**
     * Asserts that two files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     */
    public static void assertTextEquals(File expected,
                                    File actual) {
		assertTextEquals(null, expected, actual, null);
	}

	/**
	 * Asserts that two files are equal. Throws an
	 * <tt>AssertionFailedError</tt> if they are not.<p>
	 *
	 * @deprecated Use {@link #assertTextEquals assertTextEquals} instead
	 */
	public static void assertEquals(String message,
									File expected,
									File actual) {
		assertTextEquals(message, expected, actual, null);
	}

	/**
     * Asserts that two files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
	 *
	 * @deprecated Use {@link #assertTextEquals assertTextEquals} instead
     */
    public static void assertEquals(File expected,
                                    File actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * <b>Testing only</b> Asserts that two readers are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     */
    protected static void assertTextEquals(String message,
                                       Reader expected,
                                       Reader actual) {
        Assert.assertNotNull(message, expected);
        Assert.assertNotNull(message, actual);

        LineNumberReader expReader = new LineNumberReader(expected);
        LineNumberReader actReader = new LineNumberReader(actual);

        Assert.assertNotNull(message, expReader);
        Assert.assertNotNull(message, actReader);

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        String expLine;
        String actLine;
        try {
            while (true) {
                if (!expReader.ready() && !actReader.ready()) {
                    return;
                }

                expLine = expReader.readLine();
                actLine = actReader.readLine();

                if (expLine == null && actLine == null) {
                    return;
                }

                int line = expReader.getLineNumber() + 1;

                if (expReader.ready()) {
                    if (actReader.ready()) {
                        Assert.assertEquals(formatted + "Line [" + line + "]", expLine, actLine);
                    } else {
                        Assert.fail(formatted + "Line [" + line + "] expected <" + expLine + "> but was <EOF>"); 
                    }
                } else {
                    if (actReader.ready()) {
                        Assert.fail(formatted + "Line [" + line + "] expected <EOF> but was <" + actLine + ">");
                    } else {
                        Assert.assertEquals(formatted + "Line [" + line + "]", expLine, actLine);
                    }
                }
            }
        } catch (IOException e) {
            throw new ExecutionError(e);
        }
    }

    /**
     * Asserts that two binary files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.<p>
     */
    public static void assertBinaryEquals(File expected,
                                          File actual) {
        assertBinaryEquals(null, expected, actual);
    }

    /**
     * Asserts that two binary files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.<p>
     */
    public static void assertBinaryEquals(String message,
                                          File expected,
                                          File actual) {
        Assert.assertNotNull(message, expected);
        Assert.assertNotNull(message, actual);

        Assert.assertTrue("File does not exist [" + expected.getAbsolutePath() + "]", expected.exists());
        Assert.assertTrue("File does not exist [" + actual.getAbsolutePath() + "]", actual.exists());

        Assert.assertTrue("Expected file not readable", expected.canRead());
        Assert.assertTrue("Actual file not readable", actual.canRead());

        FileInputStream eis = null;
        FileInputStream ais = null;

        try {
            try {
                eis = new FileInputStream(expected);
                ais = new FileInputStream(actual);
    
                Assert.assertNotNull(message, expected);
                Assert.assertNotNull(message, actual);
    
                byte[] expBuff = new byte[8192];
                byte[] actBuff = new byte[8192];
    
                long pos = 0;
                while (true) {
                    int expLength = eis.read(expBuff, 0, 8192);
                    int actLength = ais.read(actBuff, 0, 8192);
    
                    if (expLength < actLength) {
                        Assert.fail("actual file is longer");
                    }
                    if (expLength > actLength) {
                        Assert.fail("actual file is shorter");
                    }
    
                    if (expLength == 0) {
                        return;
                    }
    
                    for (int i = 0; i < expBuff.length; ++i) {
                        if (expBuff[i] != actBuff[i]) {
                            String formatted = "";
                            if (message != null) {
                                formatted = message + " ";
                            }
                            Assert.fail(formatted + "files differ at byte " + (pos + i + 1));  // i starts at 0 so +1
                        }
                    }
    
                    pos += expBuff.length;
                    return;
                }
            } finally {
                eis.close();
                ais.close();
            }
        } catch (IOException e) {
            throw new ExecutionError(e);
        }
    }

	private static CharsetDecoder createCharsetDecoder(String charsetName) {
		Charset charset = (charsetName != null) ? Charset.forName(charsetName) : Charset.defaultCharset(); 
		return charset.newDecoder()
				.onMalformedInput(CodingErrorAction.REPORT)
				.onUnmappableCharacter(CodingErrorAction.REPORT);
	}
}
