package service.promotions;

import constants.Promotions;

public class PromotionFactory {

    private PromotionFactory(){

    }

    public static PromotionSet getPromotion(String promotion){

        PromotionSet promotionSet = null;
        if(Promotions.A.isEquals(promotion)){
            promotionSet = new PromotionSetA();
        }else if (Promotions.B.isEquals(promotion)){
            promotionSet = new PromotionSetB();
        }

        return promotionSet;
    }
}
