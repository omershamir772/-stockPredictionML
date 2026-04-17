//import libraries
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

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
        StringBuilder jsonStock = new StringBuilder();

        //read all the lines and store them js
        while ((line = read.readLine()) != null) {
            jsonStock.append(line);


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

    //create a public int that will store the length of the data
    public static int dataLength;

    //this function is used to convert the JSON file style of writing into string arrays to make them usable for the neural network
    public static void JSONtoString(String jsonStock) throws Exception{

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

            //check if the length is equal
            boolean is_length_equal = Objects.equals(openArr.length(), highArr.length())
                    && Objects.equals(lowArr.length(), closeArr.length())
                    && Objects.equals(volumeArr.length(), timestampArr.length());

            //create an if&else statement that checks if is_length_equal true
            if (is_length_equal) {
                //use the dataLength variable
                dataLength = openArr.length();
            }

            else {
                System.out.println("the data doesn't have the same length check the line between 121 - 134 or the api");
            }


            //print boolean
            System.out.println(is_length_equal);

            //catch any errors in the process
        } catch (org.json.JSONException e) {
            //print the error
            e.printStackTrace();
        }
    }
}
