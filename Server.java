import java.rmi.registry.*;
import java.rmi.*;

public class Server {
    public static void main(String[] args) {
        try {
            GraphQuery obj = new GraphQuery();
            int registry_val = (args.length > 0) ? Integer.parseInt(args[0]) : 1900;
            LocateRegistry.createRegistry(registry_val);
            String naming = "";
            try {
	            if(args.length > 0){
	            	naming = "rmi://0.0.0.0:" + args[0] + "/graph";
	            }
	            else{
	            	naming = "rmi://0.0.0.0:1900/graph";
	            }
	            Naming.rebind(naming, obj);
            }
            catch (Exception e) {
	            e.printStackTrace();
	        }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
} 