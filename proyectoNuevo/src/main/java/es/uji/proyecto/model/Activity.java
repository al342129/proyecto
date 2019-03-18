package es.uji.proyecto.model;

import java.sql.Date;

public class Activity {
	
		private int idActivity;
		private Date actDate;
		private String activityName;
		private Date duration;
		private String descriptionActivity;
		private double price;
		private int maxPeople;
		private int minPeople;
		private int vacancies;
		
		
		
		
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
		
		
		public Date getActDate() {
			return actDate;
		}
		public void setActDate(Date actDate) {
			this.actDate = actDate;
		}
		public String getActivityName() {
			return activityName;
		}
		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}
		public Date getDuration() {
			return duration;
		}
		public void setDuration(Date duration) {
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


		
		
		
		
		
		
		
		
}
