package es.uji.proyecto.model;

public class ActivityType{
	private String typeName;
	private String descriptionActType;
	private String level;


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

	@Override
	public String toString() {
		return "ActivityType [typeName=" + typeName + ", descriptionActType=" + descriptionActType + ", level=" + level
				+ "]";
	}
	
	
	

	

	
}
