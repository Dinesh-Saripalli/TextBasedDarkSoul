public class Button extends Location{

    public Button(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public void itemUsed(String item){
        switch(item) {
            case("LEAD_PIPE"): gameEnd = true; pt("You break the glass surrounding the button and press it down. \nAlarms blare through the prison and chaos ensues!!!. \nYou slip away during the confusion. \nVICTORY"); break;
            case("BAT"): gameEnd = true; pt("You break the glass surrounding the button and press it down. \nAlarms blare through the prison and chaos ensues!!!. \nYou slip away during the confusion. \nVICTORY"); break;
            case("WEIGHT"): gameEnd = true; pt("You break the glass surrounding the button and press it down. \nAlarms blare through the prison and chaos ensues!!!. \nYou slip away during the confusion. \nVICTORY"); break;
            case("KEY"): gameEnd = true; pt("You unlock the glass case surrounding the button and press it down.\nAlarms blare through the prison and chaos ensues!!!. \nYou slip away during the confusion. \nVICTORY"); break;
            case("File"): pt("You make some scratches on the glass case."); break;
            case("Toothbrush"): pt("You brush the glass case aggressively."); break;
            default: pt("It has no effect");
        }
    }

}
