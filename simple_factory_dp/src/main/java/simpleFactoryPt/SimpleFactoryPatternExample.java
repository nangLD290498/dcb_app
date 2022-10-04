package simpleFactoryPt;

import simpleFactoryPt.Entities.Animal;
import simpleFactoryPt.factory.SimpleFactory;

public class SimpleFactoryPatternExample {
    public static void main(String[] args) {
        System.out.println("Application started");
        Animal preferredAnimal = null;
        SimpleFactory simpleFactory = new SimpleFactory();

        preferredAnimal = simpleFactory.createAnimal();
        preferredAnimal.speak();
        preferredAnimal.preferredAction();
    }
}
