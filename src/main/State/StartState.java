package main.State;
import enums.StateType;
import main.GUI.Button;
import main.input.Input;
import main.General.StateManager;
import main.GamePanel;

import java.awt.*;

public class StartState extends GameState {
    private boolean wasMousePressed = false;

    public StartState(StateManager stateManager, Input input) {
        super(stateManager,input);
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

    private void handleButtonInputs() {
        Point mousePoint = new Point((int) input.mousePos.x, (int) input.mousePos.y);
        for (Button button : buttons) {
            if (button.rect.contains(mousePoint)) {
                button.checkInput(input.mousePos, input.mousePressed, wasMousePressed);
            }
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
        handleButtonInputs();
        wasMousePressed = input.mousePressed;
    }

    @Override
    public void loadMedia() {
        // Load additional media if needed
    }
}
