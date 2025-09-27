package main.General;
import enums.StateType;
import main.State.GameState;

public class StateManager {
    public GameState currentState;

    public void switchState(StateType newStateType) {
        GameState newState = null;

        switch (newStateType) {
            case PLAY:
                break;
            case MENU:
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
