package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.ulaval.glo4003.architecture_logicielle.web.converters.WeekEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.StateWeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedExpenses;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedHours;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedKilometers;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.WeekEntryViewModel;

@Controller
public class WeekEntryController {

	private AppConfiguration configuration = new AppConfiguration();
	private WeekEntryConverter converter = new WeekEntryConverter();
	private ValidatorWeekEntry validator = new ValidatorWeekEntry();

	// TODO : ajouter le id de la weekentry cliqué depuis l'interface de liste
	// de weekentries
	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.GET)
	public String getEnterTransportion(Model model, @ModelAttribute("errorMessage") String errorMessage, @ModelAttribute("assignedKilometers") AssignedKilometers kilometers) {

		WeekEntryViewModel weekEntryViewModel = converter.toKilometersWeekEntryViewModel(configuration.getCurrentUserWeekEntry(41, 2014), kilometers);
		model.addAttribute("weekEntry", weekEntryViewModel);
		model.addAttribute("errorMessage", errorMessage);
		return "vehicleExpenseEntries";
	}

	@ModelAttribute("assignedKilometers")
	public AssignedKilometers assignedKilometers() {
		return new AssignedKilometers();
	}

	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(@ModelAttribute("assignedKilometers") AssignedKilometers assignedKilometers, RedirectAttributes redirectAttributes) {

		String errorMessage = validator.ValidateAssignedKilometers(assignedKilometers.getKilometers());
		if (!errorMessage.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedKilometers", assignedKilometers);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/vehicleExpenses";
		}

		configuration.assignKilometersToEmployee(41, 2014, converter.convertStringsToIntegers(assignedKilometers.getKilometers()));

		return "redirect:/";
	}

	// TODO : ajouter le id de la weekentry cliqué depuis l'interface de liste
	// de weekentries
	@RequestMapping(value = "/employeeExpenses", method = RequestMethod.GET)
	public String getEnterExpenses(Model model, @ModelAttribute("errorMessage") String errorMessage, @ModelAttribute("assignedExpenses") AssignedExpenses expenses) {

		WeekEntryViewModel weekEntryViewModel = converter.toExpensesWeekEntryViewModel(configuration.getCurrentUserWeekEntry(41, 2014), expenses);
		model.addAttribute("weekEntry", weekEntryViewModel);
		model.addAttribute("errorMessage", errorMessage);
		return "employeeExpenseEntries";
	}

	@ModelAttribute("assignedExpenses")
	public AssignedExpenses assignedexpenses() {
		return new AssignedExpenses();
	}

	@RequestMapping(value = "/employeeExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(@ModelAttribute("assignedExpenses") AssignedExpenses assignedExpenses, RedirectAttributes redirectAttributes) {

		String errorMessage = validator.ValidateAssignedExpenses(assignedExpenses.getExpenses());
		if (!errorMessage.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedExpenses", assignedExpenses);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/employeeExpenses";
		}

		configuration.assignExpensesToEmployee(41, 2014, converter.convertStringsToDoubles(assignedExpenses.getExpenses()));

		return "redirect:/";
	}
	
	// TODO : ajouter le id de la weekentry cliqué depuis l'interface de liste
	// de weekentries
	@RequestMapping(value = "/workingHours", method = RequestMethod.GET)
	public String getEnterHours(Model model, @ModelAttribute("errorMessage") String errorMessage, @ModelAttribute("assignedHours") AssignedHours hours) {
		WeekEntryViewModel weekEntryViewModel = converter.toHoursWeekEntryViewModel(configuration.getCurrentUserWeekEntry(41, 2014), hours);
		model.addAttribute("weekEntry", weekEntryViewModel);
		model.addAttribute("errorMessage", errorMessage);
		return "workingHourEntries";
	}

	@ModelAttribute("assignedHours")
	public AssignedHours assignedhours() {
		return new AssignedHours();
	}

	@RequestMapping(value = "/workingHours", method = RequestMethod.POST)
	public String getHoursOfWeek(@ModelAttribute("assignedHours") AssignedHours assignedHours, RedirectAttributes redirectAttributes) {

		String errorMessage = validator.ValidateAssignedHours(assignedHours.getHours());
		if (!errorMessage.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedHours", assignedHours);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/workingHours";
		}

		configuration.assignHoursToEmployee(41, 2014, converter.convertStringsToDoubles(assignedHours.getHours()));

		return "redirect:/";
	}

	@RequestMapping(value = "/submittedEntryList", method = RequestMethod.GET)
	public String submittedEntries(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String department = ((EmployeeEntry) auth.getPrincipal())
				.getDepartment();

		List<WeekEntry> weekEntries = new LinkedList<WeekEntry>();

		WeekEntry w = new WeekEntry();
		w.setEmail("aaa@ddd.com");
		w.setWeekNumber(43);
		w.setYearNumber(2014);
		w.setState(StateWeekEntry.SUBMITTED);
		w = configuration.getWeekEntryByEmailAndWeek("joe@gmail.com", 41, 2014);
		weekEntries.add(w);
		weekEntries.add(w);

		for (WeekEntry w1 : configuration.getAllWeekEntries()) {
			if ((w1.getState() != StateWeekEntry.APPROVED) && (w1.getState() != StateWeekEntry.REFUSED)) {
				weekEntries.add(w1);
			}
		}

		model.addAttribute("daysNameOfWeek", w.getDaysOfWeek());
		model.addAttribute("weekEntries", weekEntries);
		model.addAttribute("hours", w.getHoursEntries());
		return "submittedEntries";
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST, params = { "approve" })
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
	}
	
	@RequestMapping(value = "/approve", method = RequestMethod.POST, params = { "deny" })
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
	}
}
