package application;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class memoController implements Initializable {

   @FXML 
   Label labelDate_y;
   @FXML 
   Label labelDate_m;
   @FXML 
   Label labelDate_d;
   @FXML 
   TextArea data;
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      labelDate_y.setText(date_y + ".");
      labelDate_m.setText("       "+date_m + ".");
      //labelDate_d.setText("    "+date_d + " 메모");
   }
   
   public static String date_y;
   public static String date_m;
   public static String date_d;
   private Stage primaryStage;
   
   public void setPrimaryStage(Stage primaryStage) {
      this.primaryStage = primaryStage;
   }
   
   public void save() {
      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
      fileChooser.setInitialFileName(date_y +"." + date_m + " 메모.txt");
      File selectedFile = fileChooser.showSaveDialog(primaryStage);
      
      try {
         String selectedFilePath = selectedFile.getPath();
         FileWriter fw = new FileWriter(selectedFilePath);
         fw.write(data.getText().replaceAll("\n", "\r\n"));
         fw.flush();
         fw.close();
      } catch (Exception e) {}
      primaryStage.close();
   }

   public void cancel() {
      primaryStage.close();
   }

}