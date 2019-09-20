package service.dataretriever;

import exception.AmazinKartException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class DataRetriever{

    private static final String CONNECTION_FAILED = "Not able to establish connection.";
    private static final String INVALID_URL = "URL is invalid";
    private static final String NO_PRODUCT_INFO_FOUND = "Product information is not found.";

    public String getData(String url) throws AmazinKartException {
        StringBuilder sb = new StringBuilder();

        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();

            BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            if(sb.length()<0){
                throw new AmazinKartException(NO_PRODUCT_INFO_FOUND);
            }

        }
        catch(MalformedURLException me){
            throw new AmazinKartException(INVALID_URL);
        }catch (IOException io){
            throw new AmazinKartException(CONNECTION_FAILED);
        }

        return sb.toString();
    }

    public double getParsedDoubleValue(Object object){
        String className = object.getClass().getName();
        if(className.equals("java.lang.Long")){
            return ((Long) object).doubleValue();
        }else if(className.equals("java.lang.Double")){
            return ((Double) object).doubleValue();
        }
        return 0;
    }

}
