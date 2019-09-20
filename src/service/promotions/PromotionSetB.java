package service.promotions;

import domain.Discount;
import domain.ProductDetails;

class PromotionSetB extends PromotionSet{

    @Override
    public void apply(ProductDetails product){

        String tag = null;
        double amount = 0;
        double discount = 0;

        double price = product.getPrice();

        if(product.getInventory()>20){
            amount = this.getPriceAfterDiscount(price,12);
            tag = "get 12% off";
        }

        if("NEW".equals(product.getArrival())){
            discount = this.getPriceAfterDiscount(price,7);
            if(discount>amount){
                amount = discount;
                tag = "get 7% off";
            }
        }

        if(tag == null && price>1000){
                amount = this.getPriceAfterDiscount(price,2);
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

