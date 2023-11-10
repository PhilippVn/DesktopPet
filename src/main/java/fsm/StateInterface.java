package fsm;

import java.util.ArrayList;

public interface StateInterface {
    State transition();
    ArrayList<State> getPossibleTransitions();
    boolean canTransition();
    boolean isDefaultState();
}
