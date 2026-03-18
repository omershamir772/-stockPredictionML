//import libraries

//import the java.io.BufferedReader to read files
import java.io.BufferedReader;

//import the java.io.InputStreamReader to read from the api
import java.io.InputStreamReader;

//import java.net.HttpURLConnection to create user agent
import java.net.HttpURLConnection;

//import java.net.URL to read the link
import java.net.URL;

//import java.util.Arrays to print arrays
import java.util.Arrays;

//import org.json.* to read the JSON string
import org.json.*;

//create a public class Main
public class Main {

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

    //this public variable is a 2d array that will be used as the input data
    //public static int[][] inputData = new int[2 * (openArr.length() - 30)][5];

    //this function will be used to generate the dataset
    public static int[][] generateDataset() throws Exception {

        //this variable is a 2d array which will be used as the dataset
        int[][] dataset = new int[2 * (openArr.length() - 30)][6];

        //those two for loops is used to create the dataset
        for (int i = 0; i <= openArr.length(); i++) {

            //use an if statement to check if i + 30 exist
            if (i + 30 >= openArr.length() || openArr.isNull(i + 30)) {
                break;
            }

            for (int k = 0; k <= 5; k++) { // k = 0 --> openArr; k = 1 --> highArr; k = 2 --> lowArr; k = 3 --> closeArr; k = 4 --> volumeArr; k = 5 --> timestampArr;

                //this switch&case statement that will check from which variable we should get the data
                switch (k) {

                    //check if k equals to 0 --> openArr
                    case 0:
                        dataset[i][k] = openArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 1 --> highArr
                    case 1:
                        dataset[i][k] = highArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 2 --> lowArr
                    case 2:
                        dataset[i][k] = lowArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 3 --> closeArr
                    case 3:
                        dataset[i][k] = closeArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 4 --> volumeArr
                    case 4:
                        dataset[i][k] = volumeArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 5 --> timestampArr
                    case 5:
                        dataset[i][k] = timestampArr.getInt(i + 30); //in case k == 0 s
                }




            }

        }
        //printing the data
        System.out.println(Arrays.deepToString(dataset));

        //return dataset
        return dataset;
    }

    //create the main function
    public static void main(String[] args) throws Exception {
        //read the JSON file data
        String jsonStock = jsonReader();

        //convert the JSON file data into usable arrays
        JSONtoString(jsonStock);

        //generate dataset
        int[][] dataset = generateDataset();

    }
}