package service.currency;


import domain.CurrencyRateDetails;
import exception.AmazinKartException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.dataretriever.DataRetriever;

import java.util.*;

public class CurrencyRates {

    DataRetriever dataRetriever;

    private static final String CURRENCY_INFO_INVALID = "Currency and rate information is invalid.";
    private static final String URL = "https://api.exchangeratesapi.io/latest";

    public CurrencyRates(){
        this.dataRetriever = new DataRetriever();
    }

    public CurrencyRateDetails getCurrencies() throws AmazinKartException {
        CurrencyRateDetails currencyRateDetails = new CurrencyRateDetails();

        Map<String, Double> currencies = currencyRateDetails.getCurrencies();

        try {
            String data = this.dataRetriever.getData(URL);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
            currencyRateDetails.setBase((String) jsonObject.get("base"));
            currencyRateDetails.setDate((String) jsonObject.get("date"));
            JSONObject rates = (JSONObject) jsonObject.get("rates");
            for (Currency c :java.util.Currency.getAvailableCurrencies()){
                Object object = rates.get(c.getCurrencyCode());
                if(object != null){
                    currencies.put(c.getCurrencyCode(), this.dataRetriever.getParsedDoubleValue(object));
                }
            }

        } catch (ParseException pe) {
            throw new AmazinKartException(CURRENCY_INFO_INVALID);
        }
        return currencyRateDetails;
    }
}
