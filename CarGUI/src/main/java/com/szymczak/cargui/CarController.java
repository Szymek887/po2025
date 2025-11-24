package com.szymczak.cargui;

import com.szymczak.car.Car;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CarController {
    private Car car;

    // --- Layout Containers ---
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private VBox leftVBox;

    @FXML
    private VBox centerVBox;

    @FXML
    private HBox selectionHBox;

    // --- Menu Bar ---
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem newMenuItem;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private Menu editMenu;

    @FXML
    private MenuItem cutMenuItem;

    @FXML
    private MenuItem copyMenuItem;

    @FXML
    private MenuItem pasteMenuItem;

    // --- Car Details Section ---
    @FXML
    private TitledPane carDetailsPane;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField regNumberTextField;

    @FXML
    private TextField carWeightTextField;

    @FXML
    private TextField speedTextField;

    @FXML
    private Button turnOnButton;

    @FXML
    private Button turnOffButton;

    // --- Gearbox Section ---
    @FXML
    private TitledPane gearboxPane;

    @FXML
    private TextField gearboxNameTextField;

    @FXML
    private TextField gearboxPriceTextField;

    @FXML
    private TextField gearboxWeightTextField;

    @FXML
    private TextField gearTextField;

    @FXML
    private Button increaseGearButton;

    @FXML
    private Button decreaseGearButton;

    // --- Engine Section ---
    @FXML
    private TitledPane enginePane;

    @FXML
    private TextField engineNameTextField;

    @FXML
    private TextField enginePriceTextField;

    @FXML
    private TextField engineWeightTextField;

    @FXML
    private TextField rpmTextField;

    @FXML
    private Button speedUpButton;

    @FXML
    private Button speedDownButton;

    // --- Clutch Section ---
    @FXML
    private TitledPane clutchPane;

    @FXML
    private TextField clutchNameTextField;

    @FXML
    private TextField clutchPriceTextField;

    @FXML
    private TextField clutchWeightTextField;

    @FXML
    private TextField clutchStatusTextField;

    @FXML
    private Button depressClutchButton;

    // --- Center View (Selection & Map) ---
    @FXML
    private ComboBox<Car> carComboBox;

    @FXML
    private Button addCarButton;

    @FXML
    private Button removeCarButton;

    @FXML
    private Text mapText;

    @FXML
    private ImageView carImageView;

    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        this.car = new Car();

        Car car2 = new Car("Sporty",
                    new com.szymczak.car.Engine("EngineCo", "V8 Turbo", 200),
                    new com.szymczak.car.Gearbox("GearboxInc", "6-Speed Manual", 150),
                    new com.szymczak.car.Clutch("ClutchLtd", "Performance Clutch", 50));

        this.carComboBox.getItems().add(this.car);
        this.carComboBox.getItems().add(car2);
        this.carComboBox.getSelectionModel().select(0);

        this.updateView();
    }

    private void updateView() {
        this.gearTextField.setText(String.valueOf(this.car.getGear()));
        this.modelTextField.setText(this.car.toString());
    }

    @FXML
    private void handleTurnOnButton() {
        this.car.turnOn();
        this.updateView();
    }

    @FXML
    private void handleTurnOffButton() {
        this.car.turnOff();
        this.updateView();
    }

    @FXML
    private void handleIncreaseGearButton() {
        this.car.increaseGear();
        this.updateView();
    }

    @FXML
    private void handleDecreaseGearButton() {
        this.car.decreaseGear();
        this.updateView();
    }

    @FXML
    private void handleCarComboBox() {
        Car selectedCar = this.carComboBox.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            this.car = selectedCar;
            this.updateView();
        }
    }
}