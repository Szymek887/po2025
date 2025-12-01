package com.szymczak.cargui;

import com.szymczak.car.Car;
import com.szymczak.car.Clutch;
import com.szymczak.car.Engine;
import com.szymczak.car.Gearbox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
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
        // Populate list of cars with sample data
        this.car = new Car();

        Car car2 = new Car("Sporty",
                    "SP 12345",
                    100,
                    new Engine("EngineCo", "V8 Turbo", 200, 1000),
                    new Gearbox("GearboxInc", "6-Speed Manual", 150, 1000),
                    new Clutch("ClutchLtd", "Performance Clutch", 50, 1000));

        this.carComboBox.getItems().add(this.car);
        this.carComboBox.getItems().add(car2);
        this.carComboBox.getSelectionModel().select(0);

        // Add car icon in default position
        Image carImage = new Image(getClass().getResource("/com/szymczak/images/car.jpg").toExternalForm());
        System.out.println("Width: " + carImage.getWidth() + ",Height: " + carImage.getHeight());

        this.carImageView.setImage(carImage);

        this.carImageView.setFitWidth(30);
        this.carImageView.setFitHeight(20);

        // Initial refresh of GUI fields
        this.refresh();
    }

    private void refresh() {
        // Car Fields
        this.modelTextField.setText(this.car.toString());
        this.regNumberTextField.setText(this.car.getRegNumber());
        this.carWeightTextField.setText(String.valueOf(this.car.getWeight()));
        this.speedTextField.setText(String.valueOf(this.car.getSpeed()));

        // Gearbox Fields
        this.gearboxNameTextField.setText(this.car.getGearboxName());
        this.gearboxPriceTextField.setText(String.valueOf(this.car.getGearboxPrice()));
        this.gearboxWeightTextField.setText(String.valueOf(this.car.getGearboxWeight()));
        this.gearTextField.setText(String.valueOf(this.car.getGear()));

        // Engine Fields
        this.engineNameTextField.setText(this.car.getEngineName());
        this.enginePriceTextField.setText(String.valueOf(this.car.getEnginePrice()));
        this.engineWeightTextField.setText(String.valueOf(this.car.getEngineWeight()));
        this.rpmTextField.setText(String.valueOf(this.car.getEngineRPM()));

        // Clutch Fields
        this.clutchNameTextField.setText(this.car.getClutchName());
        this.clutchPriceTextField.setText(String.valueOf(this.car.getClutchPrice()));
        this.clutchWeightTextField.setText(String.valueOf(this.car.getClutchWeight()));
        this.clutchStatusTextField.setText(this.car.getClutchStatus() ? "Pressed" : "Released");
    }

    @FXML
    private void handleTurnOnButton() {
        this.car.turnOn();
        this.refresh();
    }

    @FXML
    private void handleTurnOffButton() {
        this.car.turnOff();
        this.refresh();
    }

    @FXML
    private void handleIncreaseGearButton() {
        this.car.increaseGear();
        this.refresh();
    }

    @FXML
    private void handleDecreaseGearButton() {
        this.car.decreaseGear();
        this.refresh();
    }

    @FXML
    private void handleCarComboBox() {
        Car selectedCar = this.carComboBox.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            this.car = selectedCar;
            this.refresh();
        }
    }
}