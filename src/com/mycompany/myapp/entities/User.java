/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

 


 /**
 *
 * @author louay
 */
public class User   {
     private int id_user;
    private String nom;
    private String prenom;
    private String username;
    private String mail;
    private String String_naissance;
    private String pwd_user;
    private String ref_admin;
    private String image;
//TO DO : zid private Labell label; + static current user + profil 
    public User() {
    }

    public User(int id_user, String nom, String ref_admin) {
        this.id_user = id_user;
        this.nom = nom;
        this.ref_admin = ref_admin;
    }

    public User(String nom, String prenom, String username, String mail, String String_naissance, String pwd_user, String ref_admin, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mail = mail;
        this.String_naissance = String_naissance;
        this.pwd_user = pwd_user;
        this.ref_admin = ref_admin;
        this.image = image;
    }

    
    

    public User(String nom, String prenom, String username, String mail, String String_naissance, String pwd_user, String ref_admin) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mail = mail;
        this.String_naissance = String_naissance;
        this.pwd_user = pwd_user;
        this.ref_admin = ref_admin;
    }

    public User(int id_user, String nom, String prenom, String username, String mail,String String_naissance, String pwd_user,  String ref_admin,String image) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mail = mail;
        this.String_naissance = String_naissance;
        this.pwd_user = pwd_user;
        this.ref_admin = ref_admin;
        this.image=image;
    }

    public User(int id_user) {
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getString_naissance() {
        return String_naissance;
    }

    
    public String getPwd_user() {
        return pwd_user;
    }

   
    public String getRef_admin() {
        return ref_admin;
    }
    
        public String getImage() {
        return image;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }   

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setString_naissance(String String_naissance) {
        this.String_naissance = String_naissance;
    }

    
    public void setPwd_user(String pwd_user) {
        this.pwd_user = pwd_user;
    }

    

    public void setRef_admin(String ref_admin) {
        this.ref_admin = ref_admin;
    }
    
     public void setImage(String ref_admin) {
        this.image = ref_admin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", mail=" + mail + ", String_naissance=" + String_naissance + ", pwd_user=" + pwd_user + ", ref_admin=" + ref_admin + ", image=" + image + '}';
    }

   

    
    
    
}
