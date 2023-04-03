/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.manhattan.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.primefaces.manhattan.domain.Category;
import org.primefaces.manhattan.service.CategoryService;


/**
 *
 * @author masprog
 */
@Getter
@Setter
@NoArgsConstructor
@Named
@ViewScoped
public class CategoryView implements Serializable {

    CategoryService service = new CategoryService();
    
    private List<Category> listCategory = new ArrayList<>();
    
    
    Category category = new Category();
    
    private String name;
    
    
    
    @PostConstruct
    private void start() {
        
        service.listarCategory();
        
        listCategory = service.getListCategory();
      
    
    }
    
  public void saveCategory() {
      
      System.out.println("dado para validar: " + name);
      category.setName(name);
      service.save(category);
  }
    
  
  
        
}
