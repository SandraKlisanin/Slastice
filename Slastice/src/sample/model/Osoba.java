package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Osoba {
    private int ID;
    private String Ime;
    private String Prezime;
    private int JMBG;
    private String Adresa;
    private int Telefon;
    private String vrsta_korisnika;
    private String Korisnicko_ime;
    private String Lozinka;

    public Osoba(int ID, String Ime, String Prezime, int JMBG, String Adresa, int Telefon, String vrsta_korisnika, String Korisnicko_ime, String Lozinka) {
        this.ID = ID;
        this.Ime = Ime;
        this.Prezime = Prezime;
        this.JMBG = JMBG;
        this.Adresa = Adresa;
        this.Telefon = Telefon;
        this.vrsta_korisnika=vrsta_korisnika;
        this.Korisnicko_ime = Korisnicko_ime;
        this.Lozinka = Lozinka;
    }

    public int getID() {
        return ID;
    }

    public String getIme() {
        return Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public int getJMBG() {
        return JMBG;
    }

    public String getAdresa() {
        return Adresa;
    }

    public int getTelefon() {
        return Telefon;
    }
     public String getvrsta_korisnika() {
        return vrsta_korisnika;
    }

    public String getKorisnicko_ime() {
        return Korisnicko_ime;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public static ObservableList<Osoba> listaOsoba(){
        ObservableList<Osoba> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `osoba`");

        try {
            while (rs.next()) {
                lista.add(new Osoba(rs.getInt("ID"), rs.getString("Ime"), rs.getString("Prezime"), rs.getInt("JMBG"),  rs.getString("Adresa"),  rs.getInt("Telefon"),rs.getString("vrsta_korisnika"), rs.getString("Korisnicko_ime"),  rs.getString("Lozinka")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
}
