package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

@Controller
public class WeekEntryController {

	private AppConfiguration configuration = new AppConfiguration();
	private WeekEntryConverter converter = new WeekEntryConverter();
	private Validators validator = new Validators();

	// TODO : ajouter le id de la weekentry cliqué depuis l'interface de liste
	// de weekentries
	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.GET)
	public String getEnterTransportion(Model model,
			@ModelAttribute("errorBlock") String errorBlock,
			@ModelAttribute("assignedKilometers") AssignedKilometers kilometers) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId,
				41, 2014);

		Date date = getDateForWeekNumber(weekEntry.getWeekNumber(),
				weekEntry.getYearNumber());
		List<String> daysOfWeek = getDatesOfWeek(date);
		List<String> datesOfWeek = getDaysOfWeek();

		List<String> kilometersOfWeek;
		if (kilometers.getKilometers() != null)
			kilometersOfWeek = kilometers.getKilometers();
		else
			kilometersOfWeek = converter.convertIntegerToStringList(weekEntry
					.getKilometersEntries());

		if (errorBlock == null)
			errorBlock = "";

		Boolean isReadOnly = false;
		if (weekEntry.getState() != StateWeekEntry.INPROGRESS)
			isReadOnly = true;

		model.addAttribute("daysOfWeek", daysOfWeek);
		model.addAttribute("daysNameOfWeek", datesOfWeek);
		model.addAttribute("valuesOfWeek", kilometersOfWeek);
		model.addAttribute("errorBlock", errorBlock);
		model.addAttribute("isReadOnly", isReadOnly);

		return "vehicleExpenseEntries";
	}

	@ModelAttribute("assignedKilometers")
	public AssignedKilometers assignedKilometers() {
		return new AssignedKilometers();
	}

	@RequestMapping(value = "/vehicleExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(
			@ModelAttribute("assignedKilometers") AssignedKilometers assignedKilometers,
			RedirectAttributes redirectAttributes) {

		String messageError = validator
				.ValidateAssignedKilometers(assignedKilometers.getKilometers());
		if (!messageError.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedKilometers",
					assignedKilometers);
			redirectAttributes.addFlashAttribute("errorBlock", messageError);
			return "redirect:/vehicleExpenses";
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId,
				41, 2014);
		List<String> kilometers = assignedKilometers.getKilometers();
		weekEntry.setKilometersEntries(converter
				.convertStringsToIntegers(kilometers));
		configuration.updateWeekEntry(weekEntry);

		return "redirect:/";
	}

	// TODO : ajouter le id de la weekentry cliqué depuis l'interface de liste
	// de weekentries
	@RequestMapping(value = "/employeeExpenses", method = RequestMethod.GET)
	public String getEnterExpenses(Model model,
			@ModelAttribute("errorBlock") String errorBlock,
			@ModelAttribute("assignedExpenses") AssignedExpenses expenses) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId,
				41, 2014);

		Date date = getDateForWeekNumber(weekEntry.getWeekNumber(),
				weekEntry.getYearNumber());
		List<String> daysOfWeek = getDatesOfWeek(date);
		List<String> datesOfWeek = getDaysOfWeek();

		List<String> expensesOfWeek;
		if (expenses.getExpenses() != null)
			expensesOfWeek = expenses.getExpenses();
		else
			expensesOfWeek = converter.convertDoublesToStringList(weekEntry
					.getEmployeeExpensesEntries());

		if (errorBlock == null)
			errorBlock = "";

		Boolean isReadOnly = false;
		if (weekEntry.getState() != StateWeekEntry.INPROGRESS)
			isReadOnly = true;

		model.addAttribute("daysOfWeek", daysOfWeek);
		model.addAttribute("daysNameOfWeek", datesOfWeek);
		model.addAttribute("valuesOfWeek", expensesOfWeek);
		model.addAttribute("errorBlock", errorBlock);
		model.addAttribute("isReadOnly", isReadOnly);

		return "employeeExpenseEntries";
	}

	@ModelAttribute("assignedExpenses")
	public AssignedExpenses assignedexpenses() {
		return new AssignedExpenses();
	}

	@RequestMapping(value = "/employeeExpenses", method = RequestMethod.POST)
	public String getValuesOfWeek(
			@ModelAttribute("assignedExpenses") AssignedExpenses assignedExpenses,
			RedirectAttributes redirectAttributes) {

		String messageError = validator
				.ValidateAssignedExpenses(assignedExpenses.getExpenses());
		if (!messageError.isEmpty()) {
			redirectAttributes.addFlashAttribute("assignedExpenses",
					assignedExpenses);
			redirectAttributes.addFlashAttribute("errorBlock", messageError);
			return "redirect:/employeeExpenses";
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId,
				41, 2014);
		List<String> expenses = assignedExpenses.getExpenses();
		weekEntry.setEmployeeExpensesEntries(converter
				.convertToDoubleList(expenses));
		configuration.updateWeekEntry(weekEntry);

		return "redirect:/";
	}

	// TODO : ajouter le id de la weekentry cliqué depuis l'interface de liste
	// de weekentries
	@RequestMapping(value = "/workingHours", method = RequestMethod.GET)
	public String getEnterHours(Model model,
			@ModelAttribute("errorBlock") String errorBlock,
			@ModelAttribute("assignedHours") AssignedHours hours) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId,
				41, 2014);

		Date date = getDateForWeekNumber(weekEntry.getWeekNumber(),
				weekEntry.getYearNumber());
		List<String> daysOfWeek = getDatesOfWeek(date);
		List<String> datesOfWeek = getDaysOfWeek();

		List<String> hoursOfWeek;
		if (hours.getHours() != null)
			hoursOfWeek = hours.getHours();
		else
			hoursOfWeek = converter.convertDoublesToStringList(weekEntry
					.getHoursEntries());

		if (errorBlock == null)
			errorBlock = "";

		Boolean isReadOnly = false;
		if (weekEntry.getState() != StateWeekEntry.INPROGRESS)
			isReadOnly = true;

		model.addAttribute("daysOfWeek", daysOfWeek);
		model.addAttribute("daysNameOfWeek", datesOfWeek);
		model.addAttribute("valuesOfWeek", hoursOfWeek);
		model.addAttribute("errorBlock", errorBlock);
		model.addAttribute("isReadOnly", isReadOnly);

		return "workingHourEntries";
	}

	@ModelAttribute("assignedHours")
	public AssignedHours assignedhours() {
		return new AssignedHours();
	}

	@RequestMapping(value = "/workingHours", method = RequestMethod.POST)
	public String getHoursOfWeek(
			@ModelAttribute("assignedHours") AssignedHours assignedHours,
			RedirectAttributes redirectAttributes) {

		String messageError = validator.ValidateAssignedHours(assignedHours
				.getHours());
		if (!messageError.isEmpty()) {
			redirectAttributes
					.addFlashAttribute("assignedHours", assignedHours);
			redirectAttributes.addFlashAttribute("errorBlock", messageError);
			return "redirect:/workingHours";
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = ((EmployeeEntry) auth.getPrincipal()).getEmail();
		WeekEntry weekEntry = configuration.getWeekEntryByEmailAndWeek(userId,
				41, 2014);
		List<String> hours = assignedHours.getHours();
		weekEntry.setHoursEntries(converter.convertStringsToDoubles(hours));
		configuration.updateWeekEntry(weekEntry);

		return "redirect:/";
	}

	@RequestMapping(value = "/submittedEntryList", method = RequestMethod.GET)
	public String submittedEntries(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String department = ((EmployeeEntry) auth.getPrincipal())
				.getDepartment();

		List<String> daysOfWeek = getDaysOfWeek();
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
			if (w1.getState() != StateWeekEntry.SUBMITTED) {
				weekEntries.add(w1);
			}
		}

		model.addAttribute("daysNameOfWeek", daysOfWeek);
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

	private List<String> getDatesOfWeek(Date refDate) {

		List<String> dates = new ArrayList<String>(7);

		SimpleDateFormat format = new SimpleDateFormat("dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(refDate);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		for (int i = 0; i < 7; i++) {
			dates.add(format.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return dates;
	}

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

	private int getWeekNumber(Date refDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(refDate);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		return week;
	}

	private Date getDateForWeekNumber(int weekNumber, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}
}
