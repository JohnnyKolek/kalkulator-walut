package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class DataProvider implements IDataProvider{
    @Override
    public String acquireData(String address) throws IOException {
        URL url = new URL(address);
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-2"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        String inputLine;
        StringBuilder output = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null)
        {
            output.append(inputLine);
        }

        bufferedReader.close();
        return output.toString();
    }
}
