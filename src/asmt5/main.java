package asmt5;


interface CharacterState {
    int attack();
    int defend();
    void handleStateTransition(Character character);
}

class NormalState implements CharacterState {
    public int attack() {
        return 20;
    }

    public int defend() {
        return 10;
    }

    public void handleStateTransition(Character character) {
    }
}

class PoweredUpState implements CharacterState {
    private int turnsLeft = 3;

    public int attack() {
        return 40;
    }

    public int defend() {
        return 20;
    }

    public void handleStateTransition(Character character) {
        if (--turnsLeft <= 0) {
            character.setState(new NormalState());
        }
    }
}

class DefeatedState implements CharacterState {
    public int attack() {
        return 0;
    }

    public int defend() {
        return 0;
    }

    public void handleStateTransition(Character character) {
    }
}

interface FightingStrategy {
    void fight(Character character);
}

class MeleeStrategy implements FightingStrategy {
    public void fight(Character character) {
        int damage = character.getCurrentState().attack() * 3 / 2;
        System.out.println("Performing melee attack with damage: " + damage);
    }
}

class RangedStrategy implements FightingStrategy {
    public void fight(Character character) {
        int damage = character.getCurrentState().attack() * 8 / 5;
        System.out.println("Performing ranged attack with damage: " + damage);
    }
}

class MagicStrategy implements FightingStrategy {
    public void fight(Character character) {
        int damage = character.getCurrentState().attack() * 2;
        System.out.println("Performing magic attack with damage: " + damage);
    }
}

interface EffectVisitor {
    void applyBoost(Character character);
    void applyDamage(Character character);
    void applyHeal(Character character);
}

public class main {
    public static void main(String[] args) {
        Character hero = new Character("Hero");

        GameAction attackAction = new AttackAction();
        GameAction defendAction = new DefendAction();
        GameAction healAction = new HealAction();

        EffectVisitor effectVisitor = new StatusEffectVisitor();

        System.out.println("\n-- Normal State with Melee Strategy --");
        attackAction.executeAction(hero);
        defendAction.executeAction(hero);

        System.out.println("\n-- Switching to Ranged Strategy --");
        hero.setStrategy(new RangedStrategy());
        attackAction.executeAction(hero);

        System.out.println("\n-- Applying Power-up Effect --");
        effectVisitor.applyBoost(hero);
        attackAction.executeAction(hero);

        hero.performAction();

        System.out.println("\n-- Switching to Magic Strategy --");
        hero.setStrategy(new MagicStrategy());
        attackAction.executeAction(hero);

        System.out.println("\n-- Applying Damage Effect --");
        effectVisitor.applyDamage(hero);
        defendAction.executeAction(hero);

        System.out.println("\n-- Applying Heal Effect --");
        healAction.executeAction(hero);
    }
}
