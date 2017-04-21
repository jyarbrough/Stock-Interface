import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

class FXMLPrimaryController {
    private ReadOnlyStringWrapper text ;

    @FXML
    private TextArea myArea;

    public void initialize() {
        text= new ReadOnlyStringWrapper(this, "text");
        text.bind(myArea.textProperty());
    }
    public ReadOnlyStringProperty textProperty() {
        return text.getReadOnlyProperty();
    }
    public String getText() {
        return text.get();
    }

    @FXML
    private void openSecondWindow(ActionEvent event) throws Exception {

        Group root = new Group();
        Stage stage = new Stage();

        AnchorPane frame = FXMLLoader.load(getClass().getResource("second.fxml"));
        root.getChildren().add(frame);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
//
//    FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
//    Parent textAreaHolder = (Parent) primaryLoader.load();
//    FXMLLoader secondaryLoader = new FXMLLoader(getClass().getResource("second.fxml"));
//    Parent textAreaUser = (Parent) secondaryLoader.load();
//    FXMLPrimaryController primaryController = (FXMLPrimaryController) textAreaHolder.getController();
//    FXMLsecondController secondController = (FXMLsecondController) textAreaUser.getController();
//secondController.textProperty().bind(primaryController.textProperty());