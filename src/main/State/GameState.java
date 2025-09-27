package main.State;

import main.GUI.Button;
import main.General.StateManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class GameState {
    protected StateManager stateManager;
    protected ArrayList<Button> buttons = new ArrayList<>();

    protected void createButton(String fileName, Point position, Runnable callback) {
        Button button = new Button(fileName, position, callback);
        buttons.add(button);
    }

    public GameState(StateManager stateManager) {
        this.stateManager = stateManager;
        loadMedia();
    }

    public abstract void render(Graphics g);
    public abstract void update();
    public abstract void loadMedia();
    public abstract void handleEvent(MouseEvent e);
}
