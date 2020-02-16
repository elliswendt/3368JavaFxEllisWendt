package sample;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<Worker> employeeListView;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private TextField genderTextField;
    @FXML
    private JFXButton deleteSelectedButton;
    @FXML
    private JFXButton clearButton;
    @FXML
    private JFXButton addNewButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                ObservableValue<? extends Worker> ov, Worker old_val, Worker new_val) ->
                {
                    Worker selectedItem  = employeeListView.getSelectionModel().getSelectedItem();
                    firstNameTextField.setText(((Employee)selectedItem).firstName); //cast worker into employee
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                    genderTextField.setText(((Employee)selectedItem).gender);
                }
                );
        deleteSelectedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int selectedIdx = employeeListView.getSelectionModel().getSelectedIndex();
                if (selectedIdx != -1){
                    employeeListView.getItems().remove(selectedIdx);
                }
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                firstNameTextField.clear();
                lastNameTextField.clear();
                isActiveCheckBox.setSelected(false);
                genderTextField.clear();
            }
        });
        ObservableList<Worker> items = employeeListView.getItems();
        addNewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Employee employee = new Employee();
                employee.firstName = firstNameTextField.getText();
                employee.lastName = lastNameTextField.getText();
                employee.isActive = isActiveCheckBox.isSelected();
                employee.gender = genderTextField.getText();
                items.add(employee);
            }
        });
        Employee employee1 = new Employee();
        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        employee1.gender = "Male";
        Employee employee2 = new Employee();
        employee2.firstName = "Lisa";
        employee2.lastName = "Smith";
        employee2.gender = "Female";
        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.hire();
            items.add(employee);
        }

        Staff staff1 = new Staff();
        staff1.firstName = "StaffPerson";
        staff1.lastName = "GoodWorker";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleFaculty";
        items.add(staff1);
        items.add(faculty1);
    }
}
