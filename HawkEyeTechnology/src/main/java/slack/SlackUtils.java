
package slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class SlackUtils {
    private static final String slackWebhookUrl = "https://hooks.slack.com/services/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";

    public static void sendMessage(SlackMessage message) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(slackWebhookUrl);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(message);

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
        }
    }
}


