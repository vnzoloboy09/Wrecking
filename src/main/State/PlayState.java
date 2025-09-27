package main.State;
import enums.StateType;
import main.GUI.Button;
import main.input.Input;
import main.General.StateManager;
import main.GamePanel;

import java.awt.*;

public class PlayState extends GameState {
    private boolean wasMousePressed = false;

    public PlayState(StateManager stateManager, Input input) {
        super(stateManager,input);
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

    private void handleButtonInputs() {
        Point mousePoint = new Point((int) input.mousePos.x, (int) input.mousePos.y);
        for (Button button : buttons) {
            if(button.rect.contains(mousePoint)) {
                button.handleEvent();
            }
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
        if (input.mousePressed && !wasMousePressed) {
            handleButtonInputs();
        }
        wasMousePressed = input.mousePressed;
    }

    @Override
    public void loadMedia() {
        // Load additional media if needed
    }
}
