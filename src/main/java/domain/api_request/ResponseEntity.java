package domain.api_request;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseEntity {
        private final int statusCode;
        private final Map<String, String> headers;
        private final String body;

        public ResponseEntity(int statusCode, Map<String, String> headers, String body) {
            this.statusCode = statusCode;
            this.headers = headers;
            this.body = body;
        }

}