/**
 * 
 */
package com.socket.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.socket.server.HttpWarpper.*;
import com.socket.server.HttpWarpper.HttpConstants.HttpMethod;

/**
 * @author charles
 *
 */
public class HttpRequestTest {

	/**
	 * @param args
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest(new ByteArrayInputStream("HEAD /index.html HTTP/1.1\r\n".getBytes()));
		boolean flag=req.getMethod().equals(HttpMethod.head);
		System.out.println("HEAD test successful : "+flag);
		System.out.println(req.getFileNameFromUri(req.getUri()));
		
		HttpRequest req1 = new HttpRequest(new ByteArrayInputStream("GET / HTTP/1.1\r\n".getBytes()));
		boolean flag1=req1.getMethod().equals(HttpMethod.get);
		System.out.println("GET test successful : "+flag1);
		
		HttpRequest req2 = new HttpRequest(new ByteArrayInputStream("POST / HTTP/1.1\r\n".getBytes()));
		boolean flag2=req2.getMethod().equals(HttpMethod.post);
		System.out.println("POST test successful : "+flag2);
		

	}

}
