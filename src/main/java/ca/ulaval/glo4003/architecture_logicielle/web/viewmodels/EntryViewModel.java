package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;

public class EntryViewModel {

	private List<String> entries;
	private List<String> daysOfWeek;
	private List<String> datesOfWeek;
	private Boolean isReadOnly;
	
	public List<String> getEntries() {
		return entries;
	}
	public void setEntries(List<String> entries) {
		this.entries = entries;
	}
	public List<String> getDaysOfWeek() {
		return daysOfWeek;
	}
	public void setDaysOfWeek(List<String> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
	public List<String> getDatesOfWeek() {
		return datesOfWeek;
	}
	public void setDatesOfWeek(List<String> datesOfWeek) {
		this.datesOfWeek = datesOfWeek;
	}
	public Boolean getIsReadOnly(){
		return isReadOnly;
	}
	public void setIsReadOnly(Boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
}
