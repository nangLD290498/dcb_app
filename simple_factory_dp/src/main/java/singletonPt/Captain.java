package singletonPt;

final class Captain {

    private static Captain captain;

    private Captain(){}

    public static synchronized Captain getCaptain(){
        if(captain == null){
            captain = new Captain();
            System.out.println("New captain is elected for your team");
        }
        else {
            System.out.println("You already have a captain for your team");
            System.out.println("Send him for the toss");
        }
        return captain;
    }
}
