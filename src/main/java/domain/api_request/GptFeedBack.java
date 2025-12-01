package domain.api_request;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import config.Config;

public class GptFeedBack {
    private final OpenAIClient client;

    public GptFeedBack() {
        client = OpenAIOkHttpClient.builder().apiKey(Config.API_KEY).build();
    }

    public String getFeedback(String content) {
        ResponseCreateParams params = ResponseCreateParams.builder()
                .input(content)
                .model("gpt-5-nano")
                .build();
        Response response = client.responses().create(params);
        StringBuilder sb = new StringBuilder();
        response.output().forEach(item -> item.message().ifPresent(msg -> msg.content().forEach(c -> c.outputText().ifPresent(textObj -> sb.append(textObj.text())))));
        return sb.toString();
    }
}
