package py.una.server.tcp;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import py.una.bd.Operacion;
import py.una.entidad.MetereoDatos;

public class TCPMetereoServerHilo extends Thread {

    private Socket socket = null;

    TCPMetereoMultiServer servidor;
    
    public TCPMetereoServerHilo(Socket socket, TCPMetereoMultiServer servidor ) {
        super("TCPServerHilo");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            Operacion recibidoCliente;
            Operacion enviarCliente = new Operacion(1,"Comunicacion TCP establecida");
            out.println(enviarCliente.toJSON());

            while ((inputLine = in.readLine()) != null) {
                recibidoCliente = new Operacion(inputLine);
                enviarCliente = procesarOperacion(recibidoCliente);
                if(enviarCliente == null){
                    break;
                }    
                out.println(enviarCliente.toJSON());
            }
            
            out.close();
            in.close();
            socket.close();
            System.out.println("Finalizando Hilo");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            System.err.println(ex.toString());            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Operacion procesarOperacion(Operacion datoRecibido) throws Exception {
        int tipo_operacion = datoRecibido.tipo_operacion;
        String cuerpo = datoRecibido.cuerpo;
        switch (tipo_operacion) {
            case 1:
                TCPMetereoMultiServer.mdao.insertar( new MetereoDatos(cuerpo));
                return new Operacion(1,"Operacion completada con exito");
            case 2:
                String ciudad = cuerpo;
                String lista = "";
                ArrayList<Integer> aux = TCPMetereoMultiServer.mdao.seleccionarPorCiudad(ciudad);
                for(int i = 0; i< aux.size(); i++){
                        lista = "La temperatura de la " + ciudad +
                                " es : " + aux.get(i);
                }


                return new Operacion(2,lista);
            default:
                break;
        }
        return null;
    }   
}