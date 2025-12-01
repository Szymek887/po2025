package com.szymczak.cargui;

import com.szymczak.car.Engine;
import com.szymczak.car.Gearbox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCarController {
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
            System.out.println("Invalid weight input");
            return;
        }

        Gearbox gearbox = gearboxComboBox.getSelectionModel().getSelectedItem();
        Engine engine = engineComboBox.getSelectionModel().getSelectedItem();
        System.out.println("Car added: " + model + ", " + regNumber + ", " + weight + ", " + gearbox + ", " + engine);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
