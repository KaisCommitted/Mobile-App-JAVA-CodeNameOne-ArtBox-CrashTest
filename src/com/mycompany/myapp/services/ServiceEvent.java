/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceEvent {

    public ArrayList<Evenement> events;

    public static ServiceEvent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public boolean addEvent(Evenement E) {
        
        
        String url = Statics.BASE_URL + "/evenement/json/addEvent?NomEvent=" + E.getNom_event() + "&description=" + E.getDescription() + "&locationEvent=" + E.getLocation_event() + "&typeEvent=" + E.getType_event() + "&categorie=" + E.getCategorie().getCategorie_name() + "&idOrg=" + E.getId_org().getId_user() + "&NbMax=" + E.getNb_max() +"" ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Evenement> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

           
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            
            for (Map<String, Object> obj : list) {
                
                Evenement E = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                E.setId((int) id);
                E.setCategorie((Categorie) obj.get("categorie"));
                E.setDate_event("2025-01-01");
                E.setDescription(obj.get("NomEvent").toString());
                E.setId_org((User) obj.get("idOrg"));
                E.setImage_event("1 (12).jpg");
                E.setLocation_event(obj.get("locationEvent").toString());
                E.setNb_max(Integer.parseInt(obj.get("NbMax").toString()));
                E.setCapacite_event(E.getNb_max());
                E.setRating(0);
                E.setType_event(obj.get("typeEvent").toString());
                E.setNom_event(obj.get("NomEvent").toString());
               
                events.add(E);
            }

        } catch (IOException ex) {

        }
       
        return events;
    }
    
     public ArrayList<Evenement> displayEvents() {
        ArrayList<Evenement> events = new ArrayList<>();
         String url = Statics.BASE_URL + "/evenement/json/displayEvent";
        req.setUrl(url);
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                 Map<String, Object> mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvents.get("root");
                for (Map<String, Object> obj : listOfMaps) {
                   
                    Evenement E = new Evenement();
               
                float id = Float.parseFloat(obj.get("id").toString());
                float nbMax=  Float.parseFloat(obj.get("NbMax").toString());
                E.setId((int) id);
                E.setCategorie((Categorie) obj.get("categorie"));
                E.setDate_event("2025-01-01");
                E.setDescription(obj.get("NomEvent").toString());
                E.setId_org((User) obj.get("idOrg"));
                E.setImage_event("1 (12).jpg");
                E.setLocation_event(obj.get("locationEvent").toString());
                E.setNb_max((int) nbMax);
                E.setCapacite_event(E.getNb_max());
                E.setRating(0);
                E.setType_event(obj.get("typeEvent").toString());
                E.setNom_event(obj.get("NomEvent").toString());
               
                events.add(E);
                
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROOR");
            }
        });
        //  return null;
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/evenement/json/displayEvent";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
}
