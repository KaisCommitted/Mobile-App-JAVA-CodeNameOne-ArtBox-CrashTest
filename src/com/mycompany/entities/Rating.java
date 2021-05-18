
package com.mycompany.entities;

 

/**
 *test
 * @author Fayechi
 */
    public class Rating  {
    private int id_rating;
    private User id_user;
    private Evenement id_event;
    private int rating;

    public Rating(User id_user, Evenement id_event) {
        this.id_user = id_user;
        this.id_event = id_event;
    }

    public Rating() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Rating(User id_user, Evenement id_event, int rating) {
        this.id_user = id_user;
        this.id_event = id_event;
        this.rating = rating;
    }

    public Rating(int id_rating, User id_user, Evenement id_event, int rating) {
        this.id_rating = id_rating;
        this.id_user = id_user;
        this.id_event = id_event;
        this.rating = rating;
    }
    
    
    
    public Rating(int id_rating) {
        this.id_rating = id_rating;
    }

    public int getId_rating() {
        return id_rating;
    }

    public User getId_user() {
        return id_user;
    }

    public Evenement getId_event() {
        return id_event;
    }

    public void setId_rating(int id_rating) {
        this.id_rating = id_rating;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setId_event(Evenement id_event) {
        this.id_event = id_event;
    }

    

    
  
    
    
}
