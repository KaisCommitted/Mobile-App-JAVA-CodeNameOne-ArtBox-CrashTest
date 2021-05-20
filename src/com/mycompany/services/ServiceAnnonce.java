/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.media.AsyncMedia.MediaErrorType.Network;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Annonce;
import com.mycompany.entities.User;
import com.mycompany.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceAnnonce {

    public ArrayList<Annonce> events;

    public static ServiceAnnonce instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceAnnonce() {
        req = new ConnectionRequest();
    }

    public static ServiceAnnonce getInstance() {
        if (instance == null) {
            instance = new ServiceAnnonce();
        }
        return instance;
    }

    public boolean addJob(Annonce E) {
   
        String url = Statics.BASE_URL + "/annonce/json/addJob?TitreAnn=" + E.getTitre_ann()
                + "&desc_ann=" + E.getDesc_ann()
                + "&pay=" + E.getPay()
                + "&categorie=" + E.getCategorie().getCategorie_name()
                + "&idUser=" + E.getId_user().getId_user()
                + "&ddl=" + E.getDdl_ann();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent ann) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 /*  
 public ArrayList<Annonce> displayThisWeekJobs() {

        String url = Statics.BASE_URL + "/annonce/json/displayThisWeekJobs";
        return displayJobs(url);
    }*/
    public ArrayList<Annonce> displayAllJobs() {

        String url = Statics.BASE_URL + "/annonce/json/displayJob";
        return displayJobs(url);
    }

    public ArrayList<Annonce> displayJobs(String url) {
        ArrayList<Annonce> annonces = new ArrayList<>();

        req.setUrl(url);
        req.addResponseListener((NetworkEvent ann) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();

            try {
                Map<String, Object> mapJobs = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapJobs.get("root");
                for (Map<String, Object> obj : listOfMaps) {

                    Annonce E = new Annonce();

                    float id = Float.parseFloat(obj.get("id").toString());

                    String categorie = obj.get("categorie").toString();
                    categorie = categorie.substring(15, categorie.length() - 1);

                    Categorie C = new Categorie();
                    C.setCategorie_name(categorie);

                    String username = obj.get("idUser").toString();
                    username = username.substring(10, username.length() - 1);

                    User U = new User();
                    U.setUsername(username);

                    E.setId_ann((int) id);
                    E.setDdl_ann(obj.get("date").toString());
                    E.setDesc_ann(obj.get("descAnn").toString());
                    E.setId_user(U);
                    E.setCategorie(C);
                    E.setTitre_ann(obj.get("titreAnn").toString());

                    annonces.add(E);
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        });
        //  return null;
        NetworkManager.getInstance().addToQueueAndWait(req);
        return annonces;
    }

    public ArrayList<Categorie> displayCat(String url) {
        ArrayList<Categorie> Cats = new ArrayList<>();

        req.setUrl(url);
        req.addResponseListener((NetworkEvent ann) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();

            try {
                Map<String, Object> mapJobs = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapJobs.get("root");
                for (Map<String, Object> obj : listOfMaps) {

                    Categorie Cat = new Categorie();

                    Cat.setCategorie_name(obj.get("categorieName").toString());
                    Cat.setCategorie_image(obj.get("categorieImage").toString());
                    Cats.add(Cat);
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        });
        //  return null;
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Cats;
    }

    public ArrayList<Categorie> displayCats() {

        String url = Statics.BASE_URL + "/categorie/json/displayCat";
        return displayCat(url);
    }

     
     
      public boolean deleteJob(int id) {
        String url = Statics.BASE_URL + "annonce/json/deleteJob?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent ann) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }


   
}
