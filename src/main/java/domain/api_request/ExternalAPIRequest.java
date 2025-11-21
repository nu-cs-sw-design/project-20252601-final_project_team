package domain.api_request;

public abstract class ExternalAPIRequest {
    private String url;

    public ExternalAPIRequest(String url) {
        this.url = url;
    }

    public String getReponse(){
        return null;
    }
}
