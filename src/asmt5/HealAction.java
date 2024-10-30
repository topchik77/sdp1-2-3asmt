package asmt5;

public class HealAction extends GameAction {
    protected void prepareAction() {
        System.out.println("Preparing to heal...");
    }

    protected void performAction(Character character) {
        StatusEffectVisitor visitor = new StatusEffectVisitor();
        visitor.applyHeal(character);
    }

    protected void completeAction() {
        System.out.println("Healing completed!");
    }
}
