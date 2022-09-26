package dcb_app.demo.constant;

public enum OperatorUrlEnum {
    OPERATOR_GENERATE_TOKEN("OPERATOR_GENERATE_TOKEN","/generateToken?service_key={service_key}&service_name={service_name}"),

    OPERATOR_REQUEST("OPERATOR_REQUEST","/carrier/payment");

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
