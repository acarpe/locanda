package it.xp.scuola.locanda.util;

import it.xp.scuola.toolkit.web.*;

import java.io.IOException;
import java.io.PrintWriter;

public class FakeHttpServletResponse extends NullServletResponse {
	private FakeWriter writer = FakeWriter.create();
	private int statusCode = 200;
	private String redirectedTo;

	public int getStatusCode() {
	    return statusCode ;
    }

	public String getBody() {
		return writer.getWrittenText();
	}

	public String getRedirectedTo() {
		return redirectedTo;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
	    return writer ;
	}

	@Override
	public void setStatus(int statusCode) {
	    this.statusCode = statusCode;
	}

	@Override
	public void sendRedirect(String location) throws IOException {
	    redirectedTo = location;
	}
}
