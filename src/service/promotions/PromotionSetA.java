package service.promotions;

import domain.Discount;
import domain.ProductDetails;

class PromotionSetA extends PromotionSet {

    private static final String AFRICA_ORIGIN = "Africa";

    @Override
    public void apply(ProductDetails product){

        String tag = null;
        double amount = 0;
        double discount = 0;

        double price = product.getPrice();

        if(AFRICA_ORIGIN.equals(product.getOrigin())){
            amount = this.getPriceAfterDiscount(price,7);
            tag = "get 7% off";
        }

        if(product.getRating()==2){
            discount = this.getPriceAfterDiscount(price,4);
            if(discount>amount){
                amount = discount;
                tag = "get 4% off";
            }
        }

        if(product.getRating()<2){
            discount = this.getPriceAfterDiscount(price,8);
            if(discount>amount){
                amount = discount;
                tag = "get 8% off";
            }
        }

        if(price >= 500 && ("electronics".equals(product.getCategory()) || "furnishing".equals(product.getCategory()))){
            discount = price - 100;
            if(discount>amount){
                amount = discount;
                tag = "get Rs 100 off";
            }
        }

        if(tag == null && price>1000){
                amount = this.getPriceAfterDiscount(price,2);;
                tag = "get 2% off";
        }

        if(tag != null){
            Discount d = new Discount();
            d.setAmount(amount);
            d.setDiscountTag(tag);
            product.setDiscount(d);
        }
    }
}

