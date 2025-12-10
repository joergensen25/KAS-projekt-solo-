package gui;

import controller.Controller;
import javafx.application.Application;
import model.Udflugt;

public class App {

    Controller controller = new Controller();
    public static void main(String[] args) {

        Application.launch(MainWindow.class);
    }
}
