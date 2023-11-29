/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.gustavo.bucketsort;

import org.gustavo.bucketsort.enums.Scenario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    private Test tests;
    @FXML
    private ToggleGroup toggleGroup1;
    @FXML
    private RadioButton ten;
    @FXML
    private RadioButton fifty;
    @FXML
    private RadioButton twentyThousand;
    @FXML
    private ToggleGroup toggleGroup2;
    @FXML
    private RadioButton random;
    @FXML
    private RadioButton ascending;
    @FXML
    private RadioButton descending;
    @FXML
    private TableView<TestResult> table;
    @FXML
    private TableColumn<TestResult, String> algorithmColumn;
    @FXML
    private TableColumn<TestResult, String> elapsedTimeColumn;
    @FXML
    private TableColumn<TestResult, Integer> swapsColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tests = new Test();
    }
    
    @FXML
    private void runTests() {
        if (toggleGroup1.getSelectedToggle() == null || toggleGroup2.getSelectedToggle() == null) {
            return;
        }
        
        int input;
        Scenario scenario;
        
        if (toggleGroup1.getSelectedToggle().equals(ten)) {
            input = 10;
        } else if (toggleGroup1.getSelectedToggle().equals(fifty)) {
            input = 50;
        } else {
            input = 20_000;
        }

        
        if (toggleGroup2.getSelectedToggle().equals(random)) {
            scenario = Scenario.Random;
        } else if (toggleGroup2.getSelectedToggle().equals(ascending)) {
            scenario = Scenario.Ascending;
        } else {
            scenario = Scenario.Descending;
        }
        
        var results = tests.run(input, scenario);
        
        algorithmColumn.setCellValueFactory(new PropertyValueFactory<>("algorithm"));
        elapsedTimeColumn.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));
        swapsColumn.setCellValueFactory(new PropertyValueFactory<>("swaps"));
        
        table.setItems(getResultsAsObservableList(results));
    }

    @FXML
    private void clear() {
        toggleGroup1.getSelectedToggle().setSelected(false);
        toggleGroup2.getSelectedToggle().setSelected(false);
    }
    
    private ObservableList<TestResult> getResultsAsObservableList(List<TestResult> results) {
        return FXCollections.observableArrayList(
            results
        );
    }
}
