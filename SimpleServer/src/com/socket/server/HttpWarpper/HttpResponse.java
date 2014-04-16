package com.socket.server.HttpWarpper;

import java.io.*;
import java.util.*;


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
	//private HttpVersion _version=HttpVersion._1_1;
	StringBuilder sb=new StringBuilder();
    private byte[] data;
    private int contentLength;
 
    private Map<String,String> HeaderFormat=new HashMap<String,String>();
	 HttpResponse(HttpRequest req) throws IOException {
		// TODO Auto-generated constructor stub
		switch(req.getMethod()) {
			case get:
				doGet(req,this);
			    break;
			case head:
				doHead(req,this);
				break;
			case post:
				doPost(req,this);
				break;
			case unrecognized:
				break;
			default:
			    break;
		}
	}
	 private void doPost(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		
	}
	private void doHead(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		
	}
	private void doGet(HttpRequest req, HttpResponse res) throws IOException {
		// TODO Auto-generated method stub
		addStatusLine(StatusCode._200);
		sb.append("Content-type:"+getMimeType(req)+CRLF);
		
		File file=new File(MyWeb,req.getUri());
		System.out.println(file.getAbsolutePath());
		setData(getBytes(file));
		setContentLength(getBytes(file).length);
		sb.append("Content-Length:"+contentLength);
		
	}
	
	private String getMimeType(HttpRequest req){
		String filename=req.getFileNameFromUri(req.getUri());
		String[] tokens=filename.split("\\.");
		return MIME_TYPES.get(tokens[tokens.length-1]);
	}
	//Report the error to the client with status code
	 public void SendClientEroor(StatusCode sc,OutputStream out) throws IOException {
		 //addStatusLine(sc);
		 //Build the HTTP response body
		 String errorBody="<html><head><title>Simple Web Server Error</title></head>"+
                          "<body>"+sc.toString()+"</body><html>";
		 setData(errorBody.getBytes());
	     writeInfo(out);
		 
      }
	 public void setData(byte[] data) {this.data=data;}
	 public byte[] getData() {return data;}
	 
	 public void setContentLength(int len) {this.contentLength=len;}
	
	
	
	
	
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
	
	public void addStatusLine(StatusCode status) throws IOException {
		
		sb.append(HttpVersion._1_1+" "+status.toString()+" Simpler Web Server "+CRLF);
	}
	//public void addBody(StringBuilder )
   
	public void writeInfo(OutputStream out) throws IOException {
		DataOutputStream output=new DataOutputStream(out);
		
		if(data!=null) setContentLength(data.length);
		else setContentLength(0);
		output.writeBytes(sb.toString());
		if(data!=null) output.write(data);
		output.flush();
		output.close();
	}
}
