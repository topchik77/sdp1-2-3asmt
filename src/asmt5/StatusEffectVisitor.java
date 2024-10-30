package asmt5;

public class StatusEffectVisitor implements EffectVisitor {
    public void applyBoost(Character character) {
        System.out.println("Applying power boost!");
        character.setState(new PoweredUpState());
    }

    public void applyDamage(Character character) {
        System.out.println("Applying damage effect!");
        if (character.getCurrentState().defend() <= 0) {
            character.setState(new DefeatedState());
        }
    }

    public void applyHeal(Character character) {
        if (character.getCurrentState() instanceof DefeatedState) {
            System.out.println("Healing character from defeated state!");
            character.setState(new NormalState());
        } else {
            System.out.println("Healing has no effect on healthy character.");
        }
    }
}
