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
//                

                    String type = obj.get("typeEvent").toString();
                    type = type.substring(10, type.length() - 1);

                    //9a3ed yarjaali bhchichou brichou donc kaadi ncoupi menou ken feli hachti bih eli houwa esm l categorie
                    String categorie = obj.get("categorie").toString();
                    categorie = categorie.substring(15, categorie.length() - 1);

                    Categorie C = new Categorie();
                    C.setCategorie_name(categorie);

                    String username = obj.get("idOrg").toString();
                    username = username.substring(10, username.length() - 1);

                    User U = new User();
                    U.setUsername(username);

                    E.setId((int) id);
                    E.setDate_event(obj.get("date").toString());
                    E.setType_event(type);
                    E.setDescription(obj.get("description").toString());
                    E.setImage_event(obj.get("imageEvent").toString());
                    E.setId_org(U);
                    E.setCategorie(C);
                    E.setNom_event(obj.get("nomEvent").toString());
                    System.out.println(E.toString());
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
