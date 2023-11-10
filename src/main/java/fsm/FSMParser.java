package fsm;


import org.json.simple.JSONArray;


import org.json.simple.JSONObject;


import org.json.simple.parser.*;
import window.Window;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Parses a FSM from a config file
 * @see FSM
 */
public class FSMParser {
   private String path;

    public FSMParser(String path) {
        this.path = path;
    }

    public Config parseConfigFile() throws IOException, ParseException {
        JSONObject configJson = (JSONObject) new JSONParser().parse(new FileReader(path));
        JSONObject dimension = (JSONObject) configJson.get("dimensions");
        int width = ((Long) dimension.get("width")).intValue();
        int height = ((Long) dimension.get("height")).intValue();

        ArrayList<State> statesList = new ArrayList<>();
        JSONArray states = (JSONArray) configJson.get("states");
        for(Object state : states){
            JSONObject s = (JSONObject) state;
            String description = (String) s.get("description");
            String c = ((String) s.get("category")).toUpperCase();
            StateCategory category = StateCategory.valueOf(c);
            boolean isDefaultState = category == StateCategory.DEFAULT;
            State st = new State(description,isDefaultState,category);
            statesList.add(st);
        }

        for(Object state : states) {
            JSONObject s = (JSONObject) state;
            ArrayList<State> transitions = new ArrayList<>();
            for (Object trans : (JSONArray) s.get("transitions")) {
                String t = (String) trans;
                transitions.add(statesList.stream().filter(e -> e.getDescription().equals(t)).findFirst().get());
            }

            statesList.stream().filter(e -> e.getDescription().equals(s.get("description"))).findFirst().get().setNeighbours(transitions);
        }

        State[] statesArray = new State[statesList.size()];
        statesArray = statesList.toArray(statesArray);
        return new Config(statesArray,new Window(width,height));
    }

    public class Config{
        public State[] states;
        public Window window;

        public Config(State[] states, Window window) {
            this.states = states;
            this.window = window;
        }
    }
}
