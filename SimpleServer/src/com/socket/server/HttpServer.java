/**
 * 
 */
package com.socket.server;

import java.net.*;
import java.util.*;
import java.io.*;

import com.socket.server.HttpWarpper.HttpConnectionHandler;

/**
 * @author charles
 *
 */
public class HttpServer {
	private ServerSocket serverSocket;
	//public static int PORT=8085;
	private int port;
	public String ENCODING="utf-8";
	
	public int  getPort(){ return port;}
	public void setPort(int port) { this.port=port;}
	

	public HttpServer(int port){
		try {
			setPort(port);
			serverSocket=new ServerSocket(getPort());
			System.out.println("Http Server started at "+new Date()+ " at PORT "+getPort());
			System.out.println("Waiting for connection ...");
			int clientNo=1;
			Socket  client=null;
			while(true){
				client=serverSocket.accept();
				                           
				InetAddress inetAddr=client.getInetAddress();
				System.out.println("Starting thread for client" + clientNo +" at" +new Date());
				System.out.println("Client " +clientNo+" 's host name : "
				                  +inetAddr.getHostName()+", IP Address :"+inetAddr.getHostAddress());
				System.out.println("Connection,sending data ...");	
				HttpConnectionHandler task=new HttpConnectionHandler(client);
				new Thread(task).start();
				clientNo++;
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		
	}

//	class HttpConnectionHandler implements Runnable {
//		private Socket socket;
//		public HttpConnectionHandler(Socket socket) {
//			this.socket=socket;
//		}
//
//		@Override
//		public void run() {
//			// TODO 开启一个线程监听客户端，处理客户端的请求消息，返回给客户端响应消息
//			try {
//				BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				PrintWriter out=new PrintWriter(socket.getOutputStream());
//				
//				//  暂时不处理请求消息,后实现 HttpRequest类：处理GET/POST方法，得到URI资源，获取消息体内容
//			    String request=".";
//			    while(!request.equals("")) {
//			    	request=br.readLine();
//			    }
//			    
//			    
//			    //发送响应消息给客户端-To Do：完善 HttpResponse类
//			     out.println("HTTP/1.0 200 OK");
//			     out.println("Content-Type: text/html; charset="+ENCODING);
//			     out.println(); //finish the response header with Blank line 
//			     out.println("<h1>Hello Simple Http Server</h1>");
//			     out.println("这是一个简单Http Server demo. <br>");
//			     
//			     out.println("<form method=post action='/path?qryParm=POST URL查询参数'> POST表单使用"
//			    		   + "<p>我的系统：<input type='text' name='myOS' style='width: 300px'/></p>"
//			    		   + "<p>我的电话：<input type='text' name='myTel' style='width: 300px'/></p>"
//			    		   + "<input name=username value='用户'>"
//			    		   + "<input name=submit type=submit value=submit></form>");
//			     out.println("<form method=get action='/path?qryParm=GET URL查询参数' > GET表单使用"  
//                           + "<p><input name=username value='用户'></p> "  
//                           + "<input name=submit type=submit value=submit></form>");  
//
//                 out.println("<form method=post action='/path?qryParm=POST URL查询参数'"  
//                           + " enctype='multipart/form-data' >"  
//                           + "文件上传  <input type='file' name=file1 ><br>"  
//                           + "          "  
//                           + "<input type='file' name=file2 ><br>"  
//                           + "          "  
//                           + "<input name=username value='用户'> "  
//                           + "<input name=submit type=submit value=submit></form>");  
//			     out.flush();
//                 out.close();
//			    socket.close();
//			     
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		}
//		
//	}
}
