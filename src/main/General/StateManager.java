package main.General;
import enums.StateType;
import main.State.GameState;
import main.State.PlayState;
import main.State.StartState;

public class StateManager {
    public GameState currentState;

    public void switchState(StateType newStateType) {
        GameState newState = null;

        switch (newStateType) {
            case PLAY:
                newState = new PlayState(this);
                break;
            case MENU:
                newState = new StartState(this);
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
