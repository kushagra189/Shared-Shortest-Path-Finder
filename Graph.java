import java.util.List;
import java.rmi.*;
import java.util.Map;
import java.util.ArrayList;

public interface Graph extends Remote
{
    ArrayList<String> list_graphs() throws RemoteException;
    int shortest_distance(String graph, String node1, String node2) throws RemoteException;
    String add_edge(String graph, String node1, String node2) throws RemoteException;
    String create_graph(String graph) throws RemoteException;
    Map<String, List<String>> get_graph(String graph) throws RemoteException;
} 