package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.Date;

public class PayPeriod
{
	private Date startDate;
	private Date endDate;
	
	public PayPeriod(Date startDate, Date endDate){
		
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
