package domain.api_request;

public class LLMFeedback extends ExternalAPIRequest {

    public LLMFeedback(String url) {
        super(url);
    }

    @Override
    public String getResponse() {
        return "LLM feedback response";
    }
}