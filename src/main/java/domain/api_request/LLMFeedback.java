package domain.api_request;

public class LLMFeedback extends ExternalAPIRequest {

    public LLMFeedback(String url, RequestMethod method) {
        super(url, method);
    }

    @Override
    public String getResponse() {
        return "LLM feedback response";
    }
}