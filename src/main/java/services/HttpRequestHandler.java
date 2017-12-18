package services;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequestHandler {

	public HttpsURLConnection sendSSLGetRequest(String uri,
			Map<String, String> headers) throws IOException {
		URL url = new URL(uri);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		
		return connection;
	}

}