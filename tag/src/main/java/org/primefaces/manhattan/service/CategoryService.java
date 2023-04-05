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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.primefaces.manhattan.domain.Category;
import org.primefaces.manhattan.util.ApacheHttpClient;

/**
 *
 * @author MAURO.MANUEL
 */
@Getter
@Setter
@NoArgsConstructor
@Named
@ViewScoped
public class CategoryService implements Serializable {

    List<Category> listCategory = new ArrayList<>();
    
    Category category = new Category();


    public void save(Category category) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        
        // Gson g = new Gson();

       String url = "http://localhost:8090/categories";
       


        try {
            
            System.out.println("Dados Convertidos : " + gson.toJson(category));
            
             //System.out.println("gson.toJson(category): " + gson.toJson(category));

            String resusltado = ApacheHttpClient.postRESTAPI(url, gson.toJson(category)).toString();

            System.out.println("resusltado: \n" + resusltado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria Salva com sucesso!" ));

            listarCategory();

            ///category = new Category();

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
    
   
    public void DeleteCategory(Category category) throws IOException{
        HttpClient client = HttpClientBuilder.create().build();
        
         HttpDelete request = new HttpDelete("http://localhost:8090/categories/"+category.getId());
         System.out.println("dados a remover"+request);
         client.execute(request);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria Removida com sucesso: " + category));
           listarCategory();
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
