public class Window extends Location{

    public Window(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public void itemUsed(String item){
        switch(item) {
            case("LEAD_PIPE"): gameEnd = true; pt("You bang the pipe on the bars loudly. \nIt does not seem to affect them."); pt("\nThe guards catch you trying to escape!!! \nThey reschedule your appointment with the chair. \nYOU DIED"); break;
            case("FILE"): pt("You chisel down the bars"); break;
            default: pt("It has no effect");
        }
    }

}
