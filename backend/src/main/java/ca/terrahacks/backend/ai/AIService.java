package ca.terrahacks.backend.ai;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AIService {

    public String getData(String inputCity, String inputQuestion, double inputCO, double inputNO2, double inputO3, double inputPM10, double inputPM25, double inputSO2) {
        try {
            Dotenv dotenv = Dotenv.load();
            String apiKey = dotenv.get("OPEN_AI_API_KEY");
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Header
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            String city = inputCity;
            String question = inputQuestion;
            double CO = inputCO;
            double NO2 = inputNO2;
            double O3 = inputO3;
            double PM10 = inputPM10;
            double PM25 = inputPM25;
            double SO2 = inputSO2;

            // JSON template with placeholders
            String jsonTemplate = """
                    {
                      "model": "gpt-4o-mini",
                      "messages": [
                        {
                          "role": "system",
                          "content": "You are an environmental scientist whose studies focus on addressing environmental issues. Additionally, you can recommend daily actions that citizens can take based on scientific parameters. Your answer should be only specific to %s. Today's parameters are NO2 = %f, O3 = %f, PM2.5 = %f, SO2 = %f, PM10 = %f, and CO = %f. Don't mention that I provided the data in your answer."
                        },
                        {
                          "role": "user",
                          "content": "%s"
                        }
                      ],
                      "max_tokens": 300
                    }
                    """;

            String json = String.format(jsonTemplate, city, NO2, O3, PM25, SO2, PM10, CO, question);

            // Send http request
            try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
                out.writeBytes(json);
                out.flush();
            }

            // Check HTTP response code
            int responseCode = connection.getResponseCode();
            // Reading http response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject message = jsonObject.getJSONArray("choices").getJSONObject(0).getJSONObject("message");
                String answer = message.getString("content");

                return answer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public AI getAnswer(String city, String question, double CO, double NO2, double O3, double PM10, double PM25, double SO2) {
        String reply = getData(city, question, CO, NO2, O3, PM10, PM25, SO2);
        return new AI(reply);
    }
}
