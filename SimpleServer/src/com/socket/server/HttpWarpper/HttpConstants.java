/**
 * 
 */
package com.socket.server.HttpWarpper;
import java.io.File;
import java.util.*;


/**
 * @author charles
 *
 */
public interface HttpConstants {
	 public final static String MyWeb=System.getProperty("user.dir")+File.separator+"MyWeb";
	 //public final static String MyWeb=getContext().getServletContext().getRealPath("/")+File.separator+"MyWeb";
	 
	 public static final String CRLF="\r\n";
	 enum HttpMethod  {
		 get("GET"),
		 head("HEAD"),
		 post("POST"),
		 put("PUT"),
		 trace("TRACE"),
		 delete("DELETE"),
		 unrecognized(null);
		 
		 private  String method;
		 HttpMethod(String method){
			 this.method=method;
		 }
		 
		 public String toString(){
			 return method;
		 }
	 }
	 enum HttpVersion  {
		 _1_0("HTTP/1.0"),
		 _1_1("HTTP/1.1");
		 
		 private String version;
		 HttpVersion(String version) {
			 this.version=version;
		 }
		 
		 public String toString(){
			 return version;
		 }
		 
		 
	 }
	 enum StatusCode implements HttpConstants {
		 _100("100 Continue"),
		 _101("101 Switching Protocols"), //Prompt message，1xx code indicate that just continue
		
		 _200("200 OK"),
		 _201("201 CREATED"),
		 _202("200 OK"),
		 _204("200 OK"), //Successful, 2xx code indicate that the request is accepted
		 
		 _301("Mover Permanently"),
		 _302("Found"),
		 _304("Modified"), //Redirect, 3xx code 
		 
		 _400("Bad Request"),
		 _401("401 Unauthorized"), 
		 _402("402 Payment Required"), 
		 _403("403 Forbidden"), 
		 _404("404 Not Found"), //Client error , 4xx code
		 
		 
		 _500("500 Internal Server Error"), 
		 _501("501 Not Implemented"), 
		 _502("502 Bad Gateway"), 
		 _503("503 Service Unavailable"),
		 _504("504 Gataway Time-out"); //Server error, 5xx code
		 
		 private String status;
		 StatusCode(String status) {
			 this.status=status;
		 }
		 public String toString(){
			 return status;
		 }
		 
	 }
	 
	 public static final Map<String,String> MIME_TYPES=new HashMap<String,String>() {{
		 put("css","text/css");
		 put("htm","text/html");
		 put("html","text/html");
		 put("h","text/plain");
		 put("c","text/plain");
		 put("cpp","text/plain");
		 put("txt","text/plain");
		 put("java","text/x-java-source,text/java");
		 put("bmp","image/bmp");
		 put("gif","image/gif");
		 put("jpg","image/jpg");
		 put("jpeg","image/jpeg");
		 put("png","image/png");
		 put("mpeg","video/mpeg");
		 put("mp4","video/mp4");
		 put("avi","video/avi");
		 put("mp3","audio/mp3");
		 put("wav","audio/wav");
		 put("doc","application/msword");
		 put("pdf","application/pdf");
		 put("js","application/javasrcipt");
		 put("zip","application/zip");
		 put("gzip","application/x-gzip");
		 put("exe","application/octest-stream");
	 }};

}
