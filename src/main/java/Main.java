import fsm.FSM;
import fsm.FSMParser;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, InvocationTargetException {
        Path configFilePath = Paths.get("","src","main","resources","config.json");
        System.out.println("Config File Path:" + configFilePath.toAbsolutePath());

        FSMParser parser = new FSMParser(configFilePath.toAbsolutePath().toString());
        long start = System.nanoTime();
        FSMParser.Config config = parser.parseConfigFile();
        long end = System.nanoTime();
        System.out.println("Parsed States: (took: " + Math.round((end-start)/1E6) + "ms) ");
        config.window.init();
        // init FSM
        FSM fsm = new FSM(config.window, config.states);
        fsm.run();
    }


}
