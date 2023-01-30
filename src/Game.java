public class Game {
    private Room currentRoom;
    private Parser parser;
    private Player player;
    private Room house;
    private Room school;
    private Room mall;
    private Room cousinsHouse;
    private Room museum;

    public Game(){
        parser = new Parser();
        player = new Player();
    }
    public static void main(String[]args){
       Game game = new Game();
       game.createRooms();
       game.play();
    }
    private void createRooms(){
         school = new Room("This is the school she attends.", "She is in her junior year at this school, and she attends all 6 periods every day, " +
                "Somewhere in the school is a paper with her schedule. Her locker is in the building." +
                " She has two lockers one for all her books and one for her practice and personal bag," +
                " they are the only two unlocked lockers in the building. ");
        house = new Room("This is where the girl and her family live.", "In this house, she has a few items left behind all around the house" +
                " She is locked up in the museum. Find the key and save her.");
         mall = new Room("The mall in front of her house.", "She visits this mall regularly in her free time whenever she could." +
                " She likes to go to the mall with her friends or family to keep her company." +
                " She has a few favorite stores she always goes to when she's there. " +
                "Find the directory and her favorite places are all highlighted.");
         cousinsHouse = new Room("Where her first cousin on her dad's side lives.", "She'd love to visit her cousin's place all the time since it is close to her house," +
                " her cousin was like a mother figure to her. They'd always hangout at her house whenever they were both free" +
                " with mutual friends or other cousins to bond and spend time together.");
         museum = new Room("The museum is a place where she'd always wanted to go to.", "The museum displays lots of artworks that she was interested in." +
                " She finds joy and comfort in looking at artworks but also feels ashamed and doubtful " +
                "since she thinks she can't be as good as they are.");

        house.setExit("north", mall);
        house.setExit("west", cousinsHouse);
        cousinsHouse.setExit("east", mall);
        cousinsHouse.setExit("south", house);
        cousinsHouse.setExit("west", school);
        mall.setExit("south", house);
        mall.setExit("west", cousinsHouse);
        mall.setExit("east", museum);

        Item letter = new Item();
        Item bed = new Item();
        Item notebook = new Item();
        Item phone = new Item();
        Item iPad = new Item();
        Item map = new Item();
        Item car = new Item();
        Item carKeys = new Item();
        Item roomKey = new Item();
        school.setItem("roomKey", roomKey);
        player.setItem("letter", letter);
        player.setItem("phone", phone);
        cousinsHouse.setItem("iPad",iPad);
        house.setItem("bed", bed);
        school.setItem("notebook", notebook);
        house.setItem("map", map);
        house.setItem("car", car);
        house.setItem("carKeys", carKeys);
        currentRoom = house;

    }
    public void play(){
        printWelcome();
        boolean finished = false;
        while(!finished){
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thanks for playing!");
    }
    private boolean processCommand(Command command){
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord){
            case UNKNOWN:
                System.out.println("I don't know what you mean");
                break;
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                look(command);
                break;
            case GRAB:
                grab(command);
                break;
            case DROP:
                drop(command);
                break;
            case DRIVE:
                drive(command);
                break;
            case OPEN:
                open(command);
                break;
        }
        return wantToQuit;
    }
    public void printHelp(){
        System.out.println("You are lost..");
        System.out.println("You are in a city");
        System.out.println();
        System.out.println("Your command words are: ");
        parser.showCommands();
    }
    private void look(Command command){
        if(command.hasSecondWord()){
            System.out.println("Your can't look at " + command.getSecondWord());
            return;
        }
        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getItemString());
    }
    private void grab(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Grab what?");
            return;
        }
        String key = command.getSecondWord();
        Item grabItem = currentRoom.getItem(key);
        if(grabItem == null){
            System.out.println("You can't grab " + command.getSecondWord());
        }else{
            player.setItem(key, grabItem);
            System.out.println("You grabbed " + key);
        }
    }
    private void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Drop what?");
            return;
        }
        String key = command.getSecondWord();
        Item dropItem = player.getItem(key);
        if(dropItem == null){
            System.out.println("You can't drop " + command.getSecondWord());
        }else{
            currentRoom.setItem(key,dropItem);
            System.out.println("You dropped " + key);
        }
    }
    public void goRoom(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Go where");
            return;
        }
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null){
            System.out.println("There is no door!");
        }else{
            currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());
        }
    }
    public void drive(Command command) {
        if (!player.getInv().containsKey("carKeys")) {
            System.out.println("You need carKeys to drive.");
            return;
        } else {
            String direction = command.getSecondWord();
            Room nextRoom = currentRoom.getExit(direction);
            school.setExit("east", cousinsHouse);
            school.setExit("south", house);
            museum.setExit("west", mall);
            if (nextRoom == null) {
                System.out.println("There is no road!");
            } else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getShortDescription());
            }
        }
    }
    public void open(Command command){
        if(command.hasSecondWord()){
            System.out.println("You can't open "+ command.getSecondWord());
            return;
        }else {

        }
    }
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("You can't quit " + command.getSecondWord());
            return false;
        } else {
            return true;
        }
    }

    private void printWelcome(){
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in front of a house with a mission to accomplish");
        System.out.println("Type\"help\" if you need assistance");
        System.out.println();
        System.out.println("We will print a long room description here");
        System.out.println("Win conditions: ");
    }
}
