package service.promotions;

import domain.ProductDetails;

public abstract class PromotionSet {

    abstract public void apply(ProductDetails product);

    public double getPriceAfterDiscount(double price, double discount){
        return price - price*(discount/100);
    }
}
