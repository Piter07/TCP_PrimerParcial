package py.una.entidad;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;

public class MetereoJSON {

    public static void main(String[] args) throws Exception {
        MetereoJSON representacion = new MetereoJSON();

        System.out.println("Ejemplo de uso 1: pasar de objeto a string");
        MetereoDatos m = new MetereoDatos(1L,"VillaElisa",12.5,20,5.0,"12/05/2022","12:00:00");

        String r1 = representacion.objetoString(m);
        System.out.println(r1);


        System.out.println("\n*************************************************************************");
        System.out.println("\nEjemplo de uso 2: pasar de string a objeto");
        String un_string = "{\"id_estacion\":123123,\"ciudad\":\"Asuncion\",\"porcentaje_humedad\":\"12.5\",\"temperatura\":\"10\",\"velocidad_viento\":\"10.3\",\"fecha\":\"13/05/2022\",\"hora\":\"11:00:00\"}";

        MetereoDatos r2 = representacion.stringObjeto(un_string);
        System.out.println(r2.id_estacion + " " + r2.ciudad + " " +r2.porcentaje_humedad + " " + r2.temperatura + " " + r2.velocidad_viento + " " + r2.fecha + " " + r2.hora);

    }


    public static String objetoString(MetereoDatos m) {

        JSONObject obj = new JSONObject();
        obj.put("id_estacion", m.getId_estacion());
        obj.put("ciudad", m.getCiudad());
        obj.put("porcentaje_humedad", m.getPorcentaje_humedad());
        obj.put("temperatura", m.getTemperatura());
        obj.put("velocidad_viento", m.getVelocidad_viento());
        obj.put("fecha", m.getFecha());
        obj.put("hora", m.getHora());

        return obj.toJSONString();
    }


    public static MetereoDatos stringObjeto(String str) throws Exception {
        MetereoDatos m = new MetereoDatos();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long estacion = (Long) jsonObject.get("id_estacion");
        m.setId_estacion(estacion);
        m.setCiudad((String)jsonObject.get("ciudad"));
        m.setPorcentaje_humedad( Double.parseDouble((String)jsonObject.get("porcentaje_humedad")));
        m.setTemperatura(Integer.parseInt((String)jsonObject.get("temperatura")));
        m.setVelocidad_viento(Double.parseDouble((String)jsonObject.get("velocidad_viento")));
        m.setFecha((String)jsonObject.get("fecha"));
        m.setHora((String)jsonObject.get("hora"));

        return m;
    }

}
