package main.State;
import enums.StateType;
import main.GUI.Button;
import main.General.StateManager;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PlayState extends GameState {

    public PlayState(StateManager stateManager) {
        super(stateManager);
        createButtons();
    }

    private void createButtons() {
        createButton("Assets/graphics/back.png", new Point(10, 10), this::goToStartState);
    }

    private void renderButtons(Graphics g) {
        for (Button button : buttons) {
            button.render(g);
        }
    }

    private void handleButtonEvents(MouseEvent e) {
        for (Button button : buttons) {
            button.handleEvent(e);
        }
    }

    private void goToStartState() {
        stateManager.switchState(StateType.MENU);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        renderButtons(g);
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
        handleButtonEvents(e);
    }
}
