package simpleFactoryPt.Entities;

public class Dog  implements Animal{

    public void speak(){
        System.out.println("Dogs say: go go");
    }

    @Override
    public void preferredAction() {
        System.out.println("Dogs prefer barking");
    }
}
