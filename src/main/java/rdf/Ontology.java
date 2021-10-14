package rdf;

import org.apache.jena.rdf.model.*;

import java.util.HashMap;

// Provides easily accessibly maps of common ontology URIs
public class Ontology {
    public static Model m = ModelFactory.createDefaultModel();

    public static HashMap<String, String> prefixes = fillPrefixes();

    public static HashMap<String, String> fillPrefixes() {
        HashMap<String, String> map = new HashMap<>();
        map.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        map.put("tgeo","https://github.com/kdports/TextGameEngine/ontology/");
        map.put("tged", "https://github.com/kdports/TextGameEngine/data/");
        return map;
    }

    public static Resource makeTgedNode(String localName) {
        // Definitely going to be used for saving, not used for loading
        return makeNode(prefixes.get("tged"), localName);
    }

    public static Resource makeNode(String prefix, String localName) {
        return m.createResource(prefix + localName);
    }

    public static Property makeProperty(String prefix, String localName) {
        return m.createProperty(prefix + localName);
    }
}
