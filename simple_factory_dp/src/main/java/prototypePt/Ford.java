package prototypePt;

public class Ford extends BasicCar {
    public int basePrice = 100000;

    public Ford(String m){
        modelName = m;
    }

    @Override
    public BasicCar clone() throws CloneNotSupportedException {
        return (Ford) super.clone();
    }
}
