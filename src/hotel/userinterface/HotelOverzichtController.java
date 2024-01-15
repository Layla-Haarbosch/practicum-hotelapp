package hotel.userinterface;

import hotel.model.Boeking;
import hotel.model.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.util.List;

public class HotelOverzichtController {
    @FXML
    private Label hotelnaamLabel;
    @FXML
    private ListView boekingenListView;
    @FXML
    private DatePicker overzichtDatePicker;

    private Hotel hotel = Hotel.getHotel();

    public void initialize() {
        hotelnaamLabel.setText("Boekingen hotel " + hotel.getNaam());
        overzichtDatePicker.setValue(LocalDate.now());
        toonBoekingen();
    }

    public void toonVorigeDag(ActionEvent actionEvent) {
        LocalDate dagEerder = overzichtDatePicker.getValue().minusDays(1);
        overzichtDatePicker.setValue(dagEerder);
    }

    public void toonVolgendeDag(ActionEvent actionEvent) {
        LocalDate dagLater = overzichtDatePicker.getValue().plusDays(1);
        overzichtDatePicker.setValue(dagLater);
    }

    public void nieuweBoeking(ActionEvent actionEvent) {
        System.out.println("nieuweBoeking() is nog niet geïmplementeerd!");

        // Maak in je project een nieuwe FXML-pagina om boekingen te kunnen invoeren
        // Open de nieuwe pagina in deze methode
        // Zorg dat de gebruiker ondertussen geen gebruik kan maken van de HotelOverzicht-pagina
        // Update na sluiten van de nieuwe pagina het boekingen-overzicht
    }

    public void toonBoekingen() {
        ObservableList<String> boekingen = FXCollections.observableArrayList();
        LocalDate huidig = overzichtDatePicker.getValue();

        for (Boeking boeking : hotel.getBoekingen()) {
            if (!huidig.isBefore(boeking.getAankomstDatum()) && !huidig.isAfter(boeking.getVertrekDatum())) {
                boekingen.add(boeking.getBoeker().getNaam() + ", kamer: " +
                        boeking.getKamer().getKamerNummer() + ", begindatum: " + boeking.getAankomstDatum() + ", " +
                        "einddatum: " + boeking.getVertrekDatum());
            }
        }

        boekingenListView.setItems(boekingen);
    }
}