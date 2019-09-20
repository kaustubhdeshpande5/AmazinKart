package constants;

public enum Promotions {

    A("promotionSetA"), B("promotionSetB");

    private String code;

    Promotions(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }


    public boolean isEquals(String value){
        return this.code.equals(value);
    }
}
