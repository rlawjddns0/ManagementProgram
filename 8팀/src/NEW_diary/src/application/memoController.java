package application;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	@FXML
	TextArea information;
	@FXML
	TextField place;
	@FXML
	TextField title;
	@FXML
	TextField year;
	@FXML
	TextField d_day;
	@FXML
	private TableView<Scheduleinfo> myTableView;
	@FXML
	private TableColumn<Scheduleinfo, String> titleColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> informationColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> placeColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> yearColumn;
	@FXML
	private TableColumn<Scheduleinfo, String> d_dayColumn;

	public static String date_y;
	public static String date_m;
	public static String date_d;
	public static String Today;

	private Stage primaryStage;
	
	public void SetmemoController(TableView<Scheduleinfo> my) {
		this.myTableView = my;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelDate_y.setText(date_y + ".");
		labelDate_m.setText("\t" + date_m + ".");
		labelDate_d.setText("\t    " + date_d + " ¸Þ¸ð");
		year.setText(date_y + date_m + date_d);
		String wantDate = date_y + date_m + date_d;	
		String Format = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(Format);
		try {
			Date Today_ = sdf.parse(Today);
			Date wantDate_ = sdf.parse(wantDate);
			
			long diffDay = (Today_.getTime() - wantDate_.getTime()) / (24*60*60*1000);
			if(diffDay > 0)
				d_day.setText("D+"+diffDay);
			else if(diffDay <0)
				d_day.setText("D"+diffDay);
			else {
				d_day.setText("D-DAY");
				
			}
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void save(ActionEvent event) {

		information.getText();
		title.getText();
		place.getText();
		year.getText();
		d_day.getText();
		myTableView.getItems().add(new Scheduleinfo(new SimpleStringProperty(title.getText()),
						new SimpleStringProperty(place.getText()), new SimpleStringProperty(information.getText()),
						new SimpleStringProperty(year.getText()), new SimpleStringProperty(d_day.getText())));

		ScheduleSet s = new ScheduleSet();
		s.insert(information.getText(), title.getText(), place.getText(), year.getText(), d_day.getText());
		primaryStage.close();
	}

	public void cancel() {
		primaryStage.close();
	}

}
