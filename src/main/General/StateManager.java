package main.General;
import enums.StateType;
import main.State.GameState;
import main.State.PlayState;
import main.State.StartState;
import main.input.Input;

public class StateManager {
    public GameState currentState;
    private Input input;

    public StateManager(Input input) {
        this.input = input;
    }

    public void switchState(StateType newStateType) {
        GameState newState = null;

        switch (newStateType) {
            case PLAY:
                newState = new PlayState(this,input);
                break;
            case MENU:
                newState = new StartState(this,input);
                break;
            default:
                System.err.println("Unknown state: " + newStateType);
                break;
        }

        if (newState != null) {
            currentState = newState;
        }
    }
}
