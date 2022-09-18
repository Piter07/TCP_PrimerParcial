package py.una.server.tcp;

import py.una.bd.MetereoDAO;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TCPMetereoMultiServer {
    
    public static MetereoDAO mdao = new MetereoDAO();

	//variables compartidas
	boolean listening = true;
	List<TCPMetereoServerHilo> hilosClientes; //almacenar los hilos (no se utiliza en el ejemplo, se deja para que el alumno lo utilice)
	List<String> usuarios; //almacenar una lista de usuarios (no se utiliza, se deja para que el alumno lo utilice)
        
        static int puertoServidor = 4444;

    public void ejecutar() throws IOException {
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(puertoServidor);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: "+puertoServidor);
            System.exit(1);
        }
        System.out.println("Puerto abierto: "+4444);

        while (listening) {
        	
            TCPMetereoServerHilo hilo = new TCPMetereoServerHilo(serverSocket.accept(), this);
            hilosClientes.add(hilo);
            hilo.start();
        }

        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        
        puertoServidor = 4444;
        if(args.length == 1){
            puertoServidor = Integer.parseInt(args[0]);
        }else if(args.length > 1){
            System.err.println("ERROR en args");
            System.exit(1);
        }
    	
    	TCPMetereoMultiServer tms = new TCPMetereoMultiServer();
    	
    	tms.hilosClientes = new ArrayList<TCPMetereoServerHilo>();
    	tms.usuarios = new ArrayList<String>();
    	
    	tms.ejecutar();
    	
    }
}
