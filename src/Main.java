//create a public class Main
public class Main {



    //create the main function
    public static void main(String[] args) throws Exception {
        //read the JSON file data
        String jsonStock = api.jsonReader();

        //convert the JSON file data into usable arrays
        api.JSONtoString(jsonStock);

        //generate dataset
        int[][] dataset = data.generateDataset();


        //generate input data
        double[][] inputData = data.generateInputData();
    }
}