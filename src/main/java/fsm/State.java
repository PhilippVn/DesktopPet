package fsm;

import java.util.ArrayList;
import java.util.Random;

public class State implements StateInterface{
    private final String description;
    private ArrayList<State> neighbours;
    private final boolean isDefaultState;
    private final Random generator = new Random();
    private final StateCategory category;

    public State(String description, ArrayList<State> neighbours, boolean isDefaultState, StateCategory category) {
        this.description = description;
        this.neighbours = neighbours;
        this.isDefaultState = isDefaultState;
        this.category = category;
    }

    public State(String description, boolean isDefaultState, StateCategory category) {
        this.description = description;
        this.isDefaultState = isDefaultState;
        this.category = category;
    }

    public StateCategory getCategory() {
        return category;
    }

    public void setNeighbours(ArrayList<State> neighbours) {
        this.neighbours = neighbours;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "State{" +
                "description='" + description + "'," +
                "category=" + category.name() +
                ", neighbours=" + getNeighboursAsString() +
                ", isDefaultState=" + isDefaultState +
                '}';
    }

    private String getNeighboursAsString(){
        StringBuilder str = new StringBuilder("[");
        int i = 0;
        for(State state : neighbours){
            str.append(state.description);
            if(i++ != neighbours.size() - 1){
                str.append(",");
            }
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public State transition() {
        if(neighbours.isEmpty()){
            throw new IllegalStateException("No transitions possible!");
        }
        // if its the default state it should be very likely to stay in the default state
        if(isDefaultState){
            if(generator.nextInt(101) < 99){
                return this;
            }
        }else
        {
            if(generator.nextInt(101) < 70){
                return this;
            }
        }
        return neighbours.get(generator.nextInt(neighbours.size()));


    }

    @Override
    public ArrayList<State> getPossibleTransitions() {
        return neighbours;
    }

    @Override
    public boolean canTransition() {
        return !getPossibleTransitions().isEmpty();
    }


    @Override
    public boolean isDefaultState() {
        return isDefaultState;
    }
}
