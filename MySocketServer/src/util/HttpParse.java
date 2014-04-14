package util;

public class HttpParse {

    private String httpHead;

    public HttpParse(String httpHead) {
        this.httpHead = httpHead;
    }

    public String extractMethod() {
        int firstSpace;
        firstSpace = httpHead.indexOf(" ");
        if (firstSpace != -1)
            return httpHead.substring(0, firstSpace);
        return null;
    }

    public String extractUri() {
        int firstSpace, secondSpace;
        firstSpace = httpHead.indexOf(" ");
        if (firstSpace != -1) {
            secondSpace = httpHead.indexOf(" ", firstSpace + 1);
            if (secondSpace > firstSpace)
                return httpHead.substring(firstSpace + 1, secondSpace);
        }
        return null;
    }

}
