package main.State;

import main.GUI.Button;
import main.input.Input;
import main.General.StateManager;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameState {
    protected StateManager stateManager;
    protected ArrayList<Button> buttons = new ArrayList<>();
    protected Input input;

    protected void createButton(String fileName, Point position, Runnable callback) {
        Button button = new Button(fileName, position, callback);
        buttons.add(button);
    }

    public GameState(StateManager stateManager, Input input) {
        this.stateManager = stateManager;
        this.input = input;
        loadMedia();
    }

    public abstract void render(Graphics g);
    public abstract void update();
    public abstract void loadMedia();
}
