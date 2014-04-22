package com.socket.server.HttpWarpper;
import java.io.*;
import java.util.StringTokenizer;

public class HttpRequest implements HttpConstants{
	 private HttpMethod _method;
	 private String _uri;
	 private String _version;
	 private BufferedReader br;

	public HttpRequest(InputStream in) throws IOException {
		// TODO Auto-generated constructor stub
		this.br=new BufferedReader(new InputStreamReader(in));
		processRequest();
	}
	
	
	
	
	
	
	public void setUri(String uri) { 
		this._uri=uri;
	}
	public String getUri(){ 
		return _uri; 
	}
	
	public void setMethod(HttpMethod method) { 
		this._method=method;
	}
	
	public HttpMethod  getMethod(){ 
		return  _method;
	}
	
	public String getRequestLine() {
		return _method.toString()+" "+_uri+" "+_version.toString();
	}
	
	
	
	
	public void processRequest() throws IOException{
		
			String headerLine=br.readLine();
			//if(headerLine.equals(HttpConstants.CRLF)|| headerLine.equals("") ) break;
			StringTokenizer s=new StringTokenizer(headerLine);
			String temp=s.nextToken();
			setMethod(HttpMethod.valueOf(temp.toLowerCase()));
			_uri=s.nextToken();
			_version=s.nextToken();
	   
		
	}
	
	//Improvement : parse the uri completely 

	
	
	public String getMimeType(String filename){
		//String filename=getFileNameFromUri(uri);
		int index=filename.lastIndexOf(".");
		String key=filename.substring(index+1);
		return MIME_TYPES.get(key);
	}
	
	
}
