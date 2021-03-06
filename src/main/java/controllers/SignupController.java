package controllers;

import databases.CustomerAccountDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Accounts;

import java.io.IOException;

public class SignupController {
    Accounts accounts;
    @FXML
    TextField firstName, lastName, userName, password, email, tel;
    @FXML
    TextArea address;

    public void setEditAccounts(Accounts accounts) {
        this.accounts = accounts;
        firstName.setText(this.accounts.getFirstname());
        lastName.setText(this.accounts.getLastname());
        userName.setText(this.accounts.getUsername());
        password.setText(this.accounts.getPassword());
        address.setText(this.accounts.getAddress());
        email.setText(this.accounts.getEmail());

        tel.setText(this.accounts.getTel());
    }

    public void editAccount(ActionEvent event) {
        if (accounts == null) {
            if (!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !userName.getText().isEmpty() && !password.getText().isEmpty() && !address.getText().isEmpty() && !email.getText().isEmpty() && !tel.getText().isEmpty() && isNumeric(tel.getText())) {
                CustomerAccountDB.saveAccountsCustomerDB(CustomerAccountDB.getCreateAccountsID(), this.firstName.getText(), this.lastName.getText(), this.userName.getText(), this.password.getText(), this.address.getText(), this.email.getText(), this.tel.getText());
                this.firstName.setText("");
                this.lastName.setText("");
                this.userName.setText("");
                this.password.setText("");
                this.address.setText("");
                this.email.setText("");
                this.tel.setText("");
                this.backToAccounts(event);
            }else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Please enter correct form.");
                errorAlert.setTitle("ERROR!");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        } else {
            this.accounts.setFirstname(firstName.getText());
            this.accounts.setLastname(lastName.getText());
            this.accounts.setUsername(userName.getText());
            this.accounts.setPassword(password.getText());
            this.accounts.setAddress(address.getText());
            this.accounts.setEmail(email.getText());
            this.accounts.setTel(tel.getText());
            CustomerAccountDB.editAccountsCustomerDB(Integer.parseInt(this.accounts.getId()), this.accounts.getFirstname(), this.accounts.getLastname(), this.accounts.getUsername(), this.accounts.getPassword(), this.accounts.getAddress(), this.accounts.getEmail(), this.accounts.getTel());
            this.backToAccounts(event);
        }
    }

    public  boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    public void cancelToAccounts(ActionEvent event) {
        this.cancelToHome(event);
    }

    public void cancelToHome(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToAccounts(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
