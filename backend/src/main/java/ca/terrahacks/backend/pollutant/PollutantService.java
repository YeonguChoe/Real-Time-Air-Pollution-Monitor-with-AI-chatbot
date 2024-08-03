package ca.terrahacks.backend.pollutant;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Service
public class PollutantService {

    public Map<String, Double> getData(double inputLatitude, double inputLongitude) {
        try {
            URL url = new URL("https://airquality.googleapis.com/v1/currentConditions:lookup?key=AIzaSyDjer390N__VdME5RdCjsbBb85A97OCS4k");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Header
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            double latitude = inputLatitude;
            double longitude = inputLongitude;

            String json = """
                    {
                        "location": {
                            "latitude": "%.4f",
                            "longitude": "%.4f"
                        },
                        "extraComputations": [
                            "HEALTH_RECOMMENDATIONS",
                            "DOMINANT_POLLUTANT_CONCENTRATION",
                            "POLLUTANT_CONCENTRATION",
                            "LOCAL_AQI",
                            "POLLUTANT_ADDITIONAL_INFO"
                        ]
                    }
                    """.formatted(latitude, longitude);

            // Send http request
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(json);
            out.flush();
            out.close();

            // Reading http response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String output = response.toString();

            JSONObject jsonOBJ = new JSONObject(output);
            JSONArray pollutantList = jsonOBJ.getJSONArray("pollutants");

            Map<String, Double> pollutantData = new HashMap<>();

            for (int i = 0; i < pollutantList.length(); i++) {
                JSONObject pollutantJSON = pollutantList.getJSONObject(i);
                String pollutantType = pollutantJSON.getString("displayName");
                double pollutantValue = pollutantJSON.getJSONObject("concentration").getDouble("value");
                pollutantData.put(pollutantType, pollutantValue);
            }

            return pollutantData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Pollutant getPollutant(double latitude, double longitude) {

        Map<String, Double> data = getData(latitude, longitude);

//        {NO2=16.31, O3=20.46, PM2.5=16.2, SO2=2.07, PM10=19.8, CO=312.53}

        double CO = data.get("CO");
        double NO2 = data.get("NO2");
        double O3 = data.get("O3");
        double PM10 = data.get("PM10");
        double PM25 = data.get("PM2.5");
        double SO2 = data.get("SO2");

        return new Pollutant(CO, NO2, O3, PM10, PM25, SO2);
    }
}
