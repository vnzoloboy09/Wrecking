package main.State;
import enums.StateType;
import main.GUI.Button;
import main.General.StateManager;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StartState extends GameState {

    public StartState(StateManager stateManager) {
        super(stateManager);
        createButtons();
    }

    private void createButtons() {
        createButton("Assets/graphics/play.png", new Point(600/2-50, 600), this::goToPlayState);
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

    private void goToPlayState() {
        stateManager.switchState(StateType.PLAY);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Main Menu", 200, 100);

        renderButtons(g);
    }

    @Override
    public void update() {
        // Update if needed
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
