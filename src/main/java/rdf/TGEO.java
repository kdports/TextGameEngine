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

    // EditorGame Terms
    public static Property hasXLocation = makeTgeoProperty("hasXLocation"); // x location of any slide, decision line...
    public static Property hasYLocation = makeTgeoProperty("hasYLocation"); // ^^

    public static Resource makeTgeoNode(String localName) {
        return Ontology.makeNode(Ontology.prefixes.get("tgeo"), localName);
    }

    public static Property makeTgeoProperty(String localName) {
        return Ontology.makeProperty(Ontology.prefixes.get("tgeo"), localName);
    }
}
