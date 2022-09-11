package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Recepti
{
    private int ID ;
    private String Naziv;
    private String Sastojci;
    private String Priprema;

    public Recepti(int ID, String Naziv,String Sastojci, String Priprema){
        this.ID=ID;
        this.Naziv=Naziv;
        this.Sastojci =Sastojci;
        this.Priprema =Priprema;
    }
    public int getID() {
        return ID;
    }
    public String getNaziv() {
        return Naziv;
    }
    public String getSastojci() {
        return Sastojci;
    }
    public String getPriprema(){
        return Priprema;
    }

    public static ObservableList<Recepti> listaRecepata(){
        ObservableList<Recepti> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `recepti`");

        try {
            while (rs.next()) {
                lista.add(new Recepti(rs.getInt("ID"), rs.getString("Naziv"), rs.getString("Sastojci"), rs.getString("Priprema")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
}
