package com.example.ms1.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class ClientHttpRequestInterceptorImpl implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution)
			throws IOException {
		ClientHttpResponse response=execution.execute(request, body);
		response=new ClientResponseImpl(response);
		return response;
	}
	
	private static class ClientResponseImpl implements ClientHttpResponse {

		private final ClientHttpResponse response;
		private byte[] body;
		
		public ClientResponseImpl(ClientHttpResponse response)
		{
			this.response=response;
		}
		@Override
		public InputStream getBody() throws IOException {
			if(body==null)
			{
				body=StreamUtils.copyToByteArray(response.getBody());
				
			}
			return new ByteArrayInputStream(body);
		}

		@Override
		public HttpHeaders getHeaders() {
			return response.getHeaders();
		}

		@Override
		public HttpStatusCode getStatusCode() throws IOException {
			return response.getStatusCode();
		}

		@Override
		public int getRawStatusCode() throws IOException {
			return response.getRawStatusCode();
		}

		@Override
		public String getStatusText() throws IOException {
			return response.getStatusText();
		}

		@Override
		public void close() {
			 response.close();
		}
		
	}

}
