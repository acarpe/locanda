package it.xp.scuola.locanda.util;

import it.xp.scuola.locanda.web.home.LocandaServlet;
import it.xp.scuola.toolkit.web.ReusableJettyApp;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestWithALiveServer {

	private static ReusableJettyApp app = new ReusableJettyApp(new LocandaServlet());
	private HttpResponse response;
	private String cachedResponseBody;
	protected Map<String, String> params = new HashMap<String, String>();

	@BeforeClass
    public static void startApplication() throws Exception {
    	app.start(8123, "src/main/webapp");
    }

	@AfterClass
    public static void shutdownApplication() throws Exception {
    	app.shutdown();
    }

	@Before
    public void clearParams() {
    	params.clear();
    }

	protected String responseBody() throws IOException {
		if (this.cachedResponseBody == null) {
		    byte[] bytes = new byte[10000];
			int bytesRead = response.getEntity().getContent().read(bytes);
			if (bytesRead == -1)
				return "";
			cachedResponseBody = new String(bytes, 0, bytesRead, Charset.forName("UTF-8"));
		}
	    return cachedResponseBody;
	}

	protected void assertMimeType(String expectedMimeType) {
		assertHeader("content-type", expectedMimeType);
	}

	protected void assertLocationHeader(String expectedLocation) {
		assertHeader("location", expectedLocation);
	}

	protected void assertHeader(String name, String expectedValue) {
		Header header = response.getLastHeader(name.toLowerCase());
		assertNotNull(name + " not set", header);
		assertEquals(name, expectedValue, header.getValue());
	}

	protected void assertStatus(int expectedStatus) {
		assertEquals("Status code", expectedStatus, response.getStatusLine().getStatusCode());
	}

	protected void get(String path) throws IOException, URISyntaxException {
		URI url = new URI(baseUrl() + path + queryString());
		HttpGet request = new HttpGet(url);
		this.response = makeHttpClient().execute(request);
	}

	protected void post(String path) throws URISyntaxException, ClientProtocolException, IOException {
		URI url = new URI(baseUrl() + path);
		HttpPost request = new HttpPost(url);
		addParameters(request);
		this.response = makeHttpClient().execute(request);
	}

	protected HttpClient makeHttpClient() {
		cachedResponseBody = null;
		return HttpClientBuilder.create().disableRedirectHandling().build();
	}

	protected String baseUrl() {
		return "http://localhost:" + 8123;
	}

	protected String queryString() throws UnsupportedEncodingException {
		String queryString = "";
		for (String name : params.keySet()) {
			if (!queryString.isEmpty())
				queryString += "&";
			queryString += urlEncode(name) + "=" + urlEncode(params.get(name));
		}
		if (!queryString.isEmpty())
			queryString = "?" + queryString;
		return queryString;
	}

	private void addParameters(HttpPost request) throws UnsupportedEncodingException {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (String name : params.keySet()) {
			parameters.add(new BasicNameValuePair(name, params.get(name)));
		}
		request.setEntity(new UrlEncodedFormEntity(parameters));
	}

	private String urlEncode(String name) throws UnsupportedEncodingException {
	    return URLEncoder.encode(name, "UTF-8");
    }
}
