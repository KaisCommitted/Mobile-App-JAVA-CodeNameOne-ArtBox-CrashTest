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
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvent;
import static com.mycompany.utils.Statics.CurrentUser;
import java.io.IOException;
import java.util.ArrayList;





/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class EventsThisWeek extends BaseForm {

    public EventsThisWeek(Resources res) throws IOException {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("This Week's Events");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("welcome.jpg"), spacer1, "", "", "Events ");
        
                
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
        RadioButton Host = RadioButton.createToggle("Host", barGroup);
       
        HasPassed.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new EventsHasPassed(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });
        
        ThisWeek.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new EventsThisWeek(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });
        
         Host.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new EventAdd(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });
         all.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new EventsHasPassed(res).show();
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
      ThisWeek.setSelected(true);
      HasPassed.setSelected(false);
        Host.setSelected(false);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(ThisWeek, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(ThisWeek, arrow);
        bindButtonSelection(HasPassed, arrow);
        bindButtonSelection(Host, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        //Content
        
         ArrayList<Evenement> list = ServiceEvent.getInstance().displayThisWeekEvents();

       ListEvents(list,res);
       //Content 
       
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
    
   private void addButton(Image img, String title, String org, String dateHosted, String location,String description,Evenement E,Resources res) {
       int height = Display.getInstance().convertToPixels(40f);
       int width = Display.getInstance().convertToPixels(60f);
      
       
       
       Label delete = new Label("Delete my Event");

        delete.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(delete.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f21);

        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        delete.setIcon(supprimerImage);
        delete.setTextPosition(RIGHT);
delete.setAlignment(RIGHT);
        //Onclick delete btn
        delete.addPointerPressedListener(l -> {
            Dialog dig = new Dialog("Delete");
            if (dig.show("Delete", "Are you sure you want to delete this event?", "Cancel", "Yes")) {
                dig.dispose();
            } else {
                dig.dispose();
                //appel fct supp service
                if (ServiceEvent.getInstance().deleteEvent(E.getId())) {
                    try {
                        new EventsThisWeek(res).show();
                    } catch (IOException ex) {
                       
                    }
                }
            }
        });
       /*TextArea delete = new TextArea("Delete my event");
       delete.setUIID("NewsTopLine");
       delete.setEditable(false);
       delete.getAllStyles().setAlignment(TextArea.RIGHT);
       delete.setVisible(false);
        delete.addActionListener((e) -> {
            try {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
                new EventsAll(res).show();
                refreshTheme();
            } catch (IOException ex) {
               
            }
        });*/
       if (org.equals(CurrentUser.getUsername())){delete.setVisible(true);}
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       TextArea HostedBy = new TextArea(org+" is Hosting "+title);
       HostedBy.setUIID("NewsTopLine");
       HostedBy.setEditable(false);
       HostedBy.getAllStyles().setAlignment(TextArea.CENTER);
      HostedBy.getAllStyles().setPaddingTop(5);
       HostedBy.getStyle().set3DText(true, true);
         //HostedBy.getAllStyles().
       Container cnt = BorderLayout.north(HostedBy);
       cnt.setLeadComponent(HostedBy);
       
        SpanLabel underTitle = new SpanLabel(description);
      TextArea date = new TextArea("Happening "+dateHosted);
      date.setUIID("NewsTopLine");
       date.setEditable(false);
       
       //date.setTextPosition(RIGHT);
       
           Style s = new Style(date.getUnselectedStyle());
           s.setFgColor(0xff2d55);
         
       
      
       TextArea locationTxt = new TextArea("At "+location);
      locationTxt.setUIID("NewsTopLine");
       locationTxt.setEditable(false);
      
         cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       delete, 
                       image,
                       
                       
                       underTitle,
                       BoxLayout.encloseX(date, locationTxt)
               ));
        
        
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
   
   public void ListEvents(ArrayList<Evenement> list,Resources res )
   {
    for (Evenement E : list) {
            String urlImage = "1 (19).jpg";
            Image placeHolder = Image.createImage(12, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlimg = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            // addButton(null,E.getId_org().getUsername(),false,0,0);
            // addButton(null,E.getCodeP(),E.getReduction(),E.getDated(),E.getDatef());
            //addButton(placeHolder, E.getNiveau());
            Image logo=null;
            String ppt="";
                   ppt="file://C:/xampp/php/www/ArtBox-CrashTest-WEB/public/imagesEvent/" + E.getImage_event();
                   System.out.println(ppt);
            
        try {
            logo = Image.createImage(ppt).scaledHeight(500);
        } catch (IOException ex) {
           
        }
           
            
            addButton(logo, E.getNom_event(), E.getId_org().getUsername(), E.getDate_event(), E.getLocation_event(),E.getDescription(),E,res);
            
            

            ScaleImageLabel image = new ScaleImageLabel(urlimg);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
   
   
   }
   }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
