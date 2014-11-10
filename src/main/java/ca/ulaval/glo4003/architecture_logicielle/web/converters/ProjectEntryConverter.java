package ca.ulaval.glo4003.architecture_logicielle.web.converters;

import java.util.ArrayList;
//import java.util.Collection;
//import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignTasksViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.ProjectViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.TaskViewModel;

@Component
public class ProjectEntryConverter {

	public AssignTasksViewModel toProjectViewModels(List<ProjectEntry> projects, List<TaskEntry> tasks, String userName){
		
		AssignTasksViewModel assignTaskViewModel = new AssignTasksViewModel();
		List<ProjectViewModel> projectsViewModel = new ArrayList<ProjectViewModel>();
		for (ProjectEntry project : projects) {
			ProjectViewModel viewModel = toProjectViewModel(project, tasks);
			projectsViewModel.add(viewModel);
		}
		
		assignTaskViewModel.projects = projectsViewModel;
		assignTaskViewModel.userId = userName;
		
		return assignTaskViewModel;
	}
	
	private ProjectViewModel toProjectViewModel(ProjectEntry project, List<TaskEntry> tasks) {
		
		ProjectViewModel projectViewModel = new ProjectViewModel();
		projectViewModel.name = project.getName();
		projectViewModel.id = project.getId();
		
		List<TaskViewModel> tasksViewModelList = new ArrayList<TaskViewModel>();
		for(TaskEntry task : project.getTasks())
		{
			TaskViewModel taskViewModel = new TaskViewModel();
			taskViewModel.id = task.getId();
			taskViewModel.name = task.getName();
			taskViewModel.isAssigned = tasks.contains(task);
			
			tasksViewModelList.add(taskViewModel);
		}
		
		projectViewModel.setTasks(tasksViewModelList);
		
		return projectViewModel;
	}
	
	public ProjectEntry toProjectEntry(ProjectViewModel viewModel) {
		ProjectEntry project = new ProjectEntry();
		project.setName(viewModel.getName());
		project.setId(viewModel.getId());
		return project;
	}
	
	public TaskEntry toTaskEntry(TaskViewModel viewModel) {
		TaskEntry task = new TaskEntry();
		task.setName(viewModel.getName());
		task.setRate(viewModel.getRate());
		task.setId(viewModel.getId());
		return task;
	}
}
