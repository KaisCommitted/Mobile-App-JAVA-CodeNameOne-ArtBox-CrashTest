
package com.mycompany.myapp.entities;



/**
 *test
 * @author Fayechi
 */
    public class Categorie   {
    private int id;
    private String categorie_name;
    private String categorie_image;

    public String getCategorie_image() {
        return categorie_image;
    }

    public void setCategorie_image(String categorie_image) {
        this.categorie_image = categorie_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie_name() {
        return categorie_name;
    }

    public void setCategorie_name(String categorie_name) {
        this.categorie_name = categorie_name;
    }

    public Categorie(int id) {
        this.id = id;
    }

    public Categorie() {
    }

    public Categorie(int id, String categorie_name) {
        this.id = id;
        this.categorie_name = categorie_name;
    }
  

    
   

    
  
    
    
}
