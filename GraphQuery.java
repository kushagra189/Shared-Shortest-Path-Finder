import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

class Pair {
    static <T, U> Map.Entry<T, U> of(T first, U second) {
        return new AbstractMap.SimpleEntry<>(first, second);
    }
}

public class GraphQuery extends UnicastRemoteObject implements Graph {

    private Map<String, Map<String, List<String>>> graphs = new HashMap<>();

    GraphQuery() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<String> list_graphs() throws RemoteException {
        try {
            ArrayList<String> output = new ArrayList<>(graphs.keySet());
            return output;
        }
        catch (Exception e) {
            e.toString();
            return null;
        }
    }

    @Override
    public String create_graph(String graph) throws RemoteException {
        try {
            graphs.put(graph, new HashMap<>());
            return "Graph Created";
        } 
        catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String add_edge(String graph, String node1, String node2) throws RemoteException {
        try {
            try {
                graphs.get(graph).computeIfAbsent(node2, k -> new ArrayList<>());
                graphs.get(graph).computeIfAbsent(node1, k -> new ArrayList<>());
            }
            catch (Exception e) {
                return e.toString();
            }
            graphs.get(graph).get(node2).add(node1);
            graphs.get(graph).get(node1).add(node2);
            return "Edge Added";
        }
        catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public int shortest_distance(String graph, String node1, String node2) throws RemoteException {
        try {
            Map<String, Boolean> visited = new HashMap<>();
            Queue<Map.Entry<String, Integer>> queue = new LinkedList<>();
            ArrayList<String> allKeys = new ArrayList(graphs.get(graph).keySet());
            int i = 0;
            for (;;) {
                if(i == allKeys.size()) {
                    break;
                }
                visited.put(allKeys.get(i), false);
                i = i + 1;
            }
            queue.add(Pair.of(node1, 0));
            for (;;) {
                if(queue.isEmpty()) {
                    break;
                }
                Map.Entry<String, Integer> head = queue.poll();
                String head_key = head.getKey(); 
                if (head_key.equals(node2)) {
                    int val = head.getValue();
                    return val;
                }
                visited.put(head_key, true);
                for (String node : graphs.get(graph).get(head.getKey())) {
                    if (!visited.get(node)) {
                        int val = head.getValue() + 1;
                        queue.add(Pair.of(node, val));
                    }
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Map<String, List<String>> get_graph(String graph) throws RemoteException {
        try {
            String new_graph = graph;
            return graphs.get(new_graph);
        } 
        catch (Exception e) {
            return null;
        }
    }
}