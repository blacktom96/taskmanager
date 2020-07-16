package jp.taskmanager.tasksmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.taskmanager.tasksmanager.model.TaskManagerModel;
import jp.taskmanager.tasksmanager.service.TaskManagerService;
import jp.taskmanager.tasksmanager.validation.Validation;


@Controller
@RequestMapping("/")
public class TaskManagerController 
{
	@Autowired
	TaskManagerService service;
	
	@Autowired
	Validation validation;
	
	@RequestMapping
	public String getAllTask(Model model) 
	{
		List<TaskManagerModel> listTaskUnfinished = service.getAllTaskUnfinished();
		List<TaskManagerModel> listTaskFinished = service.getAllTaskFinished();
		
		model.addAttribute("listTaskUnfinished", listTaskUnfinished);
		model.addAttribute("listTaskFinished", listTaskFinished);
		return "list-task";
	}

	@RequestMapping(value = {"/addNew", "/edit/{id}"})
	public String editTaskById(Model model, @PathVariable("id") Optional<Integer> taskId) 
							throws Exception 
	{
		if (taskId.isPresent()) {
			TaskManagerModel task = service.getTaskById(taskId.get());
			model.addAttribute("task", task);
			model.addAttribute("errorItem", service.addNewError());
			model.addAttribute("taskID", task.getTakusuID());
		} else {
			model.addAttribute("task", new TaskManagerModel());
			model.addAttribute("errorItem", service.addNewError());
			model.addAttribute("taskID", "");
		}
		return "add-edit-task";
	}
	
	
	@RequestMapping(path = "/createOrUpdateTask", method = RequestMethod.POST)
	public String createOrUpdateTask(Model model,TaskManagerModel taskModel) throws Exception 
	{
		Map<String, String> errorItem = new HashMap<String, String>();
		
		if(validation.validationModel(taskModel, errorItem)) {
			model.addAttribute("errorItem", errorItem);
			model.addAttribute("task", taskModel);
			if(taskModel.getTakusuID() == null) {
				model.addAttribute("taskID", "");
			}else {
				model.addAttribute("taskID", taskModel.getTakusuID());
			}
			return "add-edit-task";
		}else {
			service.createOrUpdateTask(taskModel);
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(path = "/updateTaskByCheckBox", method = RequestMethod.POST)
	public String updateTaskByCheckBox(Model model, @RequestBody String taskListID) throws Exception 
	{
		service.updateTaskByCheckBox(taskListID);
		return "redirect:/";
	}
	
	@RequestMapping(path = "/deleteTask", method = RequestMethod.POST)
	public String deleteTask(@RequestBody List<String> taskListID, Model model) throws Exception 
	{
		service.updateListTask(taskListID);
		
		List<TaskManagerModel> listTaskUnfinished = service.getAllTaskUnfinished();
		List<TaskManagerModel> listTaskFinished = service.getAllTaskFinished();
		
		model.addAttribute("listTaskUnfinished", listTaskUnfinished);
		model.addAttribute("listTaskFinished", listTaskFinished);
		
		return "list-task";
	}
}
