import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Scanner scan2 = new Scanner(System.in);
    private static int pos= 0;              //Player's 'Room' (Current Scene)
    private static int hour = 16;            // 'Time'
    private static int localPos = 0;        //Player's local position (within a room)
    private static boolean isLive = true;   //true if game is ongoing


    //Array containing basic text to be printed at start / end
    private static String[] text = {
            "Help commands: \n!Objective \n!Commands \n!Locations \n!inventory) ",
            "*********\nYou awake in a prison cell",
            "Explore your cell and get creative to escape before you are sent to deathrow!!!",
            "Explore \nGo (Location) \nInvestigate \nTake(Object) \nUse(Object)",
            "The guards catch you trying to escape!!! \nThey reschedule your appointment with the chair. \nYOU DIED",
            "The bars are cut through! \nYou crawl through the window. \nYou have successfully escaped!!!",            //temporary win condition
            "You have run out of time!!! \nThe guards blindfold you and lead you outside. \nYOU DIED"
    };

    //Instancing Location classes
    //Specfic locations (like window), are given instanced class (extend location) for unique interactions
    private static Location bed = new Location("bed","bed desc", "bed invest", "file");
    private static Location toilet = new Location("toilet","toilet desc","toilet invest", "lead_pipe");
    private static Window window = new Window("window","window desc","bed invest", null);
    private static Location wall = new Location("wall","wall desc","wall invest", null);
    private static Location door = new Location("door","door desc","door invest", null);
    private static Location ceiling = new Location("ceiling","ceiling desc","ceiling invest", null);



    //Instancing first Scene, contains Locations
    private static Location[] cellLocations = {bed, toilet, window, wall, door};
    private static Scene mainCell = new Scene(cellLocations, "You are sitting sitting on a bed in the far left corner. " +
            "\nIn the opposite corner is a toilet. \nA metal door with a slit opening is the only entrance. " +
            "\nOn the back wall is a window covered with bars.");

    //Second Test Scene
    private static Scene cellHall = new Scene(null, "Cell Hall desc");

    //Instancing 'Areas' Array that contains all Scenes (represents the map)
    private static Scene[] Areas = {mainCell, cellHall};

    //Player's Inventory
    private static ArrayList<String> inventory = new ArrayList<String>();


    public static void main (String args[]) {
        pt("Type !Help if needed.");
        pt(text[1]);     //starting messages
        while(isLive == true){   //input loop repeats until player runs out of time
            input();
            process();
        }
    }

    //Universal methods
    public static void pt(String word) {
        if(isLive == true) {        //feed stops if game is over
            System.out.println(word);
        }
    }

    //processing changes to gameState
    public static void process(){
        if(hour == 0) {
            pt(text[6]);
            isLive = false;
        }
        if(window.alarm == true){    //have to access each individual location?
            pt(text[4]);
            isLive = false;
        }
        if(window.progress == 0){
            pt(text[5]);
            isLive = false;
        }
    }


    //input loop
    public static void input() {
        String line = scan.nextLine().toUpperCase();  //all comparisons are made in upper case
        String pt1 = line;                            //part1 represents entire String, will be searched for an action keyword
        scan2 = new Scanner(line);                    //Scanner looks at each word in the Line
        hour -= 1;                                    //after every action loop, player loses an hour
        process();

        if(pt1.matches("(.*)EXPLORE(.*)") || pt1.matches("(.*)LOOK(.*)")) {
            //If player types a line w/ keywords, will go down that action path
            pt(Areas[pos].description);
        }

        else if(pt1.matches("(.*)GO(.*)") || pt1.matches("(.*)MOVE(.*)"))  {
            boolean valid = false;
            while(scan2.hasNext()) {
                String next = scan2.next();
                if(Areas[pos].contains(next)) {
                    //If Scene contains the location word in a sentence, sends player to its location, gives description of location
                    localPos = Areas[pos].returnLocalPos(next);
                    pt(Areas[pos].contents[localPos].description);
                    valid = true;
                }
            }
            if(valid == false) {        //If none of the words match a valid location, repeats loop
                pt("Enter in a valid location");
                input();
            }
        }

        else if(pt1.matches("(.*)INVESTIGATE(.*)") || pt1.matches("(.*)EXAMINE(.*)")) {
            pt(Areas[pos].contents[localPos].investigation);
        }

        //Adds an item to player's inventory if at correct location
        else if(pt1.matches("(.*)TAKE(.*)") || pt1.matches("(.*)OBTAIN(.*)") || pt1.matches("(.*)PICK(.*)"))  {
            boolean valid = false;
            while(scan2.hasNext()) {
                String next = scan2.next();
                if (Areas[pos].contents[localPos].item == null) {
                    pt("There is no item idiot");
                    valid = true;
                    break;
                } else if (next.equals(Areas[pos].contents[localPos].item.toUpperCase())) {
                    valid = true;
                    if(!inventory.contains(next)) {
                        inventory.add(next);
                    }
                    else{
                        pt("You already took that item idiot");
                    }
                }
            }
            if(valid == false) {
                pt("Enter in a valid object");
                input();
            }
        }

        //Uses an item on the current location
        else if(pt1.matches("(.*)USE(.*)")){
            boolean valid = false;
            while(scan2.hasNext()) {
                String next = scan2.next();
                if(inventory.contains(next)){
                    pt(Areas[pos].contents[localPos].itemUsed(next.toUpperCase())); //calls a location's (unique) 'item used' function
                    valid = true;
                }
            }
            if(valid == false) {
                pt("Enter in a valid object");
                input();
            }
        }

        else if(pt1.matches("(.*)!HELP(.*)")) {
            pt(text[0]);
        }
        else if(pt1.matches("(.*)!OBJECTIVE(.*)")) {
            pt(text[2]);
        }
        else if(pt1.matches("(.*)!COMMANDS(.*)")) {
            pt(text[3]);
        }
        else if(pt1.matches("(.*)!LOCATIONS(.*)")) {
            for (int i = 0; i < Areas[pos].contents.length; i++) {
                pt(Areas[pos].contents[i].name);
            }
        }
        else if(pt1.matches("(.*)!INVENTORY(.*)")) {
            pt(inventory.toString());
        }

        else{
            pt("You sit down and ponder what '" + pt1 + "' means");
        }
    }

}
