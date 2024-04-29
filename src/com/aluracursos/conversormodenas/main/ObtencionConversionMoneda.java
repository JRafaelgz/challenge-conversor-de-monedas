package com.aluracursos.conversormodenas.main;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ObtencionConversionMoneda {
    public void resultado(String monedaEntrada, String monedaSalida,Double valor){
        try {
            //Se hace llamado a API con la moneda de la cual se desea obtener la conversion
            ConsultaAPI consulta = new ConsultaAPI();
            String json = consulta.consultaConversion(monedaEntrada);

            /*
            Usando caracteristicas de Gson, usamos JsonParser para analizar el json para despues convertirlo
            y poder obtener las relaciones de conversión
            */
            JsonElement jsonElement = JsonParser.parseString(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            //Obtenemos la relacion de conversión
            Double relacion = jsonObject.getAsJsonObject("conversion_rates").get(monedaSalida).getAsDouble();

            //Conversion que se mostrara al usuario:
            System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n", valor, monedaEntrada, valor * relacion, monedaSalida);
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicacion.");
        }
    }
}