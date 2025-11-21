package domain.api_request;

import domain.HttpsRequest;

public abstract class ExternalAPIRequest {

    protected HttpsRequest httpsRequest = new HttpsRequest();
    protected String url;

    public ExternalAPIRequest(String url) {
        this.url = url;
    }

    public abstract String getResponse();
}