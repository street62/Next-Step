package webserver.http.response;

public enum StatusCode {
    OK(200),
    FOUND(302);


    private int codeNumber;

    private StatusCode(int codeNumber) {
        this.codeNumber = codeNumber;
    }

    public int getCodeNumber() {
        return codeNumber;
    }


}
