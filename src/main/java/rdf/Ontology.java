package rdf;

import org.apache.jena.rdf.model.*;

import java.util.HashMap;

/**
 * Provides easily accessibly maps of common ontology URIs to be referenced
 * in our RDF classes.
 */
public class Ontology {
    public static Model m = ModelFactory.createDefaultModel();

    public static HashMap<String, String> prefixes = fillPrefixes();

    /**
     * Places 3 prefixes that are required to describe our program into hashmaps
     *
     * @return - The hashmap containing these prefixes and URI references
     */
    public static HashMap<String, String> fillPrefixes() {
        HashMap<String, String> map = new HashMap<>();
        map.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        map.put("tgeo", "https://github.com/kdports/TextGameEngine/ontology/");
        map.put("tged", "https://github.com/kdports/TextGameEngine/data/");
        return map;
    }

    /**
     * Create a Node (Resource) based on a prefix and name
     *
     * @param localName - The name of the resource
     * @param prefix - The prefix of the resource (rdf, tgeo, tged)
     * @return - The created resource
     */
    public static Resource makeNode(String prefix, String localName) {
        return m.createResource(prefix + localName);
    }

    /**
     * Create a property based on a prefix and name
     *
     * @param localName - The name of the property
     * @param prefix - The prefix of the property (rdf, tgeo, tged)
     * @return - The created property
     */
    public static Property makeProperty(String prefix, String localName) {
        return m.createProperty(prefix + localName);
    }
}
