package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Scheduleinfo {
	String Place;
	String Title;
	String Information;
	String Year;
	String D_day;
//	String month;
//	String day;
	private StringProperty information;
    private StringProperty when;
    private StringProperty place;
    private StringProperty title;
    private StringProperty year;
    private StringProperty d_day;
//    private StringProperty month_;
//    private StringProperty day_;
    
	public void setPlace(String Place) {
		this.Place=Place;
	}
	public void setInformation(String Information) {
		this.Information=Information;
	}
	public void setTitle(String Title) {
		this.Title=Title;
	}
	public void setYear(String Year) {
		this.Year=Year;
	}
	public void setD_day(String D_day) {
		this.D_day=D_day;
	}

/*	public void setMonth(String Month) {
		this.month=Month;
	}
	
	public void setDay(String Day) {
		this.day=Day;
	}*/
	
	public String getPlace()
	{
		return Place;
	}
	public String getInformation() {
		return Information;
	}
	public String gettitle() {
		return Title;
	}
	public String getYear() {
		return Year;
	}
	public String getD_day() {
		return D_day;
	}
/*	public String getMonth() {
		return month;
	}
	public String getDay() {
		return day;
	}*/
	
    public Scheduleinfo(SimpleStringProperty title, SimpleStringProperty place, SimpleStringProperty information, SimpleStringProperty year, SimpleStringProperty d_day ) {
        this.title = title;
        this.place = place;
        this.information = information;
        this.year = year;
        this.d_day = d_day;
 //       this.month_ = month;
 //       this.day_ = day;
    }
    public Scheduleinfo()
    {
    	
    }
/*    public void SetWhen(StringProperty when) {
    	this.when=when;
    }*/
	public StringProperty whatProperty() {
        return information;
    }
    public StringProperty whenProperty() {
        return when;
    }
    public StringProperty placeProperty() {
        return place;
    }
    public StringProperty titleProperty() {
    	return title;
    }
	public StringProperty informationProperty() {
		
		return information;
	}
	public StringProperty yearProperty() {
		return year;
	}
	public StringProperty d_dayProperty() {
		return d_day;
	}
/*	public StringProperty monthProperty() {
		return month_;
	}
	public StringProperty dayProperty() {
		return day_;
	}
*/
}
