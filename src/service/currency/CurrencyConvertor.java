package service.currency;

import domain.CurrencyRateDetails;
import domain.ProductDetails;
import exception.AmazinKartException;

import java.util.List;
import java.util.Map;

public class CurrencyConvertor {

    private static final String NO_CONVERSION_INFO_AVAILABLE = "No rate information available for currency conversion.";
    private static final String RUPEE = "INR";
    CurrencyRates currencyRates = new CurrencyRates();


    public void convertToIndianRupees(List<ProductDetails> products) throws AmazinKartException {

        CurrencyRateDetails currencyRateDetails = currencyRates.getCurrencies();

        if(currencyRateDetails.getCurrencies().isEmpty()){
            throw  new AmazinKartException(NO_CONVERSION_INFO_AVAILABLE);
        }
        Map<String, Double> rates = currencyRateDetails.getCurrencies();
        if(products.size()>0){
            for (ProductDetails p: products){
                if(!RUPEE.equals(p.getCurrency())){
                    double rate= rates.get(p.getCurrency());
                    double value = rate * p.getPrice();
                    p.setPrice(value);
                    p.setCurrency(RUPEE);
                }
            }
        }
    }
}
