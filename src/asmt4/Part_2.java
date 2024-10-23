package asmt4;

import java.util.ArrayList;
import java.util.List;

class Memento {
    private final String text;

    public Memento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

class TextEditor {
    private StringBuilder text;
    private History history;

    public TextEditor() {
        this.text = new StringBuilder();
        this.history = new History();
    }

    public void write(String text) {
        saveState();
        this.text.append(text);
    }

    public void clear() {
        saveState();
        text.setLength(0);
    }

    public String getText() {
        return text.toString();
    }

    private void saveState() {
        history.push(new Memento(text.toString()));
    }

    public void undo() {
        Memento memento = history.pop();
        if (memento != null) {
            text = new StringBuilder(memento.getText());
        } else {
            System.out.println("undos unavailable");
        }
    }
}

class History {
    private List<Memento> states;

    public History() {
        states = new ArrayList<>();
    }

    public void push(Memento state) {
        states.add(state);
    }

    public Memento pop() {
        if (!states.isEmpty()) {
            return states.remove(states.size() - 1);
        }
        return null;
    }
}

class Part_2 {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.write("Hello ");
        editor.write("Adil");
        System.out.println("Current text: " + editor.getText());

        editor.undo();
        System.out.println("After undo: " + editor.getText());

        editor.write("teacher");
        System.out.println("New text: " + editor.getText());

        editor.clear();
        System.out.println("After clear: " + editor.getText());

        editor.undo();
        System.out.println("After undo clear: " + editor.getText());
    }
}
