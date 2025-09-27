package main.State;

import main.General.StateManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class GameState {
    protected StateManager stateManager;

    public GameState(StateManager stateManager) {
        this.stateManager = stateManager;
        loadMedia();
    }

    public abstract void render(Graphics g);
    public abstract void update();
    public abstract void loadMedia();
    public abstract void handleEvent(MouseEvent e);
}
