package startup.rest.clock;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClockBean {
	
	public ClockBean() {	
	super();
}

private Date getCurrentTime() {
	Calendar cal = Calendar.getInstance();
	return cal.getTime();
}

private DateFormat getDateFormat( String format, Locale locale ) throws DateTimeFormatException {
	DateFormat df = null;
	String fmt = null;
	if ( format == null || format.length() < 1 ) {
		 throw new DateTimeFormatException("No date-time format specified");
	} else
		fmt = format.toUpperCase().trim();
	if (fmt.equals("FULL")) {
			df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);		
	} else if (fmt.equals("LONG")) {
			df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
	} else if (fmt.equals("MEDIUM")) {
			df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);		
	} else if (fmt.equals("SHORT")) {
			df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
	} else {
			throw new DateTimeFormatException( format + " is not a recognized date-time format");
	} 
	return df;
}

private String formatTime(Date time, String format, Locale locale) throws DateTimeFormatException {
	DateFormat df = getDateFormat( format, locale);
	return df.format(time);
}


public String getCurrentTimeFormatted(String timeFormat, Locale locale) throws DateTimeFormatException {
	return formatTime(getCurrentTime(), timeFormat, locale);
}

public String getCurrentTimeFormatted(String timeFormat)throws DateTimeFormatException {
	Locale locale = Locale.getDefault();
	return formatTime(getCurrentTime(), timeFormat, locale);
}
}
