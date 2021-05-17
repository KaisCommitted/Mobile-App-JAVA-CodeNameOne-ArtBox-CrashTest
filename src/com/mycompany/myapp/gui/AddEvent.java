/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvent;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.User;
import java.util.List;

/**
 *
 * @author rayen
 */
public class AddEvent extends BaseForm {

    Form current;

    public AddEvent(Resources res) {

        super("Host an Event", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Host an Event");
        getContentPane().setScrollVisible(false);

        tb.addSearchCommand(s -> {
        });

        /**
         * *************************************
         */
        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe, s1, res.getImage("kkj.png"), "", "", res);

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Events", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            new ListeEvents(res).show();
            refreshTheme();
        });
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        /**
         * *********************************
         */
       
        
//        TextField type = new TextField("", "Entrer un type"); 
//        type.setUIID("TextFieldBalck");
//        addStringValue("Type", type);

       
       
       
        TextField tfName = new TextField("","Event Name");
         tfName.setUIID("TextFieldBalck");
         addStringValue("Event name", tfName);
         
        TextField tfOrganizer= new TextField("","Event  Organizer");
         tfOrganizer.setUIID("TextFieldBalck");
         addStringValue("Event Organizer", tfOrganizer);
        
        
        TextField tfType= new TextField("","Event  Type");
         tfType.setUIID("TextFieldBalck");
         addStringValue("Event Type", tfType);
         
        TextField tfCategorie= new TextField("","Event Genre");
         tfCategorie.setUIID("TextFieldBalck");
         addStringValue("Event Genre", tfCategorie);
        
        TextField tfDescription= new TextField("","Event Description");
         tfDescription.setUIID("TextFieldBalck");
         addStringValue("Event Description", tfDescription);
        
        TextField tfNbMax= new TextField("","Event Capacity");
         tfNbMax.setUIID("TextFieldBalck");
         addStringValue("Event Capacity", tfNbMax);
        
        TextField tfLocation= new TextField("","Event Location");
         tfLocation.setUIID("TextFieldBalck");
         addStringValue("Event Location", tfLocation);

       

        Button btnAjouter = new Button("Add");
        addStringValue("", btnAjouter);
        btnAjouter.addActionListener((e) -> {

            try {
                 if ((tfName.getText().length()==0)||(tfType.getText().length()==0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                   Categorie C = new Categorie(1, tfCategorie.getText());
                        User CurrentUser = new User();
                        CurrentUser.setId_user(18);
                         Evenement E = new Evenement(CurrentUser, "2023-01-01", tfName.getText(),tfType.getText(), C , tfDescription.getText(), Integer.parseInt(tfNbMax.getText()), Integer.parseInt(tfNbMax.getText()),"1 (5).jpg", tfLocation.getText());
                        

                    System.out.println("data E else gui" + E);
                    // calling adding fct in the services class
                    ServiceEvent.getInstance().addEvent(E);
                    iDialog.dispose(); //Remove LOADING after adding
                    new ListeEvents(res).show();
                    refreshTheme();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("erreur!!! ");
            }

        });
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverlay");
        Container page1
                = LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        //nooo
                                        spacer
                                )
                        )
                );
        swipe.addTab("", res.getImage("kkj.png"), page1);
    }

    public void bindButtonSelection(Button btn, Label l) {
        btn.addActionListener(e
                -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        }
        );
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();;
    }

}
