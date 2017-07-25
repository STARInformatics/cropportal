package bio.knowledge.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import com.google.gson.Gson;

public class RequestEngine<T> {
	private static String URL_ENCODING = "UTF-8";
	private Type type;
	
	public RequestEngine(Type type) {
		this.type = type;
	}
	
	private Gson gson = new Gson();
	
	private T httpRequest(String uri) {
		try {
			URL url = new URL(uri);
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(url.openConnection().getInputStream())
			);
			
			String line;
			String response = "";
			
			while ((line = reader.readLine()) != null) {
				response += line;
			}
			
			return gson.fromJson(response, type);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public T request(String query, int timeoutSeconds) {
		try {
			URLEncoder.encode(query, URL_ENCODING);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return null;
		}
		
		CompletableFuture<T> future = CompletableFuture.supplyAsync(new Supplier<T>() {
			@Override
			public T get() {
				return httpRequest(query.replace(" ", "%20"));
			}
		});
		
		try {
			return future.get(timeoutSeconds, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}
}
