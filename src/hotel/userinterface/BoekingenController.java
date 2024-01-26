package hotel.userinterface;

import hotel.HotelApp;
import hotel.model.Boeking;
import hotel.model.Hotel;
import hotel.model.KamerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BoekingenController {

    @FXML
    private Text foutmelding;
    @FXML
    private Button boek;
    @FXML
    private Button reset;
    @FXML
    private TextField naam;
    @FXML
    private TextField adres;
    @FXML
    private DatePicker aankomstDatum;
    @FXML
    private DatePicker vertrekDatum;
    @FXML
    private ComboBox kamerType;

    private final Hotel hotel = Hotel.getHotel();

    public void initialize() {
        ObservableList<KamerType> kamerTypen = FXCollections.observableArrayList();

        kamerTypen.addAll(hotel.getKamerTypen());

        this.kamerType.setItems(kamerTypen);
    }

    public void boekAction(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) boek.getScene().getWindow();
        Date today = new Date();

        if (this.naam.getText() != null && this.adres.getText() != null
                && this.aankomstDatum.getValue() != null
                && this.vertrekDatum.getValue() != null
                && !aankomstDatum.getValue().isBefore(LocalDate.now())
                && !vertrekDatum.getValue().isBefore(LocalDate.now())
                && this.aankomstDatum.getValue().isBefore(this.vertrekDatum.getValue())
                && this.kamerType.getValue() != null) {
            LocalDate aankomst = this.aankomstDatum.getValue();
            LocalDate vertrek = this.vertrekDatum.getValue();
            String naam = this.naam.getText();
            String adres = this.adres.getText();
            KamerType kamerType = (KamerType) this.kamerType.getValue();

            try {
                Boeking boeking = hotel.voegBoekingToe(aankomst, vertrek, naam, adres, kamerType);
                this.foutmelding.setText(null);
                stage.close();
            } catch (Exception ex) {
                this.foutmelding.setText(ex.getMessage());
            }
        } else {
            this.foutmelding.setText("Gegevens zijn niet correct ingevuld!");
        }
    }

    public void resetAction(ActionEvent actionEvent) {
        this.naam.setText(null);
        this.adres.setText(null);
        this.vertrekDatum.setValue(null);
        this.aankomstDatum.setValue(null);
        this.kamerType.setValue(null);
    }
}
