package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.converters.WeekEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.dao.WeekEntryRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedKilometers;

@Controller
public class WeekEntryController {
	
	// TODO: probablement à remplacer lorsque J-P aura terminé le repository
	private WeekEntryRepositoryImpl weekEntryRepository = new WeekEntryRepositoryImpl();
	private WeekEntryConverter projectConverter =  new WeekEntryConverter();
	
	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.GET)
	  public String getEnterTransportion(Model model) {
	
		// TODO: Pour le moment, c'est la date en cours qui détermine la semaine à obtenir et le numéro de semaine.
		// TODO: Éventuellement, selon la série hebdomadaire qui sera traitée (le user aura plusieurs séries), le bon weekNumber sera passé.
		Date todaysDate = new Date();
		
		int weekNumber = getWeekNumber(todaysDate);
		List<String> daysOfWeek = getDatesOfWeek(todaysDate);
		List<String> datesOfWeek = getDaysOfWeek();
		
		// TODO : trouver comment récupérer le email du user loggé
		List<Integer> kilometersOfWeek = weekEntryRepository.getWeekEntryByEmailAndWeek("joe@gmail.com", "41").getKilometersEntries();
		
	     model.addAttribute("daysOfWeek", daysOfWeek);
	     model.addAttribute("daysNameOfWeek", datesOfWeek);
	     model.addAttribute("valuesOfWeek", kilometersOfWeek);
	     return "vehicleExpenseEntries";
	  }
	
	@ModelAttribute("assignedKilometers")
	public AssignedKilometers assignedKilometers() {
		return new AssignedKilometers();
	}
	
	// TODO : ne fonctionne pas correctement. Je ne peux pas récupérer la liste du formulaire. Pourtant je vois ma liste dans Firebug.
	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(@ModelAttribute("assignedKilometers") AssignedKilometers assignedKilometers) {
	
		// TODO : remplacer les valeurs hardcodé par le user en cours, et éventuellement, la série hebdomadaire traitée.
		WeekEntry weekEntry = weekEntryRepository.getWeekEntryByEmailAndWeek("joe@gmail.com", "41");
		List<Integer> test = assignedKilometers.getKilometers();
		weekEntry.setKilometersEntries(test);
		
		return "redirect:/";
	}

	// TODO: éventuellement déplacé?
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

	// TODO: éventuellement déplacé? prendre les valeurs d'un fichier ressource.
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
	
	// TODO: éventuellement déplacé?
	private int getWeekNumber(Date refDate) {
		
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(refDate);
	     calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	     int week = calendar.get(Calendar.WEEK_OF_YEAR);
		return week;
	}
}
