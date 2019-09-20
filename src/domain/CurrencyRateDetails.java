package domain;

import java.util.HashMap;
import java.util.Map;

public class CurrencyRateDetails {

    private Map<String, Double> currencies;
    private String base;
    private String date;

    public CurrencyRateDetails(){
        this.currencies = new HashMap();
    }

    public Map<String, Double> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Double> currencies) {
        this.currencies = currencies;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
