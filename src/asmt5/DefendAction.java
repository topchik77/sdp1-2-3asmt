package asmt5;

public class DefendAction extends GameAction {
    protected void prepareAction() {
        System.out.println("Preparing to defend...");
    }

    protected void performAction(Character character) {
        int defense = character.getCurrentState().defend();
        System.out.println("Defending with " + defense + " points");
    }

    protected void completeAction() {
        System.out.println("Defense completed!");
    }
}
