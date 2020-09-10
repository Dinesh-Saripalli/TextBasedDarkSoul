public class Location {
    protected String name;              //Name of Location
    protected String description;       //Description of Location
    protected String investigation;     //What a 'closer look' reveals
    protected String item;              //item contained in location  (change to array for multiple items?)

    //Universal methods
    public static void pt(String word) {
        System.out.println(word);
    }

    public Location(){

    }

    public Location (String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Default 'item used' method that is redefined in instanced classes
    public String itemUsed(String item){
        return "It has no effect";
    }
}
