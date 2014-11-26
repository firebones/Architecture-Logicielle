package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.ulaval.glo4003.architecture_logicielle.web.converters.WeekEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.StateWeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedExpenses;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedHours;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedKilometers;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EntryViewModel;

@Controller
public class WeekEntryController {

	private AppConfiguration configuration = new AppConfiguration();
	private WeekEntryConverter converter = new WeekEntryConverter();
	private ValidatorWeekEntry validator = new ValidatorWeekEntry();

	@RequestMapping(value = "/{year}/{week}/vehicleExpenses", method = RequestMethod.GET)
	public String getEnterTransportion(@PathVariable Integer week, @PathVariable Integer year, Model model, @ModelAttribute("errorMessage") String errorMessage, @ModelAttribute("assignedKilometers") AssignedKilometers kilometers) {

		EntryViewModel weekEntryViewModel = converter.toKilometersEntryViewModel(configuration.getCurrentUserWeekEntry(configuration.getCurrentUser(), week, year), kilometers);
		model.addAttribute("weekEntry", weekEntryViewModel);
		model.addAttribute("errorMessage", errorMessage);
		return "vehicleExpenseEntries";
	}

	@ModelAttribute("assignedKilometers")
	public AssignedKilometers assignedKilometers() {
		return new AssignedKilometers();
	}

	@RequestMapping(value = "/{year}/{week}/vehicleExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(@PathVariable Integer week, @PathVariable Integer year,@ModelAttribute("assignedKilometers") AssignedKilometers assignedKilometers, RedirectAttributes redirectAttributes) {

		String errorMessage = validator.ValidateAssignedKilometers(assignedKilometers.getKilometers());
		if (!errorMessage.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedKilometers", assignedKilometers);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/{year}/{week}/vehicleExpenses";
		}

		configuration.assignKilometersToEmployeeWeek(configuration.getCurrentUser(), week, year, converter.convertStringsToIntegers(assignedKilometers.getKilometers()));

		return "redirect:/weekEntriesList";
	}

	@RequestMapping(value = "/{year}/{week}/employeeExpenses", method = RequestMethod.GET)
	public String getEnterExpenses(@PathVariable Integer week, @PathVariable Integer year, Model model, @ModelAttribute("errorMessage") String errorMessage, @ModelAttribute("assignedExpenses") AssignedExpenses expenses) {

		EntryViewModel weekEntryViewModel = converter.toExpensesEntryViewModel(configuration.getCurrentUserWeekEntry(configuration.getCurrentUser(), week, year), expenses);
		model.addAttribute("weekEntry", weekEntryViewModel);
		model.addAttribute("errorMessage", errorMessage);
		return "employeeExpenseEntries";
	}

	@ModelAttribute("assignedExpenses")
	public AssignedExpenses assignedexpenses() {
		return new AssignedExpenses();
	}

	@RequestMapping(value = "/{year}/{week}/employeeExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(@PathVariable Integer week, @PathVariable Integer year,@ModelAttribute("assignedExpenses") AssignedExpenses assignedExpenses, RedirectAttributes redirectAttributes) {

		String errorMessage = validator.ValidateAssignedExpenses(assignedExpenses.getExpenses());
		if (!errorMessage.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedExpenses", assignedExpenses);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/{year}/{week}/employeeExpenses";
		}

		configuration.assignExpensesToEmployeeWeek(configuration.getCurrentUser(), week, year, converter.convertStringsToDoubles(assignedExpenses.getExpenses()));

		return "redirect:/weekEntriesList";
	}
	
	@RequestMapping(value = "/{year}/{week}/workingHours", method = RequestMethod.GET)
	public String getEnterHours(@PathVariable Integer week, @PathVariable Integer year, Model model, @ModelAttribute("errorMessage") String errorMessage, @ModelAttribute("assignedHours") AssignedHours hours) {
		EntryViewModel weekEntryViewModel = converter.toHoursEntryViewModel(configuration.getCurrentUserWeekEntry(configuration.getCurrentUser(), week, year), hours);
		model.addAttribute("weekEntry", weekEntryViewModel);
		model.addAttribute("errorMessage", errorMessage);
		return "workingHourEntries";
	}

	@ModelAttribute("assignedHours")
	public AssignedHours assignedhours() {
		return new AssignedHours();
	}

	@RequestMapping(value = "/{year}/{week}/workingHours", method = RequestMethod.POST)
	public String getHoursOfWeek(@PathVariable Integer week, @PathVariable Integer year, @ModelAttribute("assignedHours") AssignedHours assignedHours, RedirectAttributes redirectAttributes) {

		String errorMessage = validator.ValidateAssignedHours(assignedHours.getHours());
		if (!errorMessage.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedHours", assignedHours);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/{year}/{week}/workingHours";
		}

		configuration.assignHoursToEmployeeWeek(configuration.getCurrentUser(), week, year, converter.convertStringsToDoubles(assignedHours.getHours()));

		return "redirect:/weekEntriesList";
	}
	
