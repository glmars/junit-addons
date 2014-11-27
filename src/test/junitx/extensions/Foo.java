package junitx.extensions;

import java.io.Serializable;

/**
 * Contrived class used in testing the abstract interface functional
 * compliance tests.
 *
 * @version  $Revision: 1.1 $ $Date: 2003/02/05 10:27:29 $
 * @author  <a href="mailto:pholser@yahoo.com">Paul Holser</a>
 */
public final class Foo
        implements Serializable, Comparable {

    private int i;

    public Foo(int i) {
        this.i = i;
    }

    public boolean equals(Object that) {
        return (that instanceof Foo) && ((Foo) that).i == i;
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + i;

        return result;
    }

    public int compareTo(Object that) {
        Foo other = (Foo) that;
        return i - other.i;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("Foo[i=");
        buffer.append(i);
        buffer.append(']');

        return buffer.toString();
    }

    public int getI() {
        return i;
    }
}
