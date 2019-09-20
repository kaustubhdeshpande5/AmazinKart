package service.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.ProductDetails;
import exception.AmazinKartException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterService {

    public static void writeToFile(List<ProductDetails> products){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(products);
            FileWriter fileWriter = new FileWriter("ProductDiscount.txt");
            fileWriter.write(jsonString);
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            new AmazinKartException("Not able write data to file.");
        }
    }

}
