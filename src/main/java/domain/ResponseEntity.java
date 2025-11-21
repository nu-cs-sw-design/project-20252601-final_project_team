package domain;

public class ResponseEntity {
    public int status;
    public String body;

    public ResponseEntity(int status, String body) {
        this.status = status;
        this.body = body;
    }
}