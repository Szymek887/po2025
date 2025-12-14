package com.szymczak.cargui;

import com.szymczak.car.*;
import com.szymczak.interfaces.Listener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController implements Listener {
    private Car selectedCar;

    @FXML private TextField modelTextField;
    @FXML private TextField regNumberTextField;
    @FXML private TextField carWeightTextField;
    @FXML private TextField speedTextField;

    // --- Gearbox Section ---
    @FXML private TextField gearboxNameTextField;
    @FXML private TextField gearboxPriceTextField;
    @FXML private TextField gearboxWeightTextField;
    @FXML private TextField gearTextField;

    // --- Engine Section ---
    @FXML private TextField engineNameTextField;
    @FXML private TextField enginePriceTextField;
    @FXML private TextField engineWeightTextField;
    @FXML private TextField rpmTextField;

    // --- Clutch Section ---
    @FXML private TextField clutchNameTextField;
    @FXML private TextField clutchPriceTextField;
    @FXML private TextField clutchWeightTextField;
    @FXML private TextField clutchStatusTextField;

    // --- Center View (Selection & Map) ---
    @FXML private ComboBox<Car> carComboBox;
    @FXML private ImageView carImageView;

    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        this.setupSimulation();

        this.setupMapIcon();

        if (carComboBox.getItems().isEmpty()) {

        }
        this.carImageView.setTranslateX(0);
        this.carImageView.setTranslateY(0);
        this.carImageView.setMouseTransparent(true);

        if (!carComboBox.getItems().isEmpty()) {
            this.selectCar(carComboBox.getItems().getFirst());
            this.carComboBox.getSelectionModel().selectFirst();
        }
    }

    private void setupSimulation() {
        Car defaultCar = new Car();
        defaultCar.startSimulation();

        Car sportsCar = new Car("Sporty",
                "SP 12345",
                100,
                200,
                new Engine("EngineCo", "V8 Turbo", 200, 1000),
                new Gearbox("GearboxInc", "6-Speed Manual", 150, 1000),
                new Clutch("ClutchLtd", "Performance Clutch", 50, 1000));
        sportsCar.startSimulation();

        this.carComboBox.getItems().addAll(defaultCar, sportsCar);
    }

    private void setupMapIcon() {
        Image carImage = new Image(getClass().getResource("/com/szymczak/images/car.jpg").toExternalForm());
        this.carImageView.setImage(carImage);
        this.carImageView.setFitWidth(30);
        this.carImageView.setFitHeight(20);
    }

    public void selectCar(Car newCar) {
        if (this.selectedCar != null) {
            this.selectedCar.removeListener(this);
        }

        this.selectedCar = newCar;

        if (this.selectedCar != null) {
            this.selectedCar.addListener(this);
            this.refresh();
        }
    }

    public void update() {
        Platform.runLater(this::refresh);
    }

    private void refresh() {
        if (this.selectedCar == null) return;

        CarData carData = this.selectedCar.getCarData();

        this.updateCarDetails(carData);
        this.updateGearboxDetails(carData);
        this.updateEngineDetails(carData);
        this.updateClutchDetails(carData);
        this.updateCarPosition(carData);
    }

    private void updateCarDetails(CarData data) {
        this.modelTextField.setText(data.name());
        this.regNumberTextField.setText(data.regNumber());
        this.carWeightTextField.setText(String.valueOf(data.weight()));
        this.speedTextField.setText(String.valueOf(data.maxSpeed()));
    }

    private void updateGearboxDetails(CarData data) {
        this.gearboxNameTextField.setText(data.gearboxName());
        this.gearboxPriceTextField.setText(String.valueOf(data.gearboxPrice()));
        this.gearboxWeightTextField.setText(String.valueOf(data.gearboxWeight()));
        this.gearTextField.setText(String.valueOf(data.gear()));
    }

    private void updateEngineDetails(CarData data) {
        this.engineNameTextField.setText(data.engineName());
        this.enginePriceTextField.setText(String.valueOf(data.enginePrice()));
        this.engineWeightTextField.setText(String.valueOf(data.engineWeight()));
        this.rpmTextField.setText(String.valueOf(data.engineRevs()));
    }

    private void updateClutchDetails(CarData data) {
        this.clutchNameTextField.setText(data.clutchName());
        this.clutchPriceTextField.setText(String.valueOf(data.clutchPrice()));
        this.clutchWeightTextField.setText(String.valueOf(data.clutchWeight()));
        this.clutchStatusTextField.setText(data.isClutchPressed() ? "Pressed" : "Released");
    }

    private void updateCarPosition(CarData data) {
        this.carImageView.setTranslateX(data.position().getX());
        this.carImageView.setTranslateY(data.position().getY());
    }

    @FXML
    private void handleTurnOnButton() {
        if (selectedCar != null) this.selectedCar.turnOn();
    }

    @FXML
    private void handleTurnOffButton() {
        if (selectedCar != null) this.selectedCar.turnOff();
    }

    @FXML
    private void handleIncreaseGearButton() {
        if (selectedCar != null) this.selectedCar.increaseGear();
    }

    @FXML
    private void handleDecreaseGearButton() {
        if (selectedCar != null) this.selectedCar.decreaseGear();
    }

    @FXML void handleSpeedUpButton() {
        if (selectedCar != null) this.selectedCar.speedUp();
    }

    @FXML void handleSlowDownButton() {
        if (selectedCar != null) this.selectedCar.slowDown();
    }

    @FXML
    private void handleCarComboBox() {
        Car selected = this.carComboBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectCar(selected);
        }
    }

    @FXML
    private void handleCenterClick(MouseEvent event) {
        if (selectedCar != null) {
            Position newPosition = new Position();
            newPosition.setPosition(event.getX(), event.getY());
            this.selectedCar.rideTo(newPosition);
        }
    }

    @FXML
    private void handleAddCarButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/szymczak/cargui/add-car-view.fxml"));
            Parent root = loader.load();

            AddCarController controller = loader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Car");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCar(Car car) {
        car.startSimulation();
        this.carComboBox.getItems().add(car);
        this.carComboBox.getSelectionModel().select(car);
    }
}