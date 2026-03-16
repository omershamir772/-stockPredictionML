//import libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    //this function uses both HttpURLConnection and java.net.URL to find the stocks data
    public static String jsonReader() throws Exception {

            //this variable store the link for the api
            URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/NVDA?range=max&interval=1d");

            //create the url connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            //bufferread the stocks
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            String jsonStock = "";

            while ((line = read.readLine()) != null) {
                jsonStock = line;


            }

            read.close();

            return jsonStock.toString();

    }

    public static void main(String[] args) throws Exception {
        try {
            String jsonStock = jsonReader();
            JSONObject jsonObject =  new JSONObject(jsonStock);
            JSONObject chart = jsonObject.getJSONObject("chart");
            JSONArray result = chart.getJSONArray("result");
            JSONObject result0 = result.getJSONObject(0);
            //find the timestamp
            JSONArray timestampArr = result0.getJSONArray("timestamp");
            JSONObject indicators = result0.getJSONObject("indicators");
            JSONArray quote = indicators.getJSONArray("quote");
            JSONObject quote0 = quote.getJSONObject(0);
            //find the open value
            JSONArray openArr = quote0.getJSONArray("open");
            //find the high value
            JSONArray highArr = quote0.getJSONArray("high");
            //find the low value
            JSONArray lowArr = quote0.getJSONArray("low");
            //find the close value
            JSONArray closeArr = quote0.getJSONArray("close");
            //find the volume value
            JSONArray volumeArr = quote0.getJSONArray("volume");

            System.out.println(openArr);
            System.out.println(highArr);
            System.out.println(lowArr);
            System.out.println(closeArr);
            System.out.println(volumeArr);
            System.out.println(timestampArr);

            //double[][] data =

        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }

    }
}