package gui;

import controller.Controller;
import javafx.application.Application;

public class App {

    Controller controller = new Controller();
    public static void main(String[] args) {

        Application.launch(MainWindow.class);
    }
}
