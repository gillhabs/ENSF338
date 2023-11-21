import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

/**
 * Graph class
 * @author gillhabs
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

    public void fastSP(GraphNode g) {

        HashMap<GraphNode, Boolean> toBeChecked = new HashMap<GraphNode,Boolean>();
        Map<GraphNode, Integer> currDist = new HashMap<GraphNode, Integer>();
        Map<GraphNode, GraphNode> pred = new HashMap<GraphNode, GraphNode>();
        int count = 0;
        GraphNode lowestVertex = new GraphNode("0");

        for(GraphNode f : contents.keySet()) {
            currDist.put(f, Integer.MAX_VALUE);
            lowestVertex = f;
            pred.put(f, null);
            toBeChecked.put(f, true); 
            if(count == 0){
                currDist.put(f, 0);
            }
            count ++;
        }

        count = 0;
        for(GraphNode h : toBeChecked.keySet()) {
            if(toBeChecked.get(h) == true) {
                if(currDist.get(h) < currDist.get(lowestVertex)) {
                    lowestVertex = h;
                }
            }
            else{}
        }

        toBeChecked.put(lowestVertex, false);
        Set<GraphNode> neighbours = contents.get(lowestVertex);

        for(GraphNode value: neighbours) {
            
            List<GraphNode> weight = Arrays.asList(lowestVertex, value);
            int tempDistance;
            
            if(weights.get(weight) == null){
                tempDistance = currDist.get(value);
                
            }
            else{
                tempDistance = currDist.get(value) + weights.get(weight);
                
            }
        
            if(tempDistance < currDist.get(value)){
                currDist.put(value, tempDistance);
                pred.put(value, lowestVertex);
            }
        }
    }


    public void slowSP(GraphNode g) { 
        PriorityQueue<GraphNode> toBeChecked = new PriorityQueue<GraphNode>();
        Map<GraphNode, Integer> currDist = new HashMap<GraphNode, Integer>();
        Map<GraphNode, GraphNode> pred = new HashMap<GraphNode, GraphNode>();
        GraphNode lowestVertex = g;
        int count = 0;

        for(GraphNode f : contents.keySet()) {
       
            currDist.put(f, Integer.MAX_VALUE);
            pred.put(f, null);
            toBeChecked.add(f); 
            if(count == 0){
                currDist.put(f, 0);
            }
            count ++;
        }


        toBeChecked.remove(lowestVertex);

        if(toBeChecked.size() != 0){

            Set<GraphNode> neighbours = contents.get(lowestVertex);
    
            for(GraphNode value: neighbours) {

                List<GraphNode> weight = Arrays.asList(lowestVertex, value);
                int tempDistance;

                if(weights.get(weight) == null){
                    tempDistance = currDist.get(value);

                }
                else{
                    tempDistance = currDist.get(value) + weights.get(weight);
      
                }
            
                if(tempDistance < currDist.get(value)){
                    currDist.put(value, tempDistance);
                    pred.put(value, lowestVertex);

                }
            }
        }
    }

    public static void main (String[] args) {


        Graph g = Graph.importFromFile("random.dot");

        int count = 0;
        long max = 0;
        long min = Integer.MAX_VALUE;
        long avg = 0;
        long sum = 0;

        ArrayList<Long> slowResults = new ArrayList<>();
        for(GraphNode e : g.getContents().keySet()) {
            count++;
            long startTime = System.currentTimeMillis();
            g.slowSP(e);
            long endTime = System.currentTimeMillis();
            long total = endTime - startTime;
            sum += total;
            if(total > max){max = total;}
            if(total < min){min = total;}
            avg = sum / count;
            slowResults.add(total);
            // if (count % 50 == 0)
            //     System.out.println(startTime + " " + endTime);
            
        }
        System.out.println("Min for slowSP is " + min);
        System.out.println("Max for slowSP is " + max);
        System.out.println("Average for slowSP is " + avg);

        count = 0;
        max = 0;
        min = Integer.MAX_VALUE;
        avg = 0;
        sum = 0;

        ArrayList<Long> fastResults = new ArrayList<>();
        for(GraphNode f : g.getContents().keySet()) {
            count++;
            long startTime = System.currentTimeMillis();
            g.fastSP(f);
            long endTime = System.currentTimeMillis();
            long total = endTime - startTime;
            sum += total;
            if(total > max){max = total;}
            if(total < min){min = total;}
            avg = sum / count;

            fastResults.add(total);
        }

        System.out.println("Min for fastSP is " + min);
        System.out.println("Max for fastSP is " + max);
        System.out.println("Average for fastSP is " + avg);

        // Plotting
        Plot plt = Plot.create();
        plt.hist()
            .add(slowResults)
            .add(fastResults)
            .bins(10)
            .stacked(true);
        plt.xlabel("Distribution");
        plt.ylabel("Time");
        plt.title("Distribution of execution times across all nodes");
        plt.legend().loc("upper right");
        try {
            plt.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * GraphNode class to be used in Graph object
 * Every 'data' is assumed to be unique
 * @author chantaeh
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