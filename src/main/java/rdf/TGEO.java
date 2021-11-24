package rdf;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * Defines and creates Resources and Properties that are used in saving and loading
 * RDF files.
 */
public class TGEO {
    public static Resource Slide =  makeTgeoNode("Slide");
    public static Resource Decision = makeTgeoNode("Decision");
    public static Property categorizedAs = makeTgeoProperty("categorizedAs");
    public static Resource isFirst = makeTgeoNode("isFirst");
    public static Property hasDecision = makeTgeoProperty("hasDecision");
    public static Property hasText = makeTgeoProperty("hasText");
    public static Property directsTo = makeTgeoProperty("directsTo");
    public static Property hasXLocation = makeTgeoProperty("hasXLocation");
    public static Property hasYLocation = makeTgeoProperty("hasYLocation");

    /**
     * Create a Node (Resource) using the tgeo prefix
     *
     * @param localName - The name of the resource
     * @return - The created resource
     */
    public static Resource makeTgeoNode(String localName) {
        return Ontology.makeNode(Ontology.prefixes.get("tgeo"), localName);
    }

    /**
     * Create a Node (Resource) using the tgeo prefix
     *
     * @param localName - The name of the resource
     * @return - The created property
     */
    public static Property makeTgeoProperty(String localName) {
        return Ontology.makeProperty(Ontology.prefixes.get("tgeo"), localName);
    }
}
