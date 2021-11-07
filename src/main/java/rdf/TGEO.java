package rdf;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class TGEO {
    public static Resource Slide =  makeTgeoNode("Slide");
    public static Resource Decision = makeTgeoNode("Decision");
    public static Property categorizedAs = makeTgeoProperty("categorizedAs");
    public static Resource isFirst = makeTgeoNode("isFirst");
    public static Property hasDecision = makeTgeoProperty("hasDecision");
    public static Property hasOrder = makeTgeoProperty("hasOrder");
    public static Property hasText = makeTgeoProperty("hasText");
    public static Property directsTo = makeTgeoProperty("directsTo");

    // EditorGame Terms, not sure which ones are needed or not.
    public static Resource comesFrom = makeTgeoNode("comesFrom"); // where the decision comes from
    public static Property hasID = makeTgeoProperty("hasID"); // something like decisionID as shown in EditorGame
    public static Property hasXLocation = makeTgeoProperty("hasXLocation"); // x location of any slide, decision line...
    public static Property hasYLocation = makeTgeoProperty("hasYLocation"); // ^^
    public static Property goesTo = makeTgeoProperty("goesTo"); // where the decision goes to (may be the same as directsto)
    public static Property hasLeftLine = makeTgeoProperty("hasLeftLine"); // line associated with the decision, as shown in editorgame
    public static Property hasRightLine = makeTgeoProperty("hasRightLine"); // ^^

    public static Resource makeTgeoNode(String localName) {
        return Ontology.makeNode(Ontology.prefixes.get("tgeo"), localName);
    }

    public static Property makeTgeoProperty(String localName) {
        return Ontology.makeProperty(Ontology.prefixes.get("tgeo"), localName);
    }
}
