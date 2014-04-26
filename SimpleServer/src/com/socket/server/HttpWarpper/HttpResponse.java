package com.socket.server.HttpWarpper;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;


/*
 *  <http-version> <reponse-code> <response-text> CRLF
 *  <headerName1>:<headerData1>
 *  <headerName1>:<headerData1>
 *  .
 *  .
 *  CRLF
 *  <body>
 */
public class HttpResponse implements HttpConstants {
	
    byte[] data;
    private int contentLength;
    private HttpRequest req;
    private OutputStream out;
 
    //private Map<String,String> HeaderFormat=new HashMap<String,String>();
	HttpResponse(HttpRequest req,OutputStream out) throws IOException  {
		this.req=req;
		this.out=out;
		sendContent();
	}
	 
	
	
	
	
	private void sendContent() throws IOException {
	    switch(req.getMethod()) {
			case get:
				doGet();
				break;
			case post:
				doPost();
				break;
			case head:
				doHead();
				break;
			default:
				SendClientEroor(StatusCode._501, out);
				break;
			}
	}
	
	private void doGet() throws IOException {
		
		String filename="./MyWeb" +req.getUri(); // '.' represents the current dir 
		FileInputStream fis=null;
		boolean isExists=true;
		
		try {
			fis=new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			isExists=false;
		}
		String statusLine=null;
		String contentTypeLine=null;
		String contentLengthLine="error";
		String errorBody=null;
		if(isExists) {
			statusLine=HttpVersion._1_1+" "+StatusCode._200+" Simpler Web Server "+HttpConstants.CRLF;
			contentTypeLine="Content-Type: "+req.getMimeType(filename)+HttpConstants.CRLF;
			contentLengthLine="Content-Length: "+(new Integer(fis.available()).toString())+HttpConstants.CRLF;
		}
		else {
			statusLine=HttpVersion._1_1+" "+StatusCode._404+" Simpler Web Server "+HttpConstants.CRLF;
			contentTypeLine="Content-Type: text/html"+HttpConstants.CRLF;
			errorBody="<HTML>" +   
			           "<HEAD><TITLE>Simple Web Server Error</TITLE></HEAD>" +   
			           "<BODY>"+ StatusCode._404  
			           +"<br>usage:http://yourHostName:port/"   
			           +"fileName.html</BODY></HTML>" ;
		}
		
		//Send headers
		out.write(statusLine.getBytes());
		out.write(contentTypeLine.getBytes());
		out.write(contentLengthLine.getBytes());
		out.write(HttpConstants.CRLF.getBytes());
		System.out.println("header sent" + statusLine);
		//Send body
		if(isExists) {
			sendBody(fis,out);
			fis.close();
		}
		else {
			out.write(errorBody.getBytes());
		}
	}

       private void sendBody(FileInputStream fis, OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=fis.read(buffer))!=-1) {
			out.write(buffer,0,len);
		}
	   }

		
	private void doHead() {
		// TODO Auto-generated method stub
		
	}





	private void doPost() {
		// TODO Auto-generated method stub
		
	}





	

    //Report the error to the client with status code
	 public void SendClientEroor(StatusCode sc,OutputStream out) throws IOException {
		
		 //Build the HTTP response body
		 String errorBody="<html><head><title>Simple Web Server Error</title></head>"+
                          "<body>"+sc.toString()+"</body><html>";
		 setData(errorBody.getBytes());
	     writeContent(out);
		 
      }
	 public void setData(byte[] data) {this.data=data;}
	 public byte[] getData() {return data;}
	 
	 public void setContentLength(int len) {this.contentLength=len;}
	 public int getContentLength() {return contentLength;}
	
	
	
	//read file data
	private byte[] getBytes(File file) throws IOException {
		int len=(int)file.length();
		byte[] arr=new byte[len];
		FileInputStream input=new FileInputStream(file); 
		int off=0;
		while(off<len) {
			int num=input.read(arr, off, len-off);
			off+=num;
		}
		input.close();
		return arr;
	}
	
   public void writeContent(OutputStream out) throws IOException {
		DataOutputStream output=new DataOutputStream(out);
		
		//output.write(writeStatusLine().getBytes());
		//output.write(writeHeaders().getBytes());
		
		if(data!=null) setContentLength(data.length);
		else setContentLength(0);
		if(data!=null) output.write(data);
		output.flush();
		output.close();
	}
	
	
}
