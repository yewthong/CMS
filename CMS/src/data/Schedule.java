package data;

public class Schedule {

	String departure_date;
	String departure_day;
	String arrival_date;
	String arrival_day;
	String departure_time;
	String arrival_time;
	String departure_location;
	String arrival_location;
	float sailing_space;

	public Schedule(){	
	}

	public Schedule(String departure_date, String departure_day, String arrival_date, String arrival_day,
			String departure_time, String arrival_time, String departure_location, String arrival_location,
			float sailing_space) {
		this.departure_date = departure_date;
		this.departure_day = departure_day;
		this.arrival_date = arrival_date;
		this.arrival_day = arrival_day;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.departure_location = departure_location;
		this.arrival_location = arrival_location;
		this.sailing_space = sailing_space;
	}

	public String getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}

	public String getDeparture_day() {
		return departure_day;
	}

	public void setDeparture_day(String departure_day) {
		this.departure_day = departure_day;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getArrival_day() {
		return arrival_day;
	}

	public void setArrival_day(String arrival_day) {
		this.arrival_day = arrival_day;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public String getDeparture_location() {
		return departure_location;
	}

	public void setDeparture_location(String departure_location) {
		this.departure_location = departure_location;
	}

	public String getArrival_location() {
		return arrival_location;
	}

	public void setArrival_location(String arrival_location) {
		this.arrival_location = arrival_location;
	}

	public float getSailing_space() {
		return sailing_space;
	}

	public void setSailing_space(float sailing_space) {
		this.sailing_space = sailing_space;
	}


	
	
	
	
	
	
}