	@RequestMapping(value = "/weekEntriesList", method = RequestMethod.GET)
	public String weekEntries(Model model) {
		model.addAttribute("weekEntries", configuration.getCurrentUserWeekEntries());
		return "weekEntriesList";
	}
	
	@RequestMapping(value = "/{year}/{week}/submitWeekEntry", method = RequestMethod.GET)
	public String submitWeekEntry(@PathVariable Integer week, @PathVariable Integer year, Model model) {
		configuration.submitWeekEntry(configuration.getCurrentUser(), week, year);
		return "redirect:/weekEntriesList";
	}

	@RequestMapping(value = "/submittedEntryList", method = RequestMethod.GET)
	public String submittedEntries(Model model) {
//		Authentication auth = SecurityContextHolder.getContext()
//				.getAuthentication();
//		String department = ((EmployeeEntry) auth.getPrincipal())
//				.getDepartment();

		List<WeekEntry> weekEntries = new LinkedList<WeekEntry>();
		List<String> userEntries = new LinkedList<String>();

//		WeekEntry w = new WeekEntry();
//		w.setEmail("aaa@ddd.com");
//		w.setWeekNumber(43);
//		w.setYearNumber(2014);
//		w.setState(StateWeekEntry.SUBMITTED);
//		w = configuration.getCurrentUserWeekEntry(configuration.getCurrentUser(), 41, 2014);
//		weekEntries.add(w);
//		weekEntries.add(w);

		for (WeekEntry weekEntry : configuration.getAllWeekEntries()) {
			if (weekEntry.getState() == StateWeekEntry.SUBMITTED) {
				weekEntries.add(weekEntry);
//				if(!userEntries.contains(weekEntry.getEmail()))
//					userEntries.add(weekEntry.getEmail());
			}
		}

//		model.addAttribute("daysNameOfWeek", w.getDaysOfWeek());
		model.addAttribute("weekEntries", weekEntries);
//		model.addAttribute("hours", w.getHoursEntries());
		return "submittedEntries";
	}

/*	@RequestMapping(value = "/approve", method = RequestMethod.POST, params = { "approve" })
	public String approve(@ModelAttribute("entry.email") String email,
			@ModelAttribute("entry") WeekEntry entry,
			@ModelAttribute("entry.weekNumber") String weekNumber,
			@ModelAttribute("entry.yearNumber") String yearNumber,
			@RequestParam String approve, Model model) {
		entry.setState(StateWeekEntry.APPROVED);
		entry.setEmail(email);
		entry.setWeekNumber(Integer.parseInt(weekNumber));
		entry.setYearNumber(Integer.parseInt(yearNumber));
		configuration.updateWeekEntry(entry);
		return "redirect:/submittedEntryList";
	}*/
	
	
	@RequestMapping(value = "/approve", method = RequestMethod.POST, params = { "approve" })
	public String approve(@ModelAttribute("entry.email") String email,
			@ModelAttribute("entry") WeekEntry entry,
			@ModelAttribute("entry.weekNumber") String weekNumber,
			@ModelAttribute("entry.yearNumber") String yearNumber,
			@RequestParam String approve, Model model) {
		configuration.approvedWeekEntry(email, Integer.parseInt(weekNumber), Integer.parseInt(yearNumber));
		return "redirect:/submittedEntryList";
	}
	
/*	@RequestMapping(value = "/approve", method = RequestMethod.POST, params = { "deny" })
	public String deny(@ModelAttribute("entry.email") String email,
			@ModelAttribute("entry") WeekEntry entry,
			@ModelAttribute("entry.weekNumber") String weekNumber,
			@ModelAttribute("entry.yearNumber") String yearNumber,
			@RequestParam String deny, Model model) {
		entry.setState(StateWeekEntry.REFUSED);
		entry.setEmail(email);
		entry.setWeekNumber(Integer.parseInt(weekNumber));
		entry.setYearNumber(Integer.parseInt(yearNumber));
		configuration.updateWeekEntry(entry);
		return "redirect:/submittedEntryList";
	}*/
	
	@RequestMapping(value = "/deny",  method = RequestMethod.POST, params = { "deny" })
	public String deny(@ModelAttribute("entry.email") String email,
			@ModelAttribute("entry") WeekEntry entry,
			@ModelAttribute("entry.weekNumber") String weekNumber,
			@ModelAttribute("entry.yearNumber") String yearNumber,
			@RequestParam String approve, Model model) {
		configuration.deniedWeekEntry(email, Integer.parseInt(weekNumber), Integer.parseInt(yearNumber));
		return "redirect:/submittedEntryList";
	}
}
