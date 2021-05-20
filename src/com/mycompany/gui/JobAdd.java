/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Annonce;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceAnnonce;
import static com.mycompany.utils.Statics.CurrentUser;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;




/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class JobAdd extends BaseForm {
    
    
     String path="";
    String imgpath="";
    
      
 
protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
             imgpath=hi;
            return hi;
        } catch (URISyntaxException ex) {
        }
        int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            imgpath=hi;
        return hi;
    }

    public JobAdd(Resources res) throws IOException {
        super("Jobs", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Job Offer");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("welcome.jpg"), spacer1, "", "", "Jobs ");
        
                
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Upcoming", barGroup);
        all.setUIID("SelectBar");
        RadioButton ThisWeek = RadioButton.createToggle("ThisWeek", barGroup);
        ThisWeek.setUIID("SelectBar");
        RadioButton HasPassed = RadioButton.createToggle("Has Passed", barGroup);
        HasPassed.setUIID("SelectBar");
        RadioButton Host = RadioButton.createToggle("Recruit", barGroup);
       /*
        HasPassed.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new JobsHasPassed(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });
        
        
        ThisWeek.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new JobsThisWeek(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });
        */
         Host.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new JobAdd(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });
         all.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new JobsAll(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });
        Host.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, ThisWeek, HasPassed, Host),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(false);
      ThisWeek.setSelected(false);
      HasPassed.setSelected(false);
        Host.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Host, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(ThisWeek, arrow);
        bindButtonSelection(HasPassed, arrow);
        bindButtonSelection(Host, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        ;

      /*  Content */
        TextField tfTitreAnn = new TextField("","Job Title");
         tfTitreAnn.setUIID("TextFieldBalck");
         addStringValue("Job Title", tfTitreAnn);
         
       
         
         
         ComboBox comboCat = new ComboBox();
         addStringValue("Job Category", comboCat);
        List<Categorie> listCat = ServiceAnnonce.getInstance().displayCats();
        for (Categorie cat : listCat) { 
            comboCat.addItem(cat.getCategorie_name());
        }
        
        
        TextField tfDescAnn= new TextField("","Job Description");
         tfDescAnn.setUIID("TextFieldBalck");
         addStringValue("Job Description", tfDescAnn);
        
        TextField tfPay= new TextField("","Salary");
         tfPay.setUIID("TextFieldBalck");
         addStringValue("Job Salary", tfPay);
        
         Picker datePicker =new Picker();
         datePicker.setType(Display.PICKER_TYPE_DATE);
         
         datePicker.setUIID("TextFieldBlack");
         addStringValue("Application Deadline", datePicker);
         
        Button btnAjouter = new Button("Add");
        addStringValue("", btnAjouter);
        btnAjouter.addActionListener((e) -> {

            try {
                 if ((tfTitreAnn.getText().length()==0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                   Categorie C = new Categorie(1, String.valueOf(comboCat.getSelectedItem()));
                        
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String ddl=""; 
                        ddl =format.format(datePicker.getDate());
                         Annonce E = new Annonce(CurrentUser, ddl, tfTitreAnn.getText(),C , tfDescAnn.getText(), Integer.parseInt(tfPay.getText()));
                        

                    System.out.println("data E else gui" + E);
                    // calling adding fct in the services class
                    ServiceAnnonce.getInstance().addJob(E);
                    iDialog.dispose(); //Remove LOADING after adding
                   ToastBar.showMessage("Job sucessfully added", FontImage.MATERIAL_INFO);
                   // new JobsAll(res).show();
               
                    refreshTheme();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("erreur!!! ");
            }

        });
       /* content */
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
        private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
