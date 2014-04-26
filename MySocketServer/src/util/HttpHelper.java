package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.StringTokenizer;

public class HttpHelper implements HttpConstants {
    private String httpStr;
    private HttpMethod method;
    private String uri;
    private HttpVersion version;
    private static String index;

    public HttpHelper(String httpStr) {
        setHttpStr(httpStr);
        // System.out.println(httpStr);
    }

    public void setHttpStr(String httpStr) {
        this.httpStr = httpStr;
    }

    public String getHttpStr() {
        return httpStr;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setVersion(HttpVersion version) {
        this.version = version;
    }

    public HttpVersion getVersion() {
        return version;
    }
    
    public static void setIndex(String fileName) {
        index = fileName;
    }

    public static String getIndex() {
        return index;
    }

    public void parse() throws IOException {
        StringTokenizer splitter = new StringTokenizer(httpStr);
        String tmpStr = splitter.nextToken();
        // set the request method
        setMethod(HttpMethod.valueOf(tmpStr.toLowerCase()));

        // set the request uri
        setUri(splitter.nextToken());

        tmpStr = splitter.nextToken(" HTTP/");
        tmpStr = "_" + tmpStr.replace(".", "_");
        // set the http version from request
        setVersion(HttpVersion.valueOf(tmpStr.trim()));
        // System.out.println(method + " " + uri + " " + version);

    }

    public ByteBuffer wrap() throws Exception {
        String responseCode;
        if (uri.equals("/"))
            uri = index; // to improve for personalize
        String fileRoute = WEB_ROOT + File.separator + uri;
        String statusLine = null;
        String serverLine = null;
        String contentTypeLine = null;
        String contentLengthLine = null;
        String errorBody = null;
        String end = null;

        File file = new File(fileRoute);
        if (file.exists()) {
            statusLine = HttpVersion._1_1 + " " + StatusCode._200 + CRLF;
            serverLine = "Server: Simple Asynchronous Server" + CRLF;
            contentTypeLine = "Content-Type: " + getMimeType() + CRLF;
            contentLengthLine = "Content-Length: " + file.length() + CRLF;
            end = CRLF;

            responseCode = statusLine + serverLine + contentTypeLine
                    + contentLengthLine + end;
        } else {
            statusLine = HttpVersion._1_1 + " " + StatusCode._404 + CRLF;
            serverLine = "Server: Simple Asynchronous Server" + CRLF;
            contentTypeLine = "Content-Type: text/html" + CRLF;
            end = CRLF;
            errorBody = "<HTML><HEAD><TITLE>Page Error</TITLE></HEAD>"
                    + "<BODY>" + StatusCode._404 + "<br>" + uri.substring(1)
                    + " is not found</BODY></HTML>";

            responseCode = statusLine + serverLine + contentTypeLine + end
                    + errorBody;
        }
        System.out.println(responseCode);

        ByteBuffer responseBuffer = ByteBuffer.allocate(1024 * 1024);
        responseBuffer = ByteBuffer.wrap(responseCode.getBytes("UTF-8"));

        if (file.exists()) {
            byte[] fileBytes = null;
            InputStream in = new FileInputStream(file);
            int length = (int) file.length();
            fileBytes = new byte[length];
            int offset = 0;
            int sizeRead = 0;
            while (offset < fileBytes.length
                    && (sizeRead = in.read(fileBytes, offset, fileBytes.length - offset)) >= 0) {
                offset += sizeRead;
            }
            in.close();
            responseBuffer = ByteBuffer.wrap(fileBytes);
        }
        
        return responseBuffer;
    }

    public String getMimeType() {
        int index = uri.lastIndexOf(".");
        String key = uri.substring(index + 1);
        return MIME_TYPES.get(key);
    }

}
