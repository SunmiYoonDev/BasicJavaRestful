package sunmi.yoon.asgn2.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import startup.rest.clock.ClockBean;
import startup.rest.clock.DateTimeFormatException;

//URIs begin http://localhost:8088/contextRoot/clock 
//http://localhost:8088/Sunmi_Yoon_Asgn2/clock
@ApplicationPath("/")
@Path("clock")
public class ClockService extends Application {

	// clokInfo will be shown if a user enter the below URI as default display
	// it displays the current header information
	// http://localhost:8088/Sunmi_Yoon_Asgn2/clock/
	@GET
	@Produces("text/html")
	public String clockInfo(@Context UriInfo uri, @Context HttpHeaders headers) {

		// uri.getAbsolutePath() returns the current URI absolute path
		// writeHeaders(headers) returns whole information in a header

		return "<html>" + "<head>" 
				+ "<title>"+"Restful Clock Service"+"</title>"
				+ "<meta http-equiv='Content-Type' content='text/html'>" 
				+ "</head>" 
				+ "<body>"
				+ "<p>Hello, This RESTful service resides at <b>" + uri.getAbsolutePath() + "</b></p>"
				+ "<p>The request headers are </p> " 
				+ "<div align='center'>" + writeHeaders(headers) + "</div>"
				+ "    </body>\n" + "</html>";
	}

	// this method integrate all information in HttpHeaders into one string value
	// and return it
	private String writeHeaders(HttpHeaders headers) {
		StringBuilder buf = new StringBuilder();
		for (String header : headers.getRequestHeaders().keySet()) {
			buf.append(header);
			buf.append(":");
			buf.append(headers.getRequestHeader(header));
			buf.append("<br>");
		}
		return buf.toString();
	}

	// this method is the most basic of getTime methods in this class
	// it displays time with medium format
	// the URI example is below
	// http://localhost:8088/Sunmi_Yoon_Asgn2/clock/timenow
	@GET
	@Path("timenow")
	public String getTime() {
		ClockBean cb = new ClockBean();
		try {
			return cb.getCurrentTimeFormatted("MEDIUM");
		} catch (DateTimeFormatException e) {
			return e.getClass().getName() + ": " + e.getMessage();
		}
	}

	// this getTime method takes arguments such as short, medium, long, full
	// those arguments are not case sensitive
	// the URI example is below
	// http://localhost:8088/Sunmi_Yoon_Asgn2/clock/timeform/medium
	@GET
	@Path("timeform/{format}")
	public String getTime(@PathParam("format") String format) {
		ClockBean cb = new ClockBean();
		try {
			return cb.getCurrentTimeFormatted(format);
		} catch (DateTimeFormatException e) {
			return e.getClass().getName() + ": " + e.getMessage();
		}
	}

	// this method receives an argument with query string argument using
	// "?name=value" format
	// available formats: short, long, medium, full
	// the URI example is below
	// http://localhost:8088/Sunmi_Yoon_Asgn2/clock/time?format=short
	@GET
	@Path("time")
	@Consumes(MediaType.TEXT_PLAIN)
	public String getTime(@Context UriInfo uriInfo) {
		String format = uriInfo.getQueryParameters().getFirst("format");
		ClockBean cb = new ClockBean();
		try {
			return cb.getCurrentTimeFormatted(format);
		} catch (DateTimeFormatException e) {
			return e.getClass().getName() + ": " + e.getMessage();
		}
	}
}
