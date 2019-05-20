package es.uji.proyecto.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Activity {
	
		private int idActivity;
		private String activityName;
		@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
		private LocalDate actDate;
		private String location;
		private String activityImage;
		private double duration;
		private String descriptionActivity;
		private double price;
		private int maxPeople;
		private int minPeople;
		private int vacancies;
		private String typeName;
		private String level;
		
		
		
		
		public Activity() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public int getIdActivity() {
			return idActivity;
		}


		public void setIdActivity(int idActivity) {
			this.idActivity = idActivity;
		}
		
		
		public LocalDate getActDate() {
			return actDate;
		}
		public void setActDate(LocalDate actDate) {
			this.actDate = actDate;
		}
		public String getActivityName() {
			return activityName;
		}
		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}
		public double getDuration() {
			return duration;
		}
		public void setDuration(double duration) {
			this.duration = duration;
		}
		public String getDescriptionActivity() {
			return descriptionActivity;
		}
		public void setDescriptionActivity(String descriptionActivity) {
			this.descriptionActivity = descriptionActivity;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getMaxPeople() {
			return maxPeople;
		}
		public void setMaxPeople(int maxPeople) {
			this.maxPeople = maxPeople;
		}
		public int getMinPeople() {
			return minPeople;
		}
		public void setMinPeople(int minPeople) {
			this.minPeople = minPeople;
		}
		public int getVacancies() {
			return vacancies;
		}
		public void setVacancies(int vacancies) {
			this.vacancies = vacancies;
		}


		@Override
		public String toString() {
			return "Activity [actDate=" + actDate + ", activityName=" + activityName + ", duration=" + duration
					+ ", descriptionActivity=" + descriptionActivity + ", price=" + price + ", maxPeople=" + maxPeople
					+ ", minPeople=" + minPeople + ", vacancies=" + vacancies + "]";
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getActivityImage() {
			return activityImage;
		}

		public void setActivityImage(String activityImage) {
			this.activityImage = activityImage;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

}
