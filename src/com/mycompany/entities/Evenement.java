package com.mycompany.entities;









/**
 * test
 *
 * @author Fayechi
 */
public class Evenement  {

    private int id;
    private User id_org;
    private String date_event;
    private String nom_event;
    private String type_event;
    private Categorie categorie;
    private String description;
    private int capacite_event;
    private int nb_max;
    private String image_event;
    private String location_event;
    private int rating;
    

    public Evenement() {
    }

    public Evenement(User id_org, String date_event, String nom_event, String type_event, Categorie categorie, String description, int capacite_event, int nb_max, String image_event) {
        this.id_org = id_org;
        this.date_event = date_event;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.categorie = categorie;
        this.description = description;
        this.capacite_event = capacite_event;
        this.nb_max = nb_max;
        this.image_event = image_event;
    }

    public Evenement(User id_org, String date_event, String nom_event, String type_event, Categorie categorie, String description, int capacite_event, int nb_max) {
        this.id_org = id_org;
        this.date_event = date_event;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.categorie = categorie;
        this.description = description;
        this.capacite_event = capacite_event;
        this.nb_max = nb_max;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", id_org=" + id_org + ", date_event=" + date_event + ", nom_event=" + nom_event + ", type_event=" + type_event + ", categorie=" + categorie + ", description=" + description + ", capacite_event=" + capacite_event + ", nb_max=" + nb_max + ", image_event=" + image_event + ", location_event=" + location_event + ", rating=" + rating + '}';
    }

    public String getImage_event() {
        return image_event;
    }

    public void setImage_event(String image_event) {
        this.image_event = image_event;
    }

    public Evenement(User id_org, String date_event, String nom_event, String type_event, Categorie categorie, String description, int capacite_event, int nb_max, String image_event, String location_event) {
        this.id_org = id_org;
        this.date_event = date_event;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.categorie = categorie;
        this.description = description;
        this.capacite_event = capacite_event;
        this.nb_max = nb_max;
        this.image_event = image_event;
        this.location_event = location_event;
    }

    public String getLocation_event() {
        return location_event;
    }

    public void setLocation_event(String location_event) {
        this.location_event = location_event;
    }
    

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
    }

    public Evenement(User id_org, String date_event, String nom_event, String type_event, Categorie categorie, String description) {
        this.id_org = id_org;
        this.date_event = date_event;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.categorie = categorie;
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Evenement(User id_org, String date_event, String nom_event, String type_event, Categorie categorie, String description, int capacite_event) {
        this.id_org = id_org;
        this.date_event = date_event;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.categorie = categorie;
        this.description = description;
        this.capacite_event = capacite_event;
    }

    public Evenement(int id) {
        this.id = id;
    }

    public int getCapacite_event() {
        return capacite_event;
    }

    public User getId_org() {
        return id_org;
    }

    public void setId_org(User id_org) {
        this.id_org = id_org;
    }

    public void setCapacite_event(int capacite_event) {
        this.capacite_event = capacite_event;
    }

    public int getId() {
        return id;
    }

    public String getDate_event() {
        return date_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public String getType_event() {
        return type_event;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static boolean isNotInteger(String str) {
    if (str == null) {
        return true;
    }
    int length = str.length();
    if (length == 0) {
        return true;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
        if (length == 1) {
            return true;
        }
        i = 1;
    }
    for (; i < length; i++) {
        char c = str.charAt(i);
        if (c < '0' || c > '9') {
            return true;
        }
    }
    return false;
}

}
