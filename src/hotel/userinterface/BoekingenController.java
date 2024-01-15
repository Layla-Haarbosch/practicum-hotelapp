package hotel.userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BoekingenController {

    @FXML private Text foutmelding;
    @FXML private Button boek;
    @FXML private Button reset;
    @FXML private TextField naam;
    @FXML private TextField adres;
    @FXML private DatePicker aankomstDatum;
    @FXML private DatePicker vertrekDatum;
    @FXML private ComboBox kamerType;
}
