package py.una.server.tcp;

import java.util.Scanner;
import py.una.bd.Operacion;
import py.una.entidad.MetereoDatos;

import static py.una.entidad.MetereoJSON.objetoString;


public class Menu {
    
    public static void menuError(String error){
        System.err.println(error);
    }
    
    public static Operacion clienteMenu(){
        System.out.println("Pedro Lesme, 22 años");
        System.out.println("Menu Principal");
        System.out.println("1 - Agregar datos metereologicos");
        System.out.println("2 - Consultar la temperatura de una ciudad");
        System.out.println("Cualquier otro caracter para salir");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        String cuerpo = null;
        
        switch (input) {
            case 1:
                MetereoDatos datos = new MetereoDatos();
                
                System.out.println("Id Estacion: ");
                datos.setId_estacion(Long.parseLong(sc.next()));
                System.out.println("Ciudad: ");
                datos.setCiudad( sc.next());
                System.out.println("Porcentaje de Humedad: ");
                datos.setPorcentaje_humedad(Double.parseDouble(sc.next()));
                System.out.println("Temperatura: ");
                datos.setTemperatura(Integer.parseInt(sc.next()));
                System.out.println("Velocidad del Viento: ");
                datos.setVelocidad_viento(Double.parseDouble(sc.next()));
                System.out.println("Fecha: ");
                datos.setFecha( sc.next());
                System.out.println("Hora: ");
                datos.setHora( sc.next());
                
                cuerpo = objetoString(datos);
                break;
            case 2:
                System.out.println("Introduzca la ciudad");
                cuerpo = sc.next();
                break;
            default:
                System.exit(0);
        }
        return new Operacion(input,cuerpo);
    }
    
    public static void menuRespuesta(Operacion respuesta){
        System.out.println();
        System.out.println(respuesta.cuerpo);
        System.out.println();
    }

}
