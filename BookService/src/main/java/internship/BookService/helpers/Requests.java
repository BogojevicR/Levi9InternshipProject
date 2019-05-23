package internship.BookService.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class Requests {
	
	static final  String UTF = "UTF-8";
	
	/**
	 * This method is used for all post requests
	 * @param url - location or rest resource
	 * @param jsonObject - object that is going to be send via post request
	 * @return returns string of whatever rest resource returns
	 */
	
			
	/**
	 * This method is used for all post requests
	 * @param url - location or rest resource
	 * @param jsonObject - object that is going to be send via post request
	 * @return returns string of whatever rest resource returns
	 * @throws IOException 
	 */
	public String makePostRequest(String url, Map<String,String> params) throws IOException {
		URL uri = new URL(url);
       
        StringBuilder postData = new StringBuilder();
        for (Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), UTF));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), UTF));
        }
        byte[] postDataBytes = postData.toString().getBytes(UTF);

        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
 
		return sb.toString();
	}
	
	public String makeTokenCheck(String token) throws IOException{
		URL uri = new URL("http://localhost:8085/auth/validation");
	       
        StringBuilder postData = new StringBuilder();
        
        byte[] postDataBytes = postData.toString().getBytes(UTF);

        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        token = "Bearer "+ token;
        conn.setRequestProperty("Authorization", token);
        
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
 
		return sb.toString();
		
	}
	
	public String getCookie(HttpServletRequest req) {
		for(Cookie c : req.getCookies()) {
			if(c.getName().equals("token")) {
				System.out.println("NASAO TOKEN");
				return c.getValue();
			}
		}
		return "";		
	}
	
}