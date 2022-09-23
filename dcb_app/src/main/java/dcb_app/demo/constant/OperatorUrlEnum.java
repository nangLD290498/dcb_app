package dcb_app.demo.constant;

public enum OperatorUrlEnum {
    OPERATOR_ORDER("OPERATOR_REQUEST","/carrier/payment");

    private final String name;
    private final String uri;


    OperatorUrlEnum(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getUri(){
        return uri;
    }

    public String getName() {
        return name;
    }
}
