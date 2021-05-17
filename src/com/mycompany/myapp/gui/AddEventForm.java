/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceEvent;


/**
 *
 * @author bhk
 */
public class AddEventForm extends Form{

    public AddEventForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddEvenement, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new Event");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Event Name");
        TextField tfOrganizer= new TextField("","Event  Organizer");
        TextField tfType= new TextField("","Event  Type");
        TextField tfCategorie= new TextField("","Event Genre");
         TextField tfDescription= new TextField("","Event Description");
           TextField tfNbMax= new TextField("","Event Capacity");
           TextField tfLocation= new TextField("","Event Location");
           
         TextField tfDate= new TextField("","Event Date");
    
        Button btnValider = new Button("Add Event");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfType.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Categorie C = new Categorie(1, tfCategorie.getText());
                        User CurrentUser = new User();
                        CurrentUser.setId_user(18);
                         Evenement E = new Evenement(CurrentUser, "2023-01-01", tfName.getText(),tfType.getText(), C , tfDescription.getText(), Integer.parseInt(tfNbMax.getText()), Integer.parseInt(tfNbMax.getText()),"1 (5).jpg", tfLocation.getText());
                        
                       
                        if( ServiceEvent.getInstance().addEvent(E))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfCategorie,tfDate,tfDescription,tfLocation,tfNbMax,tfOrganizer,tfType,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
