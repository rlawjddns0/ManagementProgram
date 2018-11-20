package application;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ComboEx implements Initializable{

	@FXML
	private ComboBox<Integer> month;
	@FXML
    public ComboBox<Integer> year;
	 @FXML
	 public AnchorPane dataPane;

	Calendar calendar=Calendar.getInstance();
	
	//ObservableList<Integer> combolist=FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);

	List<Button> buttons = new ArrayList<Button>();

	Group group = new Group();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

        List<Integer> yearOptions = new ArrayList<Integer>();
        for (int i = 1900; i <= 2050; i++) {
            yearOptions.add(i);
        }
        List<Integer> monthOptions = new ArrayList<Integer>();
        for (int i = 1; i <= 12; i++) {
            monthOptions.add(i);
        }

        year.setItems(FXCollections.observableArrayList(yearOptions));
        year.setValue(Calendar.getInstance().get(Calendar.YEAR));
        month.setItems(FXCollections.observableArrayList(monthOptions));
        month.setValue(Calendar.getInstance().get(Calendar.MONTH) + 1);
		
	}
	private boolean isNull(ComboBox<Integer> x) {
        return x == null || x.getValue() == null;
    }
	
	private String day(Calendar c) {
        return String.format("%d", c.get(Calendar.DAY_OF_MONTH));
    }
	 @FXML
	    public void changeYearMonth(ActionEvent event) {
	        if (isNull(year) || isNull(month)) {
	            return;
	        }
	        Calendar calendar1;
	        Calendar calendar2;
	        int y = year.getValue();
	        int m = month.getValue() - 1;
	        
	        calendar1 = new GregorianCalendar(y, m, 1);

	        while (calendar1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {//특정날짜가 일요일이 아닐때까지
	            calendar1.add(Calendar.DATE, -1); //1일전
	        }

	        calendar2 = new GregorianCalendar(y, m, 1);
	        
	        calendar2.add(Calendar.MONTH, 1);//1달후
	        while (calendar2.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {//특정날짜가 일요일이 아닐때까지
	            calendar2.add(Calendar.DATE, 1); //1일후
	        }

	        int target = Calendar.DAY_OF_YEAR;//이 해의 몇일
	        double date = 0D;

	       dataPane.getChildren().clear();
	       buttons.clear();
	        while (calendar1.get(target) != calendar2.get(target)) {

	            AnchorPane pane = new AnchorPane();
	            pane.setPrefSize(30, 40);

	            Label lab = new Label(day(calendar1));
	            lab.setPrefSize(30D, 20D);

	            Button button = new Button();
	            button.setPrefSize(30, 20);
	            button.setGraphic(group);
	            button.setDisable(calendar1.get(Calendar.MONTH) != (month.getValue() - 1));
	            button.setUserData(day(calendar1));

	            AnchorPane.setTopAnchor(lab, 0D);
	            AnchorPane.setBottomAnchor(button, 0D);

	            pane.getChildren().addAll(lab, button);

	            AnchorPane.setTopAnchor(pane, Math.floor(date / 7) * 40D);
	            AnchorPane.setLeftAnchor(pane, Math.floor(date % 7) * 40D);

	            dataPane.getChildren().add(pane);
	            date++;

	            buttons.add(button);

	            calendar1.add(Calendar.DATE, 1);
	        }

	    }
}
