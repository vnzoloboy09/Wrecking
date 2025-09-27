package main.State;
import enums.StateType;
import main.General.StateManager;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PlayState extends GameState {

    public PlayState(StateManager stateManager) {
        super(stateManager);
    }

    private void goToStartState() {
        stateManager.switchState(StateType.MENU);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }

    @Override
    public void update() {
        // Update game logic if needed
    }

    @Override
    public void loadMedia() {
        // Load additional media if needed
    }

    @Override
    public void handleEvent(MouseEvent e) {
    }
}
