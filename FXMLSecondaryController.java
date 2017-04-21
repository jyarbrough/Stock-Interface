import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FXMLSecondaryController {
    private StringProperty text ;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println(text.getValue());
    }
    public void initialize() {
        text = new SimpleStringProperty(this, "text");
    }
    public StringProperty textProperty() {
        return text ;
    }
    public String getText() {
        return text.get();
    }
}

