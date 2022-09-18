package py.una.entidad;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class MetereoDatos extends MetereoJSON {
    Long id_estacion;
    String ciudad;
    double porcentaje_humedad;
    int temperatura;
    double velocidad_viento;
    String fecha;
    String hora;

    public MetereoDatos() {
    }

    public MetereoDatos(Long id_estacion, String ciudad, double porcentaje_humedad, int temperatura, double velocidad_viento, String fecha, String hora) {
        this.id_estacion = id_estacion;
        this.ciudad = ciudad;
        this.porcentaje_humedad = porcentaje_humedad;
        this.temperatura = temperatura;
        this.velocidad_viento = velocidad_viento;
        this.fecha = fecha;
        this.hora = hora;
    }
    public MetereoDatos(String str) throws Exception {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        this.id_estacion = (Long) jsonObject.get("id_estacion");;
        this.ciudad = (String)jsonObject.get("ciudad");
        this.porcentaje_humedad = Double.parseDouble(jsonObject.get("porcentaje_humedad").toString());
        this.temperatura = Integer.parseInt(jsonObject.get("temperatura").toString());
        this.velocidad_viento =Double.parseDouble(jsonObject.get("velocidad_viento").toString());
        this.fecha = (String)jsonObject.get("fecha");
        this.hora =  (String)jsonObject.get("hora");
    }

    public Long getId_estacion() {
        return id_estacion;
    }

    public void setId_estacion(Long id_estacion) {
        this.id_estacion = id_estacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPorcentaje_humedad() {
        return porcentaje_humedad;
    }

    public void setPorcentaje_humedad(double porcentaje_humedad) {
        this.porcentaje_humedad = porcentaje_humedad;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public double getVelocidad_viento() {
        return velocidad_viento;
    }

    public void setVelocidad_viento(double velocidad_viento) {
        this.velocidad_viento = velocidad_viento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
