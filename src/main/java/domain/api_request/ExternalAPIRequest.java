package domain.api_request;

public abstract class ExternalAPIRequest {
    private final String url;

    private final HttpsRequest httpsRequest;

    private RequestMethod method;

    public ExternalAPIRequest(String url, RequestMethod method) {
        this.url = url;
        this.httpsRequest = new HttpsRequest(url);
        this.method = method;
    }

    public abstract String getResponse();
}