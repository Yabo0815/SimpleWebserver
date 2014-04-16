package com.socket.server.HttpWarpper;
import java.io.*;

public class HttpRequest implements HttpConstants{
	 private HttpMethod _method;
	 private String uri;
	 private String _version;
	 StringBuilder sb=new StringBuilder();

	public HttpRequest(InputStream in) throws IOException {
		// TODO Auto-generated constructor stub
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String reqLine=br.readLine();
		processRequestLine(reqLine);
		// do not handle headers in details
//		while(!reqLine.equals("")) {
//			reqLine=br.readLine();
//			sb.append(reqLine);
//		}
	}
	
	
	
	public HttpMethod  getMethod(){ return  _method;}
	public String getUri(){ return uri; }
	
	
	public String getRequestLine() {
		return _method.toString()+" "+uri+" "+_version.toString();
	}
	
	
	
	public void processRequestLine(String line){
		String[] tokens=line.split(" "); //\s match space character 
		_method=HttpMethod.valueOf((tokens[0]).toLowerCase());
		uri=tokens[1];
		_version=tokens[2]; 
		System.out.println(uri);
		System.out.println(getRequestLine());
	}
	//parse the uri : / /index.htm /add?123&125
	public String getFileNameFromUri(String uri){
		StringBuilder sFileName =new StringBuilder();
		//sFileName.append(".");
		if(uri.charAt(uri.length()-1)=='/') {
			sFileName.append(uri+"index.html");
		} 
		else {
			//split the file name and parameter
			String[] tokens=uri.split("[?]"); // ?/+/^/* is meta patter symbol ,use [?] or \\?
			sFileName.append(tokens[0]);
		}
		
		return sFileName.toString();
	}
}
