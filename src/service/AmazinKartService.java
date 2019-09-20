package service;

import constants.Promotions;
import domain.ProductDetails;
import exception.AmazinKartException;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import service.currency.CurrencyConvertor;
import service.dataretriever.ProductDataRetriver;
import service.report.WriterService;

import java.util.List;

public class AmazinKartService {

    private static final String InvalidPromotionSet = "A valid promotion set is not found.";

    private static ProductDataRetriver productDataRetriver;
    private static CurrencyConvertor currencyConvertor;
    private static PromotionService promotionService;
    private static WriterService writerService;

    static{
        productDataRetriver = new ProductDataRetriver();
        currencyConvertor = new CurrencyConvertor();
        promotionService = new PromotionService();
        writerService = new WriterService();
    }

    public static void main(String args[]){
        if(args.length<0){
            System.out.println(InvalidPromotionSet);
            return;
        }
        String promotion = args[0];

        if(!(Promotions.A.isEquals(promotion)||Promotions.B.isEquals(promotion))){
            System.out.println(InvalidPromotionSet);
            return;
        }
       try {
           List<ProductDetails> products = productDataRetriver.getProducts();
           currencyConvertor.convertToIndianRupees(products);
           promotionService.applyPromotion(products,promotion);
           writerService.writeToFile(products);

           System.out.println("PromotionSet applied successfully.");


       }catch (AmazinKartException e){
           System.out.println("Request failed "+e.getMessage());
       }
    }


}
