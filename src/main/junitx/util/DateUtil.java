package junitx.util;

import java.util.GregorianCalendar;

/**
 * @version $Revision$, $Date$
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Ritz Bossicard</a>
 */
public class DateUtil {

    static long Time(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        return calendar.getTime().getTime();
    }

}
