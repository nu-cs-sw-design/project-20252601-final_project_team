package domain.api_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // 忽略 JSON 中未定义的字段
public class ResponseOutputItem {
    private ResponseOutputMessage message;

    public ResponseOutputMessage getMessage() { return message; }
    public void setMessage(ResponseOutputMessage message) { this.message = message; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseOutputMessage {
        private String id;
        private List<Content> content;
        private String role;
        private String status;
        private String type;
        private Map<String, Object> additionalProperties;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public List<Content> getContent() { return content; }
        public void setContent(List<Content> content) { this.content = content; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public Map<String, Object> getAdditionalProperties() { return additionalProperties; }
        public void setAdditionalProperties(Map<String, Object> additionalProperties) { this.additionalProperties = additionalProperties; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {
        @JsonProperty("outputText")
        private OutputText outputText;

        public OutputText getOutputText() { return outputText; }
        public void setOutputText(OutputText outputText) { this.outputText = outputText; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OutputText {
        private String text;

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
