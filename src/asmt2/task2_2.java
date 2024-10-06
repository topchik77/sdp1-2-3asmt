package asmt2;

abstract class ThemeFactory {
    public abstract Button createButton();
    public abstract Checkbox createCheckbox();
}

interface Button {
    void render();
}


interface Checkbox {
    void render();
}

class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Light Button ");
    }
}

class LightCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Light Checkbox ");
    }
}

class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Dark Button ");
    }
}

class DarkCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Dark Checkbox ");
    }
}

class LightThemeFactory extends ThemeFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}

class DarkThemeFactory extends ThemeFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}

public class task2_2 {
    public static void main(String[] args) {
        ThemeFactory lightFactory = new LightThemeFactory();
        Button lightButton = lightFactory.createButton();
        Checkbox lightCheckbox = lightFactory.createCheckbox();
        lightButton.render();
        lightCheckbox.render();

        ThemeFactory darkFactory = new DarkThemeFactory();
        Button darkButton = darkFactory.createButton();
        Checkbox darkCheckbox = darkFactory.createCheckbox();
        darkButton.render();
        darkCheckbox.render();
    }
}
