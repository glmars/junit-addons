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

package junitx.ant;

import junitx.tool.TestClassValidator;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

import java.io.File;
import java.util.List;
import java.util.Vector;

/**
 * Validate a JUnit <tt>TestCase</tt> or <tt>TestSuite</tt> and outline
 * potential errors in the definition of the classes.
 *
 * <h4>Usage</h4>
 * <pre>
 *  &lt;taskdef name="TestValidator"
 *              classname="junitx.ant.TestClassValidatorTask"
 *              classpath="junit-addons-3.7.x.jar" />
 *
 *  &lt;validator verbose="off">
 *    &lt;classpath>
 *      &lt;pathelement location="classes" />
 *      &lt;fileset dir="lib">
 *        &lt;include name="\*\*.jar"/>
 *        &lt;include name="\*\*.zip"/>
 *      &lt;/fileset>
 *    &lt;/classpath>
 *    &lt;fileset dir="classes">
 *      &lt;include name="*-/*Suite.class"/>
 *      &lt;include name="*-/*Test.class"/>
 *    &lt;/fileset>
 *  &lt;/validator>
 * </pre>
 *
 * @version $Revision: 1.8 $ $Date: 2003/02/06 20:43:52 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class TestClassValidatorTask
        extends Task {

    private int verbosity = Project.MSG_VERBOSE;
    private boolean quiet = false;
    private boolean failonerror = true;

    private AntClassLoader loader;
    private Path classpath;
    private List filesets = new Vector();

    private TestClassValidator.ClassValidator validator;

    /**
     * Called by the project to let the task do it's work.
     *
     * @throws BuildException if someting goes wrong with the build
     */
    public void execute()
            throws BuildException {
        this.validator = new TestClassValidator.DefaultClassValidator();
        this.validator.setListener(new TestClassValidator.ClassValidatorListener() {
            public void info(String message) {
                log("INFO> " + message, verbosity);
                System.out.println("INFO> " + message);
            }

            public void warning(String message) {
                log("WARNING> " + message, verbosity);
                System.out.println("WARNING> " + message);
            }

            public void error(String message) {
                log("ERROR> " + message, verbosity);
                System.out.println("ERROR> " + message);
            }
        });


        if (classpath != null) {
            classpath.setProject(project);
            this.loader = new AntClassLoader(project, classpath);
        }

        log(TestClassValidator.BANNER, Project.MSG_VERBOSE);
        System.out.println(TestClassValidator.BANNER);
        int count = 0;
        for (int i = 0; i < filesets.size(); i++) {
            FileSet fs = (FileSet) filesets.get(i);

            try {
                DirectoryScanner ds = fs.getDirectoryScanner(project);
                ds.scan();

                String[] files = ds.getIncludedFiles();

                for (int k = 0; k < files.length; k++) {
                    String pathname = files[k];
                    if (pathname.endsWith(".class")) {
                        String classname = pathname.substring(0, pathname.length() - ".class".length()).replace(File.separatorChar, '.');
                        processFile(classname);
                    }
                }
                count += files.length;
            } catch (BuildException e) {
                if (failonerror) {
                    throw e;
                } else {
                    log(e.getMessage(), quiet ? Project.MSG_VERBOSE : Project.MSG_WARN);
                }
            } catch (ClassNotFoundException e) {
                if (failonerror) {
                    throw new BuildException(e);
                } else {
                    log(e.getMessage(), quiet ? Project.MSG_VERBOSE : Project.MSG_WARN);
                }
            }
            log("Number of classes: " + count, Project.MSG_VERBOSE);
            System.out.println("Number of classes: " + count);
        }
    }

    /**
     * Set the classpath to be used for this validation.
     */
    public void setClasspath(Path classpath) {
        createClasspath().append(classpath);
    }

    /**
     * Creates a nested classpath element
     */
    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(project);
        }
        return this.classpath.createPath();
    }

    /**
     * Adds a reference to a CLASSPATH defined elsewhere.
     */
    public void setClasspathRef(Reference r) {
        createClasspath().setRefid(r);
    }

    /**
     * Add a new fileset instance to this task. Whatever the fileset is,
     * only filename that are <tt>.java</tt> or <tt>.class</tt> will be
     * considered as 'candidates'.
     *
     * @param set the new fileset containing the rules to get the testcases.
     */
    public void addFileset(FileSet set) {
        filesets.add(set);
    }

    /**
     * Used to force listing of all names of processed files.
     *
     * @param verbose "true" or "on"
     */
    public void setVerbose(boolean verbose) {
        if (verbose) {
            this.verbosity = Project.MSG_INFO;
        } else {
            this.verbosity = Project.MSG_VERBOSE;
        }
    }

    private void processFile(String classname)
            throws ClassNotFoundException {
        log("processing " + classname, verbosity);

        Class cls;
        if (this.loader != null) {
            cls = this.loader.loadClass(classname);
        } else {
            ClassLoader l = this.getClass().getClassLoader();
            // Can return null to represent the bootstrap class loader.
            // see API docs of Class.getClassLoader.
            if (l != null) {
                cls = l.loadClass(classname);
            } else {
                cls = Class.forName(classname);
            }
        }
        this.validator.validate(cls);
    }

}
