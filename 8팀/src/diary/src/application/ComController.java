package application;


import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ComController implements Initializable {

	@FXML
	private ComboBox<Integer> month;
	@FXML
	public ComboBox<Integer> year;
	@FXML
	public AnchorPane dataPane;
	@FXML
	public Text text;
	Calendar calendar = Calendar.getInstance();

	//ObservableList<Integer> combolist = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

	List<Button> buttons = new ArrayList<Button>(); // 버튼

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
		//디지털 시계 코드(74~88)
		Timeline timeline = new Timeline(
			    new KeyFrame(Duration.seconds(0),
			      new EventHandler<ActionEvent>() {
			        @Override public void handle(ActionEvent actionEvent) {
			          Calendar time = Calendar.getInstance();
			          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			          text.setText(simpleDateFormat.format(time.getTime()));
			        }
			      }
			    ),
			    new KeyFrame(Duration.seconds(1))
			  );
		timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();
		//text.setText(hour+":"+min+":"+sec);
		changeYearMonth(null);
		
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
		  Calendar c = new GregorianCalendar(Locale.KOREA);
	      int nday=c.get(Calendar.DAY_OF_MONTH);
	      int nmonth=c.get(Calendar.MONTH)+1;
	      int nyear=c.get(Calendar.YEAR);
		int y = year.getValue();
		int m = month.getValue() - 1;
		
		//System.out.println(today);

		calendar1 = new GregorianCalendar(y, m, 1);

		while (calendar1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {// 특정날짜가 일요일이 아닐때까지
			calendar1.add(Calendar.DATE, -1); // 1일전
		}

		calendar2 = new GregorianCalendar(y, m, 1);

		calendar2.add(Calendar.MONTH, 1);// 1달후
		while (calendar2.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {// 특정날짜가 일요일이 아닐때까지
			calendar2.add(Calendar.DATE, 1); // 1일후
		}

		int target = Calendar.DAY_OF_YEAR;// 이 해의 몇일
		double date = 0D;

		dataPane.getChildren().clear();
		buttons.clear();
	
		while (calendar1.get(target) != calendar2.get(target)) {

			AnchorPane pane = new AnchorPane();
			pane.setPrefSize(30, 60);
			Label lab = new Label(day(calendar1));
			lab.setPrefSize(30D, 20D);

			Button button = new Button();

			button.setPrefSize(50, 40);
			button.setGraphic(group);
			button.setDisable(calendar1.get(Calendar.MONTH) != (month.getValue() - 1));
			button.setUserData(day(calendar1));
			if(calendar1.get(Calendar.DAY_OF_MONTH)==nday&&calendar1.get(Calendar.MONTH)+1==nmonth&&calendar1.get(Calendar.YEAR)==nyear)
	             button.setStyle("-fx-border-color: blue");
			button.setOnAction(e -> memo());
			
			AnchorPane.setTopAnchor(lab, 0D);
			AnchorPane.setBottomAnchor(button, 0D);

			pane.getChildren().addAll(lab, button);

			AnchorPane.setTopAnchor(pane, Math.floor(date / 7) * 70D);
			AnchorPane.setLeftAnchor(pane, Math.floor(date % 7) * 70D);

			dataPane.getChildren().add(pane);
			date++;

			buttons.add(button);
			calendar1.add(Calendar.DATE, 1);
		}
	
	}
	 private void memo() {
	      System.out.println("버튼 실행!");
	         Integer localDate_m = month.getValue();
	      Integer localDate_y = year.getValue();
	         String date_m = localDate_m.toString();
	      String date_y = localDate_y.toString();
	      memoController.date_m = date_m;
	      memoController.date_y = date_y;
	      Stage newStage = new Stage();
	      
	      FXMLLoader loader = new FXMLLoader(getClass().getResource("memo.fxml"));
	      
	      Parent Comboex = null;
	      try {
	         Comboex = loader.load();
	      } catch (IOException e) {}
	   
	            
	      memoController memoControl = loader.getController();
	      memoControl.setPrimaryStage(newStage);
	      Scene scene = new Scene(Comboex);
	      
	      newStage.setTitle("메모장 작성");
	      newStage.setScene(scene);
	      newStage.show();
	   }
	
}
