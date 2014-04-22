/**
 * 
 */
package com.socket.server;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

import com.socket.server.HttpWarpper.*;

/**
 * @author charles
 *
 */
public class HttpServer {
	private ServerSocket serverSocket;
        private int port;
	public String ENCODING="utf-8";
	private boolean Stopped=false;
	
	
	public int  getPort(){ return port;}
	public void setPort(int port) { this.port=port;}
	
        public HttpServer(){
    		this(HttpConstants.DEFAULT_PORT);
        }
        
	public HttpServer(int port){
		setPort(port);
	}
	
	/**
	 * Start the server
	 * @throws IOException
	 */
	public void start()  {
		
			try {
				serverSocket=new ServerSocket(port);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Http Server is listening"+ " on PORT "+ getPort() + new Date());
			System.out.println("Waiting for connection ...");
			
			ExecutorService executor=Executors.newCachedThreadPool();
			Socket sock=null;
			while(!isStopped()) {
				
				try {
					sock=serverSocket.accept();
					executor.execute(new HttpConnectionHandler(sock));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if(isStopped()) {
						System.out.println("Server Stopped");
						return ;
					}
					e.printStackTrace();
				}
			}
			
			System.out.println("Server Stopped");
		} 
		
	
	
	public boolean isStopped(){ return this.Stopped;}
	
	/**
	 * Stop the server
	 */
	public void stop(){
		this.Stopped=true;
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}	
	

