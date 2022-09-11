package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.model.Baza;
import sample.model.Recepti;

import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReceptController implements Initializable {

    ObservableList<Recepti> data1 = FXCollections.observableArrayList();
    Baza db = new Baza();

    @FXML
    private TextField IDR;
    @FXML
    private TextField NazivR;
    @FXML
    private TextField SastojciR;
    @FXML
    private TextField PripremaR;
    @FXML
    private TableView<Recepti> ReceptiR;
    @FXML
    private TableColumn<Recepti, String> ColumnIDR;
    @FXML
    private TableColumn<Recepti, String> ColumnNazivR;
    @FXML
    private TableColumn<Recepti, String> ColumnSastojciR;
    @FXML
    private TableColumn<Recepti, String> ColumnPripremaR;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void listaRecepata () {

        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM `recepti`");
        ColumnIDR.setCellValueFactory(new PropertyValueFactory<Recepti, String>("ID"));
        ColumnNazivR.setCellValueFactory(new PropertyValueFactory<Recepti, String>("Naziv"));
        ColumnSastojciR.setCellValueFactory(new PropertyValueFactory<Recepti, String>("Sastojci"));
        ColumnPripremaR.setCellValueFactory(new PropertyValueFactory<Recepti, String>("Priprema"));

        try {

            data1.clear();
            while (rs.next()) {
                data1.add(new Recepti(
                        rs.getInt("ID"),
                        rs.getString("Naziv"),
                        rs.getString("Sastojci"),
                        rs.getString("Priprema")));
                ReceptiR.setItems(data1);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }
    public void postaviPodatkeUCelijeR(){
        ReceptiR.setOnMouseClicked(e -> {
            Recepti r = (Recepti) ReceptiR.getItems().get(ReceptiR.getSelectionModel().getSelectedIndex());
            IDR.setText(String.valueOf(r.getID()));
            NazivR.setText(r.getNaziv());
            SastojciR.setText(r.getSastojci());
            PripremaR.setText(r.getPriprema());
        });
    }
    public void DodajR(ActionEvent event){
        try{
            String sql = "INSERT INTO recepti (Naziv, Sastojci, Priprema) VALUES (?, ?, ?)";

            PreparedStatement ps = db.exec(sql);
            ps.setString(1, NazivR.getText());
            ps.setString(2, SastojciR.getText());
            ps.setString(3, PripremaR.getText());

            ps.execute();


            JOptionPane.showMessageDialog(null, "Uspjesno dodano");
            IDR.clear();
            NazivR.clear();
            SastojciR.clear();
            PripremaR.clear();
            data1.clear();
            listaRecepata();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void UrediR(ActionEvent event){
        try{

            String sql =  "UPDATE `recepti` SET `Naziv`='"+NazivR.getText()+"',`Sastojci`='"+SastojciR.getText()+"',`Priprema`='"+PripremaR.getText()+"' WHERE `ID`= "+IDR.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno azurirano");
            data1.clear();
            listaRecepata();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void ObrisiR(ActionEvent event){
        try{
            String sql = "DELETE FROM recepti WHERE ID="+IDR.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
            IDR.clear();
            NazivR.clear();
            SastojciR.clear();
            PripremaR.clear();
            data1.clear();
            listaRecepata();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void IsprazniPoljaR(ActionEvent event){
        try{
            IDR.clear();
            NazivR.clear();
            SastojciR.clear();
            PripremaR.clear();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void Odjava(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/view/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prijava!");
            stage.setScene(new Scene(root, 370, 250));
            stage.show();
        } catch (Exception e) {

        }
    }
}
