package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

public class TaskViewModel {

	public Integer id;
	public String name;
	public Boolean assigned;
	
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

	public Boolean isAssigned() {
		return assigned;
	}
	
	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}
}
