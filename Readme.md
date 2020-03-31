# Distributed Systems Assignment 3

### ***Kushagra Nagori (20161032)***

## How to run

1. Compile all .java files:
   ```
   javac *.java
   ```
2. Start server:
   ```
   java -Djava.rmi.server.hostname=<server_ip> -Djava.security.policy=server.policy Server <port>
   ```
3. Start client:
   ```
   java Client <server_ip> <server_port>
   ```


## Commands and their usage:

1. `list_graphs`

2. `create_graph graph_name`

3. `add_edge graph_name node1 node2`

4. `shortest_distance graph_name node1 node2`

5. `get_graph graph_name`

6. `exit`