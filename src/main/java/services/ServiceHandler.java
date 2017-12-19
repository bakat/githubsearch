package services;

import java.io.IOException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class ServiceHandler {

	protected abstract String getUri();
	public abstract HttpsURLConnection getRawResponse();
	public abstract void setRequestMessage(ServiceMessage message);

	public abstract void sendRequest() throws 
	JsonMappingException, JsonGenerationException, IOException;
	
	public HttpsURLConnection performGetRequest(String uri, Map<String,String> headers) throws IOException{
		HttpRequestHandler requestHandler = new HttpRequestHandler();
		
		return requestHandler.sendSSLGetRequest(uri, headers);
	}
}