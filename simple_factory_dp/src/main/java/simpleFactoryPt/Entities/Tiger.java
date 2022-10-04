package simpleFactoryPt.Entities;

public class Tiger implements Animal{
    @Override
    public void speak() {
        System.out.println("Tigers say: Hummm");
    }

    @Override
    public void preferredAction() {
        System.out.println("Tigers prefer hunting...");
    }
}
