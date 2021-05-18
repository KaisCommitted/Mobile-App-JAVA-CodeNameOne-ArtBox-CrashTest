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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Evenement;
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
        
        
        String url = Statics.BASE_URL + "/evenement/json/addEvent?NomEvent=" + E.getNom_event() 
                + "&description=" + E.getDescription() 
                + "&locationEvent=" + E.getLocation_event() 
                + "&typeEvent=" + E.getType_event()
                + "&categorie=" + E.getCategorie().getCategorie_name()
                + "&idOrg=" + E.getId_org().getId_user()
                + "&NbMax=" + E.getNb_max()
                + "&date=" + E.getDate_event() 
                 + "&image=" + E.getImage_event(); //création de l'URL
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
//                float nbMax=  Float.parseFloat(obj.get("NbMax").toString());
                E.setId((int) id);
                String date="";
               // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //date= format.format(obj.get("date"));
                E.setDate_event(obj.get("date").toString());
                  
                        // E.setCategorie((Categorie) obj.get("categorie"));
              //  E.setDate_event("2025-01-01");
            
                E.setDescription(obj.get("description").toString());
               // E.setId_org((User) obj.get("idOrg"));
                E.setImage_event(obj.get("imageEvent").toString());
               // E.setLocation_event(obj.get("locationEvent").toString());
                //E.setNb_max((int) nbMax);
                //E.setCapacite_event(E.getNb_max());
                //E.setRating(0);
                //E.setType_event(obj.get("typeEvent").toString());
                E.setNom_event(obj.get("nomEvent").toString());
               
                events.add(E);
                
                }
            } catch (Exception ex) {
                ex.printStackTrace();
             
            }
        });
        //  return null;
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

   
}
