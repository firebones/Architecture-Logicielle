package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.web.converters.WeekEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedExpenses;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedHours;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedKilometers;

@Controller
public class WeekEntryController {
	
	private AppConfiguration configuration = new AppConfiguration();
	
	// TODO: probablement ï¿½ remplacer lorsque J-P aura terminï¿½ le repository
	//private WeekEntryRepositoryImpl weekEntryRepository = new WeekEntryRepositoryImpl();
	private WeekEntryConverter projectConverter =  new WeekEntryConverter();
	
	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.GET)
	  public String getEnterTransportion(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
	
		// TODO: Pour le moment, c'est la date en cours qui dï¿½termine la semaine ï¿½ obtenir et le numï¿½ro de semaine.
		// TODO: ï¿½ventuellement, selon la sï¿½rie hebdomadaire qui sera traitï¿½e (le user aura plusieurs sï¿½ries), le bon weekNumber sera passï¿½.
		Date todaysDate = new Date();
		
		int weekNumber = getWeekNumber(todaysDate);
		List<String> daysOfWeek = getDatesOfWeek(todaysDate);
		List<String> datesOfWeek = getDaysOfWeek();
		
		// TODO : trouver comment rï¿½cupï¿½rer le email du user loggï¿½
		List<Integer> kilometersOfWeek = configuration.getWeekEntryByEmailAndWeek(userId, "41").getKilometersEntries();
		
	     model.addAttribute("daysOfWeek", daysOfWeek);
	     model.addAttribute("daysNameOfWeek", datesOfWeek);
	     model.addAttribute("valuesOfWeek", kilometersOfWeek);
	     return "vehicleExpenseEntries";
	  }
	
	@ModelAttribute("assignedKilometers")
	public AssignedKilometers assignedKilometers() {
		return new AssignedKilometers();
	}
	
	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(@ModelAttribute("assignedKilometers") AssignedKilometers assignedKilometers) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
	
		// TODO : remplacer les valeurs hardcodï¿½ par le user en cours, et ï¿½ventuellement, la sï¿½rie hebdomadaire traitï¿½e.
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId, "41");
		List<Integer> kilometers = assignedKilometers.getKilometers();
		weekEntry.setKilometersEntries(kilometers);
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/employeeExpenses", method = RequestMethod.GET)
	  public String getEnterExpenses(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
	
		// TODO: Pour le moment, c'est la date en cours qui dï¿½termine la semaine ï¿½ obtenir et le numï¿½ro de semaine.
		// TODO: ï¿½ventuellement, selon la sï¿½rie hebdomadaire qui sera traitï¿½e (le user aura plusieurs sï¿½ries), le bon weekNumber sera passï¿½.
		Date todaysDate = new Date();
		
		int weekNumber = getWeekNumber(todaysDate);
		List<String> daysOfWeek = getDatesOfWeek(todaysDate);
		List<String> datesOfWeek = getDaysOfWeek();
		
		// TODO : trouver comment rï¿½cupï¿½rer le email du user loggï¿½
		List<Double> expensesOfWeek = configuration.getWeekEntryByEmailAndWeek(userId, "41").getEmployeeExpensesEntries();
		
	     model.addAttribute("daysOfWeek", daysOfWeek);
	     model.addAttribute("daysNameOfWeek", datesOfWeek);
	     model.addAttribute("valuesOfWeek", expensesOfWeek);
	     return "employeeExpenseEntries";
	  }
	
	@ModelAttribute("assignedExpenses")
	public AssignedExpenses assignedexpenses() {
		return new AssignedExpenses();
	}
	
	@RequestMapping(value = "/employeeExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(@ModelAttribute("assignedExpenses") AssignedExpenses assignedExpenses) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
	
		// TODO : remplacer les valeurs hardcodï¿½ par le user en cours, et ï¿½ventuellement, la sï¿½rie hebdomadaire traitï¿½e.
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId, "41");
		List<Double> expenses = assignedExpenses.getExpenses();
		weekEntry.setEmployeeExpensesEntries(expenses);
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/workingHours", method = RequestMethod.GET)
	  public String getEnterHours(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
	
		// TODO: Pour le moment, c'est la date en cours qui dï¿½termine la semaine ï¿½ obtenir et le numï¿½ro de semaine.
		// TODO: ï¿½ventuellement, selon la sï¿½rie hebdomadaire qui sera traitï¿½e (le user aura plusieurs sï¿½ries), le bon weekNumber sera passï¿½.
		Date todaysDate = new Date();
		
		int weekNumber = getWeekNumber(todaysDate);
		List<String> daysOfWeek = getDatesOfWeek(todaysDate);
		List<String> datesOfWeek = getDaysOfWeek();
		
		// TODO : trouver comment rï¿½cupï¿½rer le email du user loggï¿½
		List<Double> hoursOfWeek = configuration.getWeekEntryByEmailAndWeek(userId, "41").getHoursEntries();
		
	     model.addAttribute("daysOfWeek", daysOfWeek);
	     model.addAttribute("daysNameOfWeek", datesOfWeek);
	     model.addAttribute("valuesOfWeek", hoursOfWeek);
	     return "workingHourEntries";
	  }
	
	@ModelAttribute("assignedHours")
	public AssignedHours assignedhours() {
		return new AssignedHours();
	}
	
	@RequestMapping(value = "/workingHours", method = RequestMethod.POST)
	public String getValuesOfWeek(@ModelAttribute("assignedHours") AssignedHours assignedHours) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
	
		// TODO : remplacer les valeurs hardcodées par le user en cours, et éventuellement, la série hebdomadaire traitée.
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId, "41");
		List<Double> hours = assignedHours.getHours();
		weekEntry.setEmployeeExpensesEntries(hours);
		
		return "redirect:/";
	}
	
	
	// TODO: ï¿½ventuellement dï¿½placï¿½?
	private List<String> getDatesOfWeek(Date refDate) {
		
		List<String> dates = new ArrayList<String>(7);

        SimpleDateFormat format = new SimpleDateFormat("dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
 
        for (int i = 0; i < 7; i++)
        {
        	dates.add(format.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        return dates;
    }

	// TODO: ï¿½ventuellement dï¿½placï¿½? prendre les valeurs d'un fichier ressource.
	private List<String> getDaysOfWeek() {

		List<String> daysOfWeek = new ArrayList<String>();
		daysOfWeek.add("Dimanche");
		daysOfWeek.add("Lundi");
		daysOfWeek.add("Mardi");
		daysOfWeek.add("Mercredi");
		daysOfWeek.add("Jeudi");
		daysOfWeek.add("Vendredi");
		daysOfWeek.add("Samedi");
		
		return daysOfWeek;
	}
	
	// TODO: ï¿½ventuellement dï¿½placï¿½?
	private int getWeekNumber(Date refDate) {
		
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(refDate);
	     calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	     int week = calendar.get(Calendar.WEEK_OF_YEAR);
		return week;
	}
}
