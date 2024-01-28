package hotel.userinterface;

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

import java.time.LocalDate;

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


        try {
            if (this.naam.getText().isEmpty()) {
                throw new Exception("Naam moet ingevuld zijn!");
            }

            if (this.adres.getText().isEmpty()) {
                throw new Exception("Adres moet ingevuld zijn!");
            }

            if (this.aankomstDatum.getValue() != null && this.aankomstDatum.getValue().isBefore(LocalDate.now())) {
                throw new Exception("Aankomstdatum moet in de toekomst liggen!");
            }

            if (this.vertrekDatum.getValue() != null && this.vertrekDatum.getValue().isBefore(LocalDate.now())) {
                throw new Exception("Vertrekdatum moet in de toekomst liggen!");
            }

            if (this.kamerType.getValue() == null) {
                throw new Exception("Kamertype moet geselecteer zijn!");
            }

            if (this.vertrekDatum.getValue() != null && this.aankomstDatum.getValue() != null
                    && this.vertrekDatum.getValue().isBefore(this.aankomstDatum.getValue())) {
                throw new Exception("Vertrekdatum komt na aankomstdatum!");
            }

            if (!this.naam.getText().isEmpty() && !this.adres.getText().isEmpty()
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

                hotel.voegBoekingToe(aankomst, vertrek, naam, adres, kamerType);
                this.foutmelding.setText(null);
                stage.close();
            }
        } catch (Exception ex) {
            this.foutmelding.setText(ex.getMessage());
        }
    }

    public void resetAction(ActionEvent actionEvent) {
        this.naam.setText(null);
        this.adres.setText(null);
        this.vertrekDatum.setValue(null);
        this.aankomstDatum.setValue(null);
        this.kamerType.setValue(null);
        this.foutmelding.setText("Voer uw gegevens in!");
    }
}
