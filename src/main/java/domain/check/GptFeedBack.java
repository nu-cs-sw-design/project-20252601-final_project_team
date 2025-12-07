package domain.check;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.errors.UnauthorizedException;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import config.Config;
import domain.asm.ProjectInfo;

public class GptFeedBack implements ProjectCheckRule {
    private final OpenAIClient client;

    public GptFeedBack() {
        client = OpenAIOkHttpClient.builder().apiKey(Config.API_KEY).build();
    }

    private String getFeedback(String content) {
        StringBuilder sb = new StringBuilder();
        try {
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .input(content)
                    .model("gpt-5-nano")
                    .build();
            Response response = client.responses().create(params);
            response.output().forEach(item -> item.message().ifPresent(msg -> msg.content().forEach(c -> c.outputText().ifPresent(textObj -> sb.append(textObj.text())))));
        } catch (UnauthorizedException e) {
            return null;
        }
        return sb.toString();
    }

    @Override
    public CheckResult check(ProjectInfo projectInfo) {
        String checkName = "LLM FeedBack";
        String content = "Follow is my project information. Can you give me some advice?";
        content += projectInfo.classes.toString();
        String message = getFeedback(content);
        boolean result = message != null && message.isEmpty();
        return new CheckResult(checkName, null, result, message);
    }
}
