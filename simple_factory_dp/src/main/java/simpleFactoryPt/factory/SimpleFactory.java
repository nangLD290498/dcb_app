package simpleFactoryPt.factory;

import simpleFactoryPt.Entities.Animal;
import simpleFactoryPt.Entities.Dog;
import simpleFactoryPt.Entities.Tiger;

import java.util.Scanner;

public class SimpleFactory {

    public Animal createAnimal(){
        Animal intendedAnimal = null;
        System.out.println("Enter your choice (0 for Dog, 1 for Tiger)");
        Scanner input =new Scanner(System.in);
        int choice=Integer.parseInt(input.nextLine());
        System.out.println("You have entered: "+ choice);
        switch (choice){
            case 0:
                intendedAnimal = new Dog();
                break;
            case 1:
                intendedAnimal = new Tiger();
                break;
            default:
                System.out.println("You must enter either 0 or 1");
                throw new IllegalArgumentException("Your choice tries to create an unknown animal");
        }
        return intendedAnimal;
    }
}
