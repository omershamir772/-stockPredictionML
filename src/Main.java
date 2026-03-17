//import libraries

//import the java.io.BufferedReader to read files
import java.io.BufferedReader;

//import the java.io.InputStreamReader to read from the api
import java.io.InputStreamReader;

//import java.net.HttpURLConnection to create user agent
import java.net.HttpURLConnection;

//import java.net.URL to read the link
import java.net.URL;

//import java.net.URL to read the link
import org.json.*;

//create a public class Main
public class Main {

    //this function uses both HttpURLConnection and java.net.URL to find the stocks data
    public static String jsonReader() throws Exception {

            //this variable store the link for the api
            URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/NVDA?range=max&interval=1d");

            //create the url connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //create an agent thht will read the data
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            //read the data from the api
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            //create a string that will one line per time store the JSON file data
            String line;

            //create a string that will store the JSON file data
            String jsonStock = "";

            //read all the lines and store them js
            while ((line = read.readLine()) != null) {
                jsonStock = line;


            }

            //close the object that read data from the api
            read.close();

            //return the data
            return jsonStock.toString();

    }
    //create a public array that will store the open prices of the stock
    public static JSONArray openArr;

    //create a public array that will store the highest prices of the stock
    public static JSONArray highArr;

    //create a public array that will store the lower prices of the stock
    public static JSONArray lowArr;

    //create a public array that will store the close prices of the stock
    public static JSONArray closeArr;

    //create a public array that will store the volume of the stock
    public static JSONArray volumeArr;

    //create a public array that will store the time stamp of the stock value
    public static JSONArray timestampArr;

    //create the main function
    public static void main(String[] args) throws Exception {
        try {
            String jsonStock = jsonReader();
            JSONObject jsonObject =  new JSONObject(jsonStock);
            JSONObject chart = jsonObject.getJSONObject("chart");
            JSONArray result = chart.getJSONArray("result");
            JSONObject result0 = result.getJSONObject(0);

            //find the timestamp
            timestampArr = result0.getJSONArray("timestamp");
            JSONObject indicators = result0.getJSONObject("indicators");
            JSONArray quote = indicators.getJSONArray("quote");
            JSONObject quote0 = quote.getJSONObject(0);

            //find the open value
            openArr = quote0.getJSONArray("open");

            //find the high value
            highArr = quote0.getJSONArray("high");

            //find the low value
            lowArr = quote0.getJSONArray("low");

            //find the close value
            closeArr = quote0.getJSONArray("close");

            //find the volume value
            volumeArr = quote0.getJSONArray("volume");

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