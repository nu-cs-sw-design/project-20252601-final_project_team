package domain;

public class HttpsRequest {
    public ResponseEntity post(String url, RequestBody body) {
        return new ResponseEntity(200, "OK");
    }

    public ResponseEntity get(String url) {
        return new ResponseEntity(200, "OK");
    }
}