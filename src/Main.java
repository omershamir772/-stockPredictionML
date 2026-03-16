//import libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Main {
    static public void main(String[] args) throws Exception {
        //create the url connection
        URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/NVDA?range=1y&interval=1d");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        //bufferread the stocks
        BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        String JsonStock = "";

        while ((line = read.readLine()) != null) {
            JsonStock = line;
        }

        try {
            JSONObject jsonObject =  new JSONObject(JsonStock);
            JSONObject chart = jsonObject.getJSONObject("chart");
            System.out.println(chart);
            //int timestamp = jsonObject.getInt("timestamp");
            //System.out.println(timestamp);


        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }



        read.close();
    }
}