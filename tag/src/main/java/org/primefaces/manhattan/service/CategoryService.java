/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.manhattan.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.primefaces.manhattan.domain.Category;
import org.primefaces.manhattan.util.ApacheHttpClient;

/**
 *
 * @author MAURO.MANUEL
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryService implements Serializable {

    List<Category> listCategory = new ArrayList<>();

    private Category category = new Category();

    public void save() {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        String url = "localhost:8090/categories";

        try {
            System.out.println("gson.toJson(category): " + gson.toJson(category));

            String resusltado = ApacheHttpClient.postRESTAPI(url, gson.toJson(category)).toString();

            System.out.println("resusltado: \n" + resusltado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvou: " + resusltado));

            listarCategory();

            category = new Category();

        } catch (Exception e) {
        }
    }

    public void listarCategory() {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        String url = "http://localhost:8090/categories";

        try {
            String resultado = ApacheHttpClient.httpGETRequest(url).toString();
            System.out.println("resusltado: \n" + resultado);
            if (!resultado.isEmpty()) {
                listCategory.clear();
                
             JsonArray jsonArray = jsonObjectProvideWithKey(jsonObjectProvide(resultado), "content");
             
                     System.out.println(jsonArray);

                // JSONObject ob = new JSONObject(resusltado,"sds");
                //JSONArray jsonArray = jsonObjectProvideWithKey(object, url)
                //JSONArray jsonArray = JSONArray(JSONArray())
    
                // System.out.println("Tamanho: "+objects.length());
                for (Object object1 : jsonArray) {
                    Category abm = gson.fromJson(object1.toString(), Category.class);
                    listCategory.add(abm);
                }
            }

        } catch (Exception e) {
        }
    }
    

    private JsonArray jsonObjectProvideWithKey(JsonObject object, String key) {
        JsonArray jsonArray = object.getAsJsonArray(key);

        return jsonArray;
    }
    
    
    private JsonObject jsonObjectProvide(String result){
        JsonElement element = new JsonParser().parse(result);
        
        JsonObject object = element.getAsJsonObject();
        
        return object;
    }
    
  

}
