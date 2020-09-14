import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;


public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Scanner scan2 = new Scanner(System.in);
    private static int pos= 1;              //Player's 'Room' (Current Scene)
    private static int hour = 16;            // 'Time'
    private static int localPos = 0;        //Player's local position (within a room)
    private static boolean isLive = true;   //true if game is ongoing


    //Array containing basic text to be printed at start / end
    private static String[] text = {
            "Help commands: \n!Objective \n!Commands \n!Locations \n!inventory) ",
            "*********\nYou awake in a prison cell",
            "Explore your cell and get creative to escape before you are sent to deathrow!!!",
            "Explore \nGo (Location) \nInvestigate \nTake(Object) \nUse(Object)",
            "You have run out of time!!! \nThe guards blindfold you and lead you outside. \nYOU DIED"
    };

    private static Location[][] locations = new Location[10][10];

    //Instancing 'Areas' Array that contains all Scenes (represents the map)
    private static Scene[] Areas = new Scene[10];

    //Player's Inventory
    private static ArrayList<String> inventory = new ArrayList<String>();


    public static void main (String args[]) {
        try {
            File xmlFile = new File("ClassesXml.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            NodeList nodeList = doc.getElementsByTagName("Scene");
            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;
                    NodeList nodeList2 = eElement.getElementsByTagName("Location");
                    String desc = eElement.getAttribute("desc");
                    String sceneName = eElement.getAttribute("id");
                    for(int j = 0; j < nodeList2.getLength(); j++) {
                        Node node2 = nodeList2.item(j);
                        if(node2.getNodeType() == Node.ELEMENT_NODE){
                            Element eElement2 = (Element) node2;
                            String name = eElement2.getAttribute("name");
                            String locDesc = eElement2.getAttribute("desc");
                            String invest = eElement2.getAttribute("invest");
                            String item = eElement2.getAttribute("item");
                            String type = eElement2.getAttribute("type");
                            if(item.equals("none")){
                                item = null;
                            }

                            switch(type){
                                case("window"): locations[i][j] = new Window(name, locDesc, invest, item); break;
                                default: locations[i][j] = new Location(name, locDesc, invest, item);
                            }
                        }
                    }
                    Scene newScene = new Scene(locations[i], desc, sceneName);
                    Areas[i] = newScene;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        pt("Type !Help if needed.");
        pt(text[1]);     //starting messages
        while(isLive == true){   //input loop repeats until player runs out of time
            input();
            process();
        }
    }

    public static Node Thing(NodeList nodeList){
        return nodeList.item(0);
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
            pt(text[4]);
            isLive = false;
        }

        for(int i = 0; i < locations.length-1; i++) {
            for(int j = 0; j < locations[i].length-1; j++) {
                if(locations[i][j] != null){
                    if(locations[i][j].gameEnd == true){
                        isLive = false;
                    }
                }
            }
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
                if(next.equals(Areas[pos].contents[localPos].item.toUpperCase())){
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
                if(pos == 0) {

                }
                if(inventory.contains(next)){
                    Areas[pos].contents[localPos].itemUsed(next.toUpperCase()); //calls a location's (unique) 'item used' function
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