/**
 * 
 */
package com.socket.server.HttpWarpper;

import java.io.*;
import java.net.*;

import com.socket.server.HttpWarpper.HttpConstants.*;


/**
 * @author charles
 *
 */
public class HttpConnectionHandler implements Runnable {
        Socket sock;
	InputStream in;
	OutputStream out;
	HttpRequest req;
	HttpResponse res;
	
	public HttpConnectionHandler(Socket sock) throws IOException   {
		this.sock=sock;
		
		
		
	}
	
	@Override
	public void run() {
		try {
			this.in=sock.getInputStream();
			this.out=sock.getOutputStream();
			this.req=new HttpRequest(in);
			this.res=new HttpResponse(req,out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
