package service.dataretriever;

import domain.ProductDetails;
import exception.AmazinKartException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductDataRetriver {

    private static final String PRODUCT_INFO_INVALID = "Product information is invalid.";
    private static final String URL = "https://api.jsonbin.io/b/5d31a1c4536bb970455172ca/latest";
    private DataRetriever dataRetriever;

    public ProductDataRetriver(){
        dataRetriever = new DataRetriever();
    }

    public ArrayList<ProductDetails> getProducts() throws AmazinKartException {

        ArrayList<ProductDetails> products = new ArrayList();
        try{
            String data = this.dataRetriever.getData(URL);
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(data);
            if(jsonArray!=null){
                Iterator<JSONObject> iterator = jsonArray.iterator();
                while(iterator.hasNext()) {
                    JSONObject jsonObject = iterator.next();
                    ProductDetails productDetails = new ProductDetails();
                    productDetails.setCategory((String) jsonObject.get("category"));
                    productDetails.setCurrency((String) jsonObject.get("currency"));
                    productDetails.setInventory((long) jsonObject.get("inventory"));
                    productDetails.setOrigin((String) jsonObject.get("origin"));
                    productDetails.setPrice(this.dataRetriever.getParsedDoubleValue(jsonObject.get("price")));
                    productDetails.setRating(this.dataRetriever.getParsedDoubleValue(jsonObject.get("rating")));
                    productDetails.setProduct((String) jsonObject.get("product"));
                    productDetails.setArrival((String) jsonObject.get("arrival"));
                    products.add(productDetails);
                }
            }
        }catch (ParseException pe){
            throw new AmazinKartException(PRODUCT_INFO_INVALID);
        }
        return products;
    }

}
