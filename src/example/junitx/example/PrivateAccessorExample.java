package junitx.example;

/**
 * @version $Revision: 1.4 $ $Date: 2003/05/04 20:35:58 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class PrivateAccessorExample {

    private int intvalue;
    protected Boolean boolvalue;
    private static double doublevalue;

    /* so that PMD doesn't complain anymore */
    public void dummyPMD() {
        getIntValue();
        setIntValue(2);
    }

    private int getIntValue() {
        return this.intvalue;
    }

    private void setIntValue(int value) {
        this.intvalue = value;
    }

    protected Boolean getBoolValue() {
        return this.boolvalue;
    }

    protected void setBoolValue(Boolean value) {
        this.boolvalue = value;
    }

    private static void setDoubleValue(double value) {
        doublevalue = value;
    }

    private static double getDoubleValue() {
        return doublevalue;
    }

    public void usePrivateMethodsSoThatPmdDontComplainAnymore() {
        setDoubleValue(1.0d);
        getDoubleValue();
    }
    
}
