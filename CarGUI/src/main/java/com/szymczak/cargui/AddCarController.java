package com.szymczak.cargui;

import com.szymczak.car.Car;
import com.szymczak.car.Clutch;
import com.szymczak.car.Engine;
import com.szymczak.car.Gearbox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCarController {
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private Text errorText;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField regNumberTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private ComboBox<Gearbox> gearboxComboBox;

    @FXML
    private ComboBox<Engine> engineComboBox;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField maxSpeedTextField;

    @FXML
    private void initialize() {
        gearboxComboBox.getItems().addAll(
                new Gearbox("GearboxCo", "Model A", 5, 150, 1200),
                new Gearbox("GearboxCo", "Model B", 6, 160, 1500),
                new Gearbox("Transmissions Inc.", "Model X", 7, 170, 1800)
        );

        engineComboBox.getItems().addAll(
                new Engine("EngineWorks", "EcoBoost", 130, 200),
                new Engine("PowerEngines", "V8 Turbo", 300, 400),
                new Engine("SpeedMotors", "ElectricX", 200, 300)
        );
    }

    @FXML
    private void handleConfirmButton() {
        String model = modelTextField.getText();
        String regNumber = regNumberTextField.getText();

        int weight;

        try {
            weight = Integer.parseInt(weightTextField.getText());
        } catch (NumberFormatException e) {
            this.errorText.setText("Weight should be a number");
            this.errorText.setVisible(true);
            return;
        }

        int maxSpeed;

        try {
            maxSpeed = Integer.parseInt(maxSpeedTextField.getText());
        } catch (NumberFormatException e) {
            this.errorText.setText("Max Speed should be a number");
            this.errorText.setVisible(true);
            return;
        }

        Gearbox gearbox = gearboxComboBox.getSelectionModel().getSelectedItem();
        Engine engine = engineComboBox.getSelectionModel().getSelectedItem();

        if (model.isEmpty() || regNumber.isEmpty() || gearbox == null || engine == null) {
            this.errorText.setText("Please fill out all fields");
            this.errorText.setVisible(true);
            return;
        }

        Car newCar = new Car(model, regNumber, weight, maxSpeed, engine, gearbox, new Clutch("test", "test", 100, 100));
        if (mainController != null) {
            mainController.addCar(newCar);
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
