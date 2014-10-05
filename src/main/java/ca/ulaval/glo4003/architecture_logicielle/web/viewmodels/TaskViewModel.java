package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

public class TaskViewModel {

	public Integer id;
	public String name;
	public Boolean isAssigned;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsAssigned() {
		return isAssigned;
	}
	
	public void setIsAssigned(Boolean isAssigned) {
		this.isAssigned = isAssigned;
	}
}
