package prototypePt;

public class PrototypePtExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("***Prototype Pattern Demo***\n");
        BasicCar nano = new Nano("Green Nano");
        nano.basePrice = 100000;

        BasicCar ford = new Ford("Yellow Ford");
        ford.basePrice = 500000;
        //Nano
        BasicCar bc1;
        bc1 = nano.clone();
        bc1.onRoadPrice = nano.basePrice + BasicCar.setAdditionalPrice();
        System.out.println("Car is: " + bc1.modelName + " and its price is Rs." + bc1.onRoadPrice);
        //Ford
        bc1 = ford.clone();
        bc1.onRoadPrice = ford.basePrice + bc1.setAdditionalPrice();
        System.out.println("Car is: " + bc1.modelName + " and its price is Rs." + bc1.onRoadPrice);
    }
}
