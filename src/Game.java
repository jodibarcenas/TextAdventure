public class Game {
    private Room currentRoom;
    public Game(){
    }
    public static void main(String[]args){
       Game game = new Game();
       game.createRooms();
       game.play();
    }
    private void createRooms(){
        Room school = new Room("This is the school she attends.");
        Room house = new Room("This is where the girl and her family live.");
        Room mall = new Room("The mall in front of her house.");
        Room cousinsHouse = new Room("Where her first cousin on her dad's side lives.");
        Room museum = new Room("A place that she'd always want to go to");
    }
    public void play(){
        printWelcome();
        boolean finished = false;
        while(!finished){
        }
        System.out.println("Thanks for playing!");
    }
    private void printWelcome(){
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("you will find yourself in front of a house with a mission to accomplish");
        System.out.println("type\"help\" if you need assistance");
        System.out.println();
        System.out.println("we will print a long room description here");
    }
}
