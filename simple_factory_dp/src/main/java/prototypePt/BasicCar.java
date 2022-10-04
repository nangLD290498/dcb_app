package prototypePt;

import java.util.Random;

public class BasicCar implements Cloneable {
    public static int a;
    public String modelName;
    public int basePrice, onRoadPrice;

    @Override
    public BasicCar clone() throws CloneNotSupportedException {
        return (BasicCar) super.clone();
    }

    public static int setAdditionalPrice(){
        int price = 0;
        Random r = new Random();
        int p = r.nextInt(100000);
        price = p;
        return price;
    }



}
