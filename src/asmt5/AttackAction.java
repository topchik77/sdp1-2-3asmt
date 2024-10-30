package asmt5;

public class AttackAction extends GameAction {
    protected void prepareAction() {
        System.out.println("Preparing to attack...");
    }

    protected void performAction(Character character) {
        character.getFightingStrategy().fight(character);
    }

    protected void completeAction() {
        System.out.println("Attack completed!");
    }
}
