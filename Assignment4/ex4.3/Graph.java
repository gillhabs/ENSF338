import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.*;

/**
 * Graph class
 * @author chantaeh
 */
public class Graph {
    private Map<GraphNode, Set<GraphNode>> contents = new HashMap<>();
    private Map<ArrayList<GraphNode>, Integer> weights = new HashMap<>();

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
        Iterator<Map.Entry<ArrayList<GraphNode>, Integer>> iter = weights.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<ArrayList<GraphNode>, Integer> entry = iter.next();
            if (entry.getKey().contains(node)) {
                iter.remove();
            }
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

        ArrayList<GraphNode> nodes = new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        weights.put((ArrayList<GraphNode>)nodes.clone(), weight);

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

        ArrayList<GraphNode> nodes = new ArrayList<>();
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

    public Map<ArrayList<GraphNode>, Integer> getWeights() {
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

    private Map<ArrayList<GraphNode>, Integer> getUniqueWeights() {
        // Create a Map of weights that doesn't contain 'duplicates'
        // e.g. B -> C and C -> B (these are 2 representations of the same edge)
        Map<ArrayList<GraphNode>, Integer> weightsCopy = new HashMap<>();
        for (ArrayList<GraphNode> nodes : weights.keySet()) {
            weightsCopy.put(nodes, weights.get(nodes));
        }
        Iterator<Map.Entry<ArrayList<GraphNode>, Integer>> iter = weights.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<ArrayList<GraphNode>, Integer> entry = iter.next();
            ArrayList<GraphNode> arr = entry.getKey();
            ArrayList<GraphNode> reverseArr = new ArrayList<>();
            reverseArr.add(arr.get(1));
            reverseArr.add(arr.get(0));
            if (weightsCopy.keySet().contains(reverseArr)) {
                weightsCopy.remove(arr);
            }
        }
        return weightsCopy;
    }

    private boolean hasCycle() {
        Set<ArrayList<GraphNode>> weightsKeys = getUniqueWeights().keySet();

    /* The following code in the rest of the hasCycle() method was generated by ChatGPT */

        // initialize each node as its own parent
        Map<GraphNode, GraphNode> parent = new HashMap<>();
        for (GraphNode node : contents.keySet()) {
            parent.put(node, node);
        }
    
        // find function to get the ultimate parent of a node
        Function<GraphNode, GraphNode> find = (node) -> {
            GraphNode p = parent.get(node);
            while (p != parent.get(p)) {
                p = parent.get(p);
            }
            parent.put(node, p); // path compression
            return p;
        };
    
        // iterate through each edge
        for (ArrayList<GraphNode> edge : weightsKeys) {
            GraphNode node1 = edge.get(0);
            GraphNode node2 = edge.get(1);
    
            GraphNode parent1 = find.apply(node1);
            GraphNode parent2 = find.apply(node2);
    
            // if the two nodes are already in the same set, there is a cycle
            if (parent1.equals(parent2)) {
                return true;
            }
    
            // otherwise, union the two sets
            parent.put(parent1, parent2);
        }
    
        return false;
    }

    /**
     * Implements the full Kruskal algorithm
     * @return  Graph object which is a minimum spanning tree
     */
    public Graph mst() {
        Map<ArrayList<GraphNode>, Integer> uniqueWeights = getUniqueWeights();
        Graph mstGraph = new Graph();

        // Loop until all nodes are included in the mstGraph
        while (mstGraph.contents.size() < this.contents.size()) {
            ArrayList<GraphNode> minEdge = new ArrayList<>();
            int minWeight = -1;
            // Find the edge with next minimum weight
            for(ArrayList<GraphNode> nodes : uniqueWeights.keySet()) {
                if (minWeight == -1) {
                    minWeight = uniqueWeights.get(nodes);
                    minEdge = nodes;
                }
                if (uniqueWeights.get(nodes) < minWeight) {
                    minEdge = nodes;
                    minWeight = uniqueWeights.get(nodes);
                }
            }
            uniqueWeights.remove(minEdge);

            // Add the edge to the graph
            GraphNode node1 = mstGraph.addNode(minEdge.get(0).getData());
            GraphNode node2 = mstGraph.addNode(minEdge.get(1).getData());
            mstGraph.addEdge(node1, node2, minWeight);

            // Remove the edge from the graph if it creates a cycle
            if(mstGraph.hasCycle()) {
                mstGraph.removeEdge(node1, node2);
            }
        }

        return mstGraph;
    }
    

    public static void main (String[] args) {
        // Test a cyclic path
        // Graph g2 = new Graph();
        // GraphNode node6 = g2.addNode("B");
        // GraphNode node7 = g2.addNode("C");
        // GraphNode node8 = g2.addNode("D");
        // g2.addEdge(node7, node8);
        // g2.addEdge(node6, node7);
        // g2.addEdge(node6, node8);
        // System.out.println(g2.hasCycle());
        // System.out.println(g2.mst());

    //     Graph g3 = new Graph();
    //     GraphNode node_0 = g3.addNode("0");
    //     GraphNode node_3 = g3.addNode("3");
    //     GraphNode node_4 = g3.addNode("4");
    //     GraphNode node_1 = g3.addNode("1");
    //     GraphNode node_2 = g3.addNode("2");
    //     g3.addEdge(node_0, node_3, 3);
    //     g3.addEdge(node_0, node_4, 12);
    //     g3.addEdge(node_3, node_1, 5);
    //     g3.addEdge(node_3, node_2, 3);
    //     g3.addEdge(node_4, node_2, 7);
    //     g3.addEdge(node_1, node_2, 2);
    //     System.out.println("\n" + g3.toString());

    //     Graph g4 = g3.mst();
    //     System.out.println(g4.toString());
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
