package ca.ulaval.glo4003.architecture_logicielle.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignTasksViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.ProjectViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.TaskViewModel;

@Component
public class ProjectEntryConverter {

	public AssignTasksViewModel convertProjects(List<ProjectEntry> projects, List<String> tasks, String userName){
		
		AssignTasksViewModel assignTaskView = new AssignTasksViewModel();
		List<ProjectViewModel> projectsViewModel = new ArrayList<ProjectViewModel>();
		for (ProjectEntry project : projects) {
			ProjectViewModel viewModel = convertProjects(project, tasks);
			projectsViewModel.add(viewModel);
		}
		
		assignTaskView.projects = projectsViewModel;
		assignTaskView.userId = userName;
		
		return assignTaskView;
	}
	
	private ProjectViewModel convertProjects(ProjectEntry project, List<String> tasks) {
		
		ProjectViewModel projectModel = new ProjectViewModel();
		projectModel.name = project.getName();
		projectModel.id = project.getId();
		
		List<TaskViewModel> tasksModelList = new ArrayList<TaskViewModel>();
		for(TaskEntry task : project.getTasks())
		{
			TaskViewModel taskModel = new TaskViewModel();
			taskModel.id = task.getId();
			taskModel.name = task.getName();
			taskModel.isAssigned = VerifyTaskIsAssigned(project.getId(), task.getId(), tasks);
			
			tasksModelList.add(taskModel);
		}
		
		projectModel.setTasks(tasksModelList);
		
		return projectModel;
	}
	
	private Boolean VerifyTaskIsAssigned(Integer projetId, Integer taskId, List<String> tasks) {
		
		String concatenatedId = projetId + "-" + taskId;
		return tasks.contains(concatenatedId);
	}

}
