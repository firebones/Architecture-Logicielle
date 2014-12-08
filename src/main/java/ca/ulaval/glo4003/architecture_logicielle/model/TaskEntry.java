package ca.ulaval.glo4003.architecture_logicielle.model;

public class TaskEntry {
	private Integer id;
	private String name;
	private Double rate;
	private Double hours;
	
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
	
	public Double getRate() {
		return rate;
	}
	
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	public Double getHours(){
		return hours;
	}
	
	public void setHours(Double hours){
		this.hours = hours;
	}
	
	@Override
	public boolean equals(Object object) {
		return this.id == ((TaskEntry) object).getId();
	}
}
