//import libraries
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api {
    //this function uses both HttpURLConnection and java.net.URL to find the stocks data
    public static String jsonReader() throws Exception {

        //this variable store the link for the api
        URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/NVDA?range=max&interval=1d"); //this api stores the information from the past to the future

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

    //this function is used to convert the JSON file style of writing into string arrays to make them usable for the neural network
    public static void JSONtoString(String jsonStock) throws Exception{

        //create a try&catch statement
        try {

            //read the JSON file data from the api
            JSONObject jsonObject =  new JSONObject(jsonStock);

            //sort the JSON file into result
            JSONObject chart = jsonObject.getJSONObject("chart");

            //sort the JSON file into result
            JSONArray result = chart.getJSONArray("result");

            //create an object
            JSONObject result0 = result.getJSONObject(0);

            //find the timestamp
            timestampArr = result0.getJSONArray("timestamp");

            //sort the JSON file data into indicators
            JSONObject indicators = result0.getJSONObject("indicators");

            //sort the JSON file data into quote
            JSONArray quote = indicators.getJSONArray("quote");

            //create an object
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

            //print the data
            System.out.println(openArr);
            System.out.println(highArr);
            System.out.println(lowArr);
            System.out.println(closeArr);
            System.out.println(volumeArr);
            System.out.println(timestampArr);


            //catch any errors in the process
        } catch (org.json.JSONException e) {
            //print the error
            e.printStackTrace();
        }
    }
}
