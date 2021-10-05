import java.util.*;

public class Scene {
    public String prompt;
    public ArrayList<Decision> decisions;

    public Scene(String prompt, ArrayList<Decision> decisions) {
        this.prompt = prompt;
        this.decisions = decisions;
    }
}