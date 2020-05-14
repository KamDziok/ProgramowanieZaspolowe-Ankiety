package com.Ankiety_PZ.hibernate;
// Generated 2020-04-16 17:33:57 by Hibernate Tools 4.3.1


import javax.persistence.*;
import java.util.List;

/**
 * Uzytkownicy generated by hbm2java
 */
public class Uzytkownicy  implements java.io.Serializable {


     private Integer idUzytkownika;
     private String imie;
     private String nazwisko;
     private String mail;
     private String haslo;
     private int uprawnienia;
     private String miejscowosc;
     private String ulica;
     private String numerBudynku;
     private String numerLokalu;
     private String kodPocztowy;
     private int liczbaPunktow;

    @ManyToMany
    @JoinTable(name = "uzytkownicy_ankiety",
            joinColumns = {@JoinColumn(name = "idUzytkownika")},
            inverseJoinColumns = {@JoinColumn(name = "idAnkiety")})
    private List<Ankiety> ankiety;

    @ManyToMany
    @JoinTable(name = "uzytkownicy_nagrody",
            joinColumns = {@JoinColumn(name = "idUzytkownika")},
            inverseJoinColumns = {@JoinColumn(name = "idNagrody")})
    private List<Nagrody> nagrody;

    @OneToMany(
            mappedBy = "odpowiedzi",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OdpowiedziUzytkownicy> odpowiedziUzytkownicy;

    @OneToMany(
            mappedBy = "pytania",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PytaniaUzytkownicy> pytaniaUzytkownicy;

    public Uzytkownicy() {
    }

	
    public Uzytkownicy(String imie, String nazwisko, String mail, String haslo, int uprawnienia, String miejscowosc, String ulica, String numerBudynku, String kodPocztowy, int liczbaPunktow) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.mail = mail;
        this.haslo = haslo;
        this.uprawnienia = uprawnienia;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.numerBudynku = numerBudynku;
        this.kodPocztowy = kodPocztowy;
        this.liczbaPunktow = liczbaPunktow;
    }
    public Uzytkownicy(String imie, String nazwisko, String mail, String haslo, int uprawnienia, String miejscowosc, String ulica, String numerBudynku, String numerLokalu, String kodPocztowy, int liczbaPunktow) {
       this.imie = imie;
       this.nazwisko = nazwisko;
       this.mail = mail;
       this.haslo = haslo;
       this.uprawnienia = uprawnienia;
       this.miejscowosc = miejscowosc;
       this.ulica = ulica;
       this.numerBudynku = numerBudynku;
       this.numerLokalu = numerLokalu;
       this.kodPocztowy = kodPocztowy;
       this.liczbaPunktow = liczbaPunktow;
    }

    public boolean updatePunkty(int punkty, boolean dodanie) {
        if(dodanie) {
            liczbaPunktow += punkty;
            return true;
        }
        else
            if (liczbaPunktow - punkty >= 0) {
                liczbaPunktow -= punkty;
                return true;
            } else
                return false;
    }
   
    public Integer getIdUzytkownika() {
        return this.idUzytkownika;
    }
    
    public void setIdUzytkownika(Integer id) {
        this.idUzytkownika = id;
    }
    public String getImie() {
        return this.imie;
    }
    
    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getNazwisko() {
        return this.nazwisko;
    }
    
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getHaslo() {
        return this.haslo;
    }
    
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    public int getUprawnienia() {
        return this.uprawnienia;
    }
    
    public void setUprawnienia(int uprawnienia) {
        this.uprawnienia = uprawnienia;
    }
    public String getMiejscowosc() {
        return this.miejscowosc;
    }
    
    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }
    public String getUlica() {
        return this.ulica;
    }
    
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }
    public String getNumerBudynku() {
        return this.numerBudynku;
    }
    
    public void setNumerBudynku(String numerBudynku) {
        this.numerBudynku = numerBudynku;
    }
    public String getNumerLokalu() {
        return this.numerLokalu;
    }
    
    public void setNumerLokalu(String numerLokalu) {
        this.numerLokalu = numerLokalu;
    }
    public String getKodPocztowy() {
        return this.kodPocztowy;
    }
    
    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }
    public int getLiczbaPunktow() {
        return this.liczbaPunktow;
    }
    
    public void setLiczbaPunktow(int liczbaPunktow) {
        this.liczbaPunktow = liczbaPunktow;
    }


    public List<Ankiety> getAnkiety() {
        return ankiety;
    }

    public void setAnkiety(List<Ankiety> ankiety) {
        this.ankiety = ankiety;
    }


    public List<Nagrody> getNagrody() {
        return nagrody;
    }

    public void setNagrody(List<Nagrody> nagrody) {
        this.nagrody = nagrody;
    }


    public List<OdpowiedziUzytkownicy> getOdpowiedziUzytkownicy() {
        return odpowiedziUzytkownicy;
    }

    public void setOdpowiedziUzytkownicy(List<OdpowiedziUzytkownicy> odpowiedziUzytkownicy) {
        this.odpowiedziUzytkownicy = odpowiedziUzytkownicy;
    }


    public List<PytaniaUzytkownicy> getPytaniaUzytkownicy() {
        return pytaniaUzytkownicy;
    }

    public void setPytaniaUzytkownicy(List<PytaniaUzytkownicy> pytaniaUzytkownicy) {
        this.pytaniaUzytkownicy = pytaniaUzytkownicy;
    }
}


