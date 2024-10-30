package asmt5;

public class Character {
    private CharacterState currentState;
    private FightingStrategy fightingStrategy;
    private String name;

    public Character(String name) {
        this.name = name;
        this.currentState = new NormalState();
        this.fightingStrategy = new MeleeStrategy();
    }

    public void setState(CharacterState state) {
        this.currentState = state;
        System.out.println(name + " changed state to: " + state.getClass().getSimpleName());
    }

    public void setStrategy(FightingStrategy strategy) {
        this.fightingStrategy = strategy;
        System.out.println(name + " changed strategy to: " + strategy.getClass().getSimpleName());
    }

    public CharacterState getCurrentState() {
        return currentState;
    }

    public FightingStrategy getFightingStrategy() {
        return fightingStrategy;
    }

    public void acceptEffect(EffectVisitor visitor) {
        visitor.applyBoost(this);
    }

    public void performAction() {
        currentState.handleStateTransition(this);
    }
}
