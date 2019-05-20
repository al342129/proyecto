package es.uji.proyecto.model;

import java.util.ArrayList;
import java.util.List;

public class ActivityType{
	private String typeName;
	private String descriptionActType;
	private String level;
	private ArrayList<ActivityType> activityTypes;


	public ActivityType() {
		super();	
	}

	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDescriptionActType() {
		return descriptionActType;
	}

	public void setDescriptionActType(String descriptionActType) {
		this.descriptionActType = descriptionActType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStructure(){
		return typeName + "," + level;
	}
	
	@Override
	public String toString() {
		return "ActivityType [typeName=" + typeName + ", descriptionActType=" + descriptionActType + ", level=" + level
				+ "]";
	}


	public ArrayList<ActivityType> getActivityTypes() {
		return activityTypes;
	}
	
	
	

	

	
}
