//import libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    static public void main(String[] args) throws Exception {
        //create the url connection
        URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/NVDA?range=3m&interval=1d");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        //bufferread the stocks
        BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;

        while ((line = read.readLine()) != null) {
            System.out.println(line);
        }

        read.close();
    }
}