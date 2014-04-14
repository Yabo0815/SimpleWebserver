package util;

public class HttpWrap {
    private String wrapString;

    public HttpWrap() {
        this.wrapString = null;
    }

    public HttpWrap(String wrapString) {
        this.wrapString = wrapString;
    }

    public String wrapCode() {
        String head = "HTTP/1.1 404 File Not Found/r/n"
                + "Content-Type: text/html/r/n" + "Content-Length: 23/r/n"
                + "/r/n" + "<h1>File Not Found</h1>";

        return head;
    }
}
