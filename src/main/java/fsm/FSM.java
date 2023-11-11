package fsm;

import animation.Animation;
import window.Window;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * implements a Finite State Machine for the transition between the animations
 */
public class FSM {
    private final HashMap<StateCategory, ArrayList<State>> categoryMap = new HashMap<>();
    private final HashMap<State, Animation> animationMap = new HashMap<>();
    private final Window window;
    private State currentState;
    private Animation currentAnimation;


    public FSM(Window window, State[] states) throws FileNotFoundException {
        this.window = window;
        for(State state : states){
            if(state.getCategory() == StateCategory.DEFAULT){
                this.currentState = state;
            }
            Animation animation = new Animation(state.getDescription(),state.getCategory().name().toLowerCase(),window);
            animationMap.put(state,animation);
        }

        for(State state : states){
            categoryMap.putIfAbsent(state.getCategory(),new ArrayList<>());
        }

        for(State state : states){
            categoryMap.get(state.getCategory()).add(state);
        }

        this.currentAnimation = animationMap.get(currentState);
    }

    public HashMap<StateCategory, ArrayList<State>> getCategoryMap() {
        return categoryMap;
    }

    public HashMap<State, Animation> getAnimationMap() {
        return animationMap;
    }

    public Window getWindow() {
        return window;
    }

    public State getCurrentState() {
        return currentState;
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void run() {
        currentAnimation.playAnimation();
        while(true){
            if(window.wasClicked()){
                // special case
                currentState = categoryMap.get(StateCategory.MOUSECLICK).get(0);
                currentAnimation = animationMap.get(currentState);
                System.out.println("Switched State: " + currentState);
                currentAnimation.playAnimation();
                window.resetClicked();
            }
            else if (currentState.canTransition()) {
                    State nextState = currentState.transition();
                    if(currentState != nextState){
                        currentState = nextState;
                        currentAnimation = animationMap.get(currentState);
                        System.out.println("Switched State: " + currentState);
                        currentAnimation.playAnimation();
                    }
            }
            if(!window.wasClicked()){ // only sleep if there was no click to make it more responsive
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}
