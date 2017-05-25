
package server;

import java.util.Calendar;
import java.util.Formatter;

public class currentTime {

	public static Formatter getCurrentTime() {
		Formatter fmt = new Formatter();
		Calendar cal = Calendar.getInstance();

		fmt = new Formatter();
		fmt.format("%tl:%tM", cal, cal);

		return fmt;

	}

}
