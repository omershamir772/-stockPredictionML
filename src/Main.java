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



    //this function will be used to generate the dataset
    public static int[][] generateDataset() throws Exception {

        //this variable is a 2d array which will be used as the dataset
        int[][] dataset = new int[2 * (api.openArr.length() - 30)][6];

        //those two for loops is used to create the dataset
        for (int i = 0; i <= api.openArr.length(); i++) {

            //use an if statement to check if i + 30 exist
            if (i + 30 >= api.openArr.length() || api.openArr.isNull(i + 30)) {
                break;
            }

            for (int k = 0; k <= 5; k++) { // k = 0 --> openArr; k = 1 --> highArr; k = 2 --> lowArr; k = 3 --> closeArr; k = 4 --> volumeArr; k = 5 --> timestampArr;

                //this switch&case statement that will check from which variable we should get the data
                switch (k) {

                    //check if k equals to 0 --> openArr
                    case 0:
                        dataset[i][k] = api.openArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 1 --> highArr
                    case 1:
                        dataset[i][k] = api.highArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 2 --> lowArr
                    case 2:
                        dataset[i][k] = api.lowArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 3 --> closeArr
                    case 3:
                        dataset[i][k] = api.closeArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 4 --> volumeArr
                    case 4:
                        dataset[i][k] = api.volumeArr.getInt(i + 30); //in case k == 0 s
                    //check if k equals to 5 --> timestampArr
                    case 5:
                        dataset[i][k] = api.timestampArr.getInt(i + 30); //in case k == 0 s
                }




            }

        }
        //printing the data
        System.out.println(Arrays.deepToString(dataset));

        //return dataset
        return dataset;
    }

    //this function
    public static int[][] generateInputData() throws Exception {
        //this variable is a 2d array that will be used as the input data
        int[][] inputData = new int[2 * (api.openArr.length() - 30)][6];

        //those two for loops is used to create the input data
        for (int i = 0; i <= api.openArr.length() - 30; i++) {

            for (int k = 0; k <= 6; k++) { // k = 0 --> openArr; k = 1 --> highArr; k = 2 --> lowArr; k = 3 --> closeArr; k = 4 --> volumeArr; k = 5 --> timestampArr;

                //this switch&case statement that will check from which variable we should get the data
                switch (k) {

                    //check if k equals to 0 --> openArr
                    //check if k equals to 1 --> highArr
                    case 0:
                        inputData[i][k] = api.openArr.getInt(i); //in case k == 0 s
                        //check if k equals to 1 --> highArr
                    case 1:
                        inputData[i][k] = api.highArr.getInt(i); //in case k == 0 s
                        //check if k equals to 2 --> lowArr
                    case 2:
                        inputData[i][k] = api.lowArr.getInt(i); //in case k == 0 s
                        //check if k equals to 3 --> closeArr
                    case 3:
                        inputData[i][k] = api.closeArr.getInt(i); //in case k == 0 s
                        //check if k equals to 4 --> volumeArr
                    case 4:
                        inputData[i][k] = api.volumeArr.getInt(i); //in case k == 0 s
                        //check if k equals to 5 --> timestampArr
                    case 5:
                        inputData[i][k] = api.timestampArr.getInt(i); //in case k == 0 s
                }

            }

        }
        //print data
        System.out.println(Arrays.deepToString(inputData));
        //return input data
        return inputData;
    }

    //create the main function
    public static void main(String[] args) throws Exception {
        //read the JSON file data
        String jsonStock = api.jsonReader();

        //convert the JSON file data into usable arrays
        api.JSONtoString(jsonStock);

        //generate dataset
        int[][] dataset = generateDataset();


        //generate input data
        int[][] inputData = generateInputData();

    }
}