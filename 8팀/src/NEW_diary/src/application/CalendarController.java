package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CalendarController implements Initializable {

	@FXML
	private TableView<Scheduleinfo> myTableView;
	@FXML
	private TableColumn<Scheduleinfo, String> placeColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> titleColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> informationColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> yearColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> d_dayColumn;
	@FXML
	private ComboBox<Integer> month;
	@FXML
	public ComboBox<Integer> year;
	@FXML
	public AnchorPane dataPane;
	@FXML
	public Text text;

	public ScheduleSet Set;
	public Scheduleinfo info;

	private Calendar calendar1;
	private Calendar calendar2;

	Calendar calendar = Calendar.getInstance();

	ObservableList<Integer> combolist = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

	List<Button> buttons = new ArrayList<Button>(); // 버튼

	Group group = new Group();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Set = new ScheduleSet();

		// info=Set.select();

	//	ObservableList<Scheduleinfo> myList = FXCollections.observableArrayList();

		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		placeColumn.setCellValueFactory(cellData -> cellData.getValue().placeProperty());
		informationColumn.setCellValueFactory(cellData -> cellData.getValue().informationProperty());
		yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
		d_dayColumn.setCellValueFactory(cellData -> cellData.getValue().d_dayProperty());
		//myTableView.setItems(myList);

		Set.select(myTableView);

		// What.setText("ad");
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
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Calendar time = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
				text.setText(simpleDateFormat.format(time.getTime()));
			}
		}), new KeyFrame(Duration.seconds(1)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
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
		int nday = c.get(Calendar.DAY_OF_MONTH);
		int nmonth = c.get(Calendar.MONTH) + 1;
		int nyear = c.get(Calendar.YEAR);

		Date d = new Date();
		int y = year.getValue();
		int m = month.getValue() - 1;

		calendar1 = new GregorianCalendar(y, m, 1);

		while (calendar1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {// 특정날짜가 일요일이 아닐때까지
			// System.out.println(calendar1.get(Calendar.DATE));
			calendar1.add(Calendar.DATE, -1); // 1일전
		}

		calendar2 = new GregorianCalendar(y, m, 1);

		calendar2.add(Calendar.MONTH, 1);// 1달후
		while (calendar2.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {// 특정날짜가 일요일이 아닐때까지
			// System.out.println(calendar2.get(Calendar.DATE));
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
			
			String day = lab.getText();
			button.setOnAction(e -> memo(day));
			if (calendar1.get(Calendar.DAY_OF_MONTH) == nday && calendar1.get(Calendar.MONTH) + 1 == nmonth
					&& calendar1.get(Calendar.YEAR) == nyear)
				button.setStyle("-fx-border-color: blue");

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

	private void memo(String day) {
		System.out.println("버튼 실행!");
		Integer localDate_m = month.getValue();
		Integer localDate_y = year.getValue();
		Integer today_y = Calendar.getInstance().get(Calendar.YEAR);
		Integer today_m = Calendar.getInstance().get(Calendar.MONTH) + 1;
		Integer today_d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		String date_m = localDate_m.toString();
		String date_y = localDate_y.toString();
		String Todate_y = today_y.toString();
		String Todate_m = today_m.toString();
		String Todate_d = today_d.toString();
		
		if(today_d <10)
			Todate_d = 0 + Todate_d;
		if(today_m <10)
			Todate_m = 0 + Todate_m;
		if(today_y <10)
			Todate_y = 0 + Todate_y;
		if(localDate_m <10)
			date_m = 0 + date_m;
		if(localDate_y <10)
			date_y = 0 + date_y;
		if(Integer.parseInt(day) < 10)
			day = 0 + day;
		String Today = Todate_y + Todate_m + Todate_d;
		memoController.date_m = date_m;
		memoController.date_y = date_y;
		memoController.date_d = day;
		memoController.Today = Today;
		Stage newStage = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("memo.fxml"));

		Parent Comboex = null;
		try {
			Comboex = loader.load();
		} catch (IOException e) {
		}

		memoController memoControl = loader.getController();
		memoControl.SetmemoController(myTableView);
		memoControl.setPrimaryStage(newStage);
		Scene scene = new Scene(Comboex);

		newStage.setTitle("메모장 작성");
		newStage.setScene(scene);
		newStage.show();
	}

	@FXML
	public void moveToday(ActionEvent event) {

		year.setValue(Calendar.getInstance().get(Calendar.YEAR));
		month.setValue(Calendar.getInstance().get(Calendar.MONTH) + 1);
		changeYearMonth(null);
	}

	public void delete(ActionEvent event) throws SQLException {
	      TableView<Scheduleinfo> sc=new TableView<Scheduleinfo>();
	      ScheduleSet s=new ScheduleSet();
	      Scheduleinfo z=new Scheduleinfo();
	      //String t=myTableView.getSelectionModel().getSelectedItem().getInformation();
	      Scheduleinfo selectedItem = myTableView.getSelectionModel().getSelectedItem();
	      if(selectedItem!=null)
	      {
	         System.out.println(selectedItem.yearProperty());
	         s.delete(selectedItem.yearProperty().getValue());
	         myTableView.getItems().remove(selectedItem);
	         //myTableView.remove(selectedItem);
	         
	      }
	      //myTableView.getItems().remove(selectedItem);
	   }
}
