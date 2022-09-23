package dcb_app.demo.constant;

public enum SPUrlEnum {
    SP_REQUEST("SP_REQUEST","/api/transaction/charge");

    private final String name;
    private final String uri;


    SPUrlEnum(String name, String uri) {
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