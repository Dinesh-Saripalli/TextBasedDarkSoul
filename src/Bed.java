public class Bed extends Location{

    public Bed(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public void itemUsed(String item){
        switch(item) {
            case("LEAD_PIPE"): pt("You attack the mattress with the pipe. \nIt does not seem to affect it."); break;
            case("BAT"): pt("You attack the mattress with the bat. \nIt does not seem to affect it."); break;
            case("FILE"): pt("You chisel down one of the bed legs."); break;
            case("TOOTHBRUSH"): pt("You brush the mattress vigorously."); break;
            default: pt("It has no effect");
        }
    }

}
