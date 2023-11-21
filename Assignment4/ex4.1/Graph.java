import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

/**
 * Graph class
 * @author chantaeh
 */
public class Graph {
    private Map<GraphNode, Set<GraphNode>> contents = new HashMap<>();
    private Map<List<GraphNode>, Integer> weights = new HashMap<>();

    public Graph() {

    }

    /**
     * Creates a new GraphNode
     * @param data  The String to store in the node
     * @return  The created GraphNode
     */
    public GraphNode addNode(String data) {
        // If the node already exists, return it
        for (GraphNode key : contents.keySet()) {
            if (key.getData().equals(data)) {
                return key;
            }
        }
        
        // Otherwise, create a new node and add it to contents
        GraphNode node = new GraphNode(data);
        contents.put(node, new TreeSet<GraphNode>());
        return node;
    }

    /**
     * Removes the given GraphNode from the Graph
     * @param node
     */
    public void removeNode(GraphNode node) {
        contents.remove(node);

        // Remove node from sets that it's in
        for(GraphNode currNode : contents.keySet()) {
            if(contents.get(currNode).contains(node)) {
                contents.get(currNode).remove(node);
            }
        }

        // Remove entries from weights map with this node
        Iterator<Map.Entry<List<GraphNode>, Integer>> iter = weights.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<List<GraphNode>, Integer> entry = iter.next();
            entry.getKey().contains(node);
            iter.remove();
        }

    }

    /**
     * Creates an edge between nodes n1 and n2
     * @param n1
     * @param n2
     * @param weight
     */
    public void addEdge(GraphNode n1, GraphNode n2, int weight) {
        // Add edge to both nodes since this is an undirected graph
        contents.get(n1).add(n2);
        contents.get(n2).add(n1);

        List<GraphNode> nodes = new LinkedList<>();
        nodes.add(n1);
        nodes.add(n2);
        weights.put(nodes, weight);

        nodes.clear();
        nodes.add(n2);
        nodes.add(n1);
        weights.put(nodes, weight);
    }

    /**
     * Creates an edge between nodes n1 and n2, no weight provided
     * So weight = 1
     * @param n1
     * @param n2
     */
    public void addEdge(GraphNode n1, GraphNode n2) {
        addEdge(n1, n2, 1);
    }

    /**
     * Removes the edge between nodes n1 and n2
     * @param n1
     * @param n2
     */
    public void removeEdge(GraphNode n1, GraphNode n2) {
        contents.get(n1).remove(n2);
        contents.get(n2).remove(n1);

        List<GraphNode> nodes = new LinkedList<>();
        nodes.add(n1);
        nodes.add(n2);
        weights.remove(nodes);

        nodes.clear();
        nodes.add(n2);
        nodes.add(n1);
        weights.remove(nodes);
    }

    public Map<GraphNode, Set<GraphNode>> getContents() {
        return contents;
    }

    public Map<List<GraphNode>, Integer> getWeights() {
        return weights;
    }

    public String toString() {
        String result = "{";
        for (GraphNode key  : contents.keySet()) {
            result += key.getData();
            result += "=[";
            for (GraphNode node : contents.get(key)) {
                result += node.getData();
                result += ", ";
            }
            if (!contents.get(key).isEmpty()) {
                result = result.substring(0, result.length()-2);
            }
            result += "], ";
        }
        result += "}";
        return result;
    }

    /**
     * imports a graph description from a GraphViz file. 
     * @param file  File name
     * @return  Graph created from file content
     */
    public static Graph importFromFile(String file) {
        Graph g = null;
        File f = new File(file);

        if (f.exists()) {
            ArrayList<String> inputList = new ArrayList<>();
            BufferedReader in = null;
            try {
                // Open a BufferedReader
                in = new BufferedReader(new FileReader(f));
                while(in.ready()) {
                    inputList.add(in.readLine());
                }
            } catch (IOException ioe) {
                return null;
            } finally {
                if (in != null) {
                // Close BufferedReader object
                    try {
                        in.close();
                    }
                    catch (IOException e) {
                        System.out.println("Couldn't close file " + f.getName());
                        return null;
                    }
                }
            }

            // if graph is not undirected, return null
            if (!inputList.get(0).matches("strict graph G \\{[\r\n]*")) {
                return null;
            }

            g = new Graph();

            for(int i = 1; i < inputList.size()-1; i++) {
                // Extract data from the string
                Pattern pattern = Pattern.compile("[\\w]{1,} --");
                Matcher matcher = pattern.matcher(inputList.get(i));
                if (!matcher.find()) {
                    return null;
                }
                String src = matcher.group();
                src = src.substring(0, src.length()-3);

                pattern = Pattern.compile("-- [\\w]{1,}");
                matcher = pattern.matcher(inputList.get(i));
                if (!matcher.find()) {
                    return null;
                }
                String dest = matcher.group();
                dest = dest.substring(3);
                
                int weight = 1;
                pattern = Pattern.compile("\\[[\\w]*weight=[\\d]*[\\w]*\\]");
                matcher = pattern.matcher(inputList.get(i));
                if (matcher.find()) {
                    String strWeight = matcher.group();
                    strWeight = strWeight.substring(1, strWeight.length()-1);
                    strWeight = strWeight.replace("weight=", "");
                    try {
                        weight = Integer.parseInt(strWeight);
                    } catch (Exception e) {
                        return null;
                    }
                }
                GraphNode n1 = g.addNode(src);
                GraphNode n2 = g.addNode(dest);
                g.addEdge(n1, n2, weight);
                
            }

        }
        
        return g;
    }

    public static void main (String[] args) {
        // Graph g1 = new Graph();
        // GraphNode node2 = g1.addNode("B");
        // GraphNode node3 = g1.addNode("C");
        // GraphNode node4 = g1.addNode("D");
        // g1.addEdge(node2, node3, 5);
        // g1.addEdge(node3, node4);

        // System.out.println(g1.toString());
        // System.out.println(g1.getWeights() + "\n");

        // g1.removeNode(node4);
        // System.out.println(g1.toString());
        // g1.removeEdge(node2, node3);
        // System.out.println(g1.toString());
        // System.out.println(g1.getWeights() + "\n");

        // Graph g = Graph.importFromFile("testFile.txt");
        // System.out.println(g.getWeights());
        // System.out.println(g.toString());
    }
}

/**
 * GraphNode class to be used in Graph object
 * Every 'data' is assumed to be unique
 */
class GraphNode implements Comparable<GraphNode>{
    private String data;

    public GraphNode(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public int compareTo(GraphNode g) {
        return (data.compareTo(g.getData()));
    }
}