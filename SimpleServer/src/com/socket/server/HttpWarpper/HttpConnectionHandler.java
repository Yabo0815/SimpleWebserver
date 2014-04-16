/**
 * 
 */
package com.socket.server.HttpWarpper;

import java.net.*;

/**
 * @author charles
 *
 */
public class HttpConnectionHandler implements Runnable {
	private Socket sock;
	
	public HttpConnectionHandler(Socket sock) {
		this.sock=sock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			HttpRequest req=new HttpRequest(sock.getInputStream());
			HttpResponse res=new HttpResponse(req);
			res.writeInfo(sock.getOutputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
