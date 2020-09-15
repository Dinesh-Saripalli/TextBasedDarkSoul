public class Vent extends Location {

    static public boolean ventBroken;

    public Vent(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public void itemUsed(String item){
        switch(item) {
            case("SHANK"): ventBroken = true; pt("You cut through the vent coverings"); break;
            default: pt("It has no effect");
        }
    }
}
