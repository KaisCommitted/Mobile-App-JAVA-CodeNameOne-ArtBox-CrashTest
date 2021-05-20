package com.mycompany.entities;


/**
 *test
 * @author Fayechi
 */
public class Annonce  {

    private int id_ann;
    private User id_user;
    private String titre_ann;
    private String desc_ann;
    private int pay;
    private Categorie categorie;
    private String ddl_ann;
     
    public Annonce() {
    }
    
    
 public Annonce(User id_user, String ddl_ann, String titre_ann, Categorie categorie, String desc_ann, int pay) {
        this.id_user = id_user;
        this.titre_ann = titre_ann;
        this.desc_ann = desc_ann;
        this.pay = pay;
        this.categorie = categorie;
        this.ddl_ann = ddl_ann;
    }












public Annonce(User id_user, String titre_ann, String desc_ann, int pay, Categorie categorie, String ddl_ann) {
        this.id_user = id_user;
        this.titre_ann = titre_ann;
        this.desc_ann = desc_ann;
        this.pay = pay;
        this.categorie = categorie;
        this.ddl_ann = ddl_ann;
    }

    public Annonce(User IdUser, String rTitreAnn, String rDescAnn, int Pay, String Categorie, String rDdlAnn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getId_ann() {
        return id_ann;
    }
    
    public User getId_user() {
        return id_user;
    }

    public String getTitre_ann() {
        return titre_ann;
    }

    public String getDesc_ann() {
        return desc_ann;
    }

    public int getPay() {
        return pay;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getDdl_ann() {
        return ddl_ann;
    }

    public void setId_ann(int id_ann) {
        this.id_ann = id_ann;
    }
    
    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setTitre_ann(String titre_ann) {
        this.titre_ann = titre_ann;
    }

    public void setDesc_ann(String desc_ann) {
        this.desc_ann = desc_ann;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setDdl_ann(String ddl_ann) {
        this.ddl_ann = ddl_ann;
    }

  
    @Override
    public String toString() {
        return "Annonce{" + "id_ann=" + id_ann + ", id_user=" + id_user + ", titre_ann=" + titre_ann + ", desc_ann=" + desc_ann +  ", pay=" + pay + ", categorie=" + categorie + ", ddl_ann=" + ddl_ann +'}';
    }


    
}
