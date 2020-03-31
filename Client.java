import java.io.DataInputStream;
import java.rmi.*;
import java.util.Map;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            String ip, port, rmi_str, command;
            
            if(args.length == 2) {
                ip = args[0];
                port = args[1];
            }
            else {
                port = "1900";
                if(args.length == 1) {
                    ip = args[0];
                }
                else {
                    ip = "localhost";
                }
            }
            rmi_str = "rmi://" + ip + ":" + port + "/graph";
            Graph server = (Graph) Naming.lookup(rmi_str);
            DataInputStream input = new DataInputStream(System.in);

            System.out.println("Allowed commands: list_graphs, create_graph, add_edge, shortest_distance, get_graph, exit");
            while (!(command = input.readLine().toLowerCase()).equals("exit")) {
                String[] cs = command.split(" ");
                if(cs[0].equals("list_graphs")) {
                    System.out.println(server.list_graphs());
                }
                else if(cs[0].equals("create_graph")) {
                    String error = (cs.length < 2) ? ("Wrong format. Use : `create_graph graph_name`") : ("");
                    if(!error.equals("")) {
                        System.out.println(error);
                    }
                    else {
                        System.out.println(server.create_graph(cs[1])); 
                    } 
                }
                else if(cs[0].equals("shortest_distance")) {
                    String error = (cs.length<4) ? ("Invalid no. of arguments. Correct usage: `shortest_distance graph_name node1 node2`") : ("");
                    if(!error.equals("")) {
                        System.out.println(error);
                    }
                    else {
                        System.out.println(server.shortest_distance(cs[1], cs[2], cs[3]));
                    }
                }
                else if(cs[0].equals("add_edge")) {
                    String error = (cs.length<4) ? ("Invalid number of arguments. Correct usage: `add_edge graph_name node1 node2`") : ("");
                    if(!error.equals("")) {
                        System.out.println(error);
                    }
                    else {
                        System.out.println(server.add_edge(cs[1], cs[2], cs[3]));
                    }
                }
                else if(cs[0].equals("get_graph")) {
                    String error = "Graph name required. Correct usage: `get_graph graph_name`";
                    if (cs.length < 2) {
                        System.out.println(error);
                    }
                    else {
                        Map<String, List<String>> graph = server.get_graph(cs[1]);
                        if(graph != null) {
                            System.out.println(graph);
                        }
                        else {
                            System.out.println("No Such Graph Exists");
                        }
                    }
                }
                else {
                    System.out.println("Allowed commands: list_graphs, create_graph, add_edge, shortest_distance, get_graph, exit");
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
} 