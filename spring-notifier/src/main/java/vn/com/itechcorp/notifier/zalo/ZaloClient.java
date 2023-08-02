package vn.com.itechcorp.notifier.zalo;

import com.google.gson.JsonObject;
import com.vng.zalo.sdk.APIException;
import com.vng.zalo.sdk.oa.ZaloOaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ZaloClient {

    private final ZaloOaClient zaloClient = new ZaloOaClient();

    @Value("${zalo.access_token}")
    private String accessToken;

    public void sendMessage(String toUserID, String message) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("access_token", accessToken);

            JsonObject id = new JsonObject();
            id.addProperty("user_id", toUserID);

            JsonObject text = new JsonObject();
            text.addProperty("text", message);

            JsonObject body = new JsonObject();
            body.add("recipient", id);
            body.add("message", text);

            JsonObject excuteRequest = zaloClient.excuteRequest(
                    "https://openapi.zalo.me/v2.0/oa/message",
                    "POST",
                    null,
                    body,
                    headers,
                    null
            );
        } catch (APIException e) {
            log.error("[Zalo-Client] Can not send message. Error msg : {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public JsonObject getInterestedUsers(int offset, int count) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("access_token", accessToken);

            JsonObject data = new JsonObject();
            data.addProperty("offset", 0);
            data.addProperty("count", 5);

            Map<String, Object> params = new HashMap<>();
            params.put("data", data.toString());

            return zaloClient.excuteRequest(
                    "https://openapi.zalo.me/v2.0/oa/getfollowers",
                    "GET",
                    params,
                    null,
                    headers,
                    null
            );
        } catch (APIException e) {
            log.error("[Zalo-Client] Can not get interested users");
            throw new RuntimeException(e);
        }
    }

}
