package service;

import domain.ProductDetails;
import service.promotions.PromotionFactory;
import service.promotions.PromotionSet;

import java.util.List;

public class PromotionService {

    public PromotionService() {
    }

    public void applyPromotion(List<ProductDetails> products, String promotion) {

        PromotionSet promotionSet = PromotionFactory.getPromotion(promotion);
        for (ProductDetails product : products) {
            promotionSet.apply(product);
        }
    }
}
