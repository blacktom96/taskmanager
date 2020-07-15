package jp.taskmanager.tasksmanager.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.taskmanager.tasksmanager.constant.Constant;
import jp.taskmanager.tasksmanager.model.TaskManagerModel;
import jp.taskmanager.tasksmanager.repository.TaskManagerRepository;



@Service
public class TaskManagerService {
	
	@Autowired
	TaskManagerRepository repository;
	
	private Constant constant;
	
	public List<TaskManagerModel> getAllTaskUnfinished()
	{
		List<TaskManagerModel> result = (List<TaskManagerModel>) repository.getAllTaskUnfinished();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<TaskManagerModel>();
		}
	}
	
	public List<TaskManagerModel> getAllTaskFinished()
	{
		List<TaskManagerModel> result = (List<TaskManagerModel>) repository.getAllTaskFinished();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<TaskManagerModel>();
		}
	}
	
	public TaskManagerModel getTaskById(Integer id) throws Exception 
	{
		Optional<TaskManagerModel> task = repository.findById(id);
		
		if(task.isPresent()) {
			return task.get();
		} else {
			throw new Exception("指定されたIDのタスクレコードは存在しません");
		}
	}
	
	
	@SuppressWarnings("static-access")
	public TaskManagerModel createNewTask(TaskManagerModel model) throws Exception 
	{
		model.setTorokubi(formatDateTime("1"));
		model.setHenkobi(formatDateTime("1"));
		if(model.getKanryoBi().isEmpty() ) {
			model.setJouKyou(constant.UNFINISHIED);
		}else {
			model.setJouKyou(constant.FINISHIED);
		}
		model = repository.save(model);
		
		return model;
	
	}
	
	
	
	
	@SuppressWarnings("static-access")
	public TaskManagerModel createOrUpdateTask(TaskManagerModel model) throws Exception 
	{
		if(model.getTakusuID() == null) 
		{
			createNewTask(model);
			return model;
		} 
		else 
		{
			Optional<TaskManagerModel> task = repository.findById(model.getTakusuID());
			
			if(task.isPresent()) 
			{
				TaskManagerModel addNewTask = task.get();
				addNewTask.setTakusuID(model.getTakusuID());
				addNewTask.setNaiYou(model.getNaiYou());
				addNewTask.setTaitoru(model.getTaitoru());
				addNewTask.setKanryoBi(model.getKanryoBi());
				addNewTask.setYoteiBi(model.getYoteiBi());
				addNewTask.setYuusenjuni(model.getYuusenjuni());
				addNewTask.setTorokubi(addNewTask.getTorokubi());
				addNewTask.setHenkobi(formatDateTime("1"));
				if(null == model.getKanryoBi()) {
					model.setJouKyou(constant.UNFINISHIED);
				}else {
					model.setJouKyou(constant.FINISHIED);
				}

				addNewTask = repository.save(addNewTask);
				
				return addNewTask;
			} else {
				createNewTask(model);
				return model;
			}
		}
	} 
	
	
	public String formatDateTime(String option) throws Exception{
		LocalDateTime now = LocalDateTime.now();
		
		if("1" == option) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH-mm-ss");
			return dtf.format(now);
		}else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			return dtf.format(now);
		}
	}
	
	@SuppressWarnings("static-access")
	public void updateListTask(List<String> taskListID) throws Exception 
	{
		for(int i = 0; i < taskListID.size(); i++) {
			Optional<TaskManagerModel> task = repository.findById(Integer.parseInt(taskListID.get(i)));
			
			if(task.isPresent()) 
			{
				TaskManagerModel taskManagerModel = task.get();
				taskManagerModel.setKaijo(constant.ISDELETE);
				taskManagerModel.setHenkobi(formatDateTime("1"));
				taskManagerModel = repository.save(taskManagerModel);
			}else {
				throw new Exception("指定されたIDのタスクレコードは存在しません");
			}
		}
		
	}
	@SuppressWarnings("static-access")
	public void updateTaskByCheckBox(String taskListID) throws Exception 
	{
		Optional<TaskManagerModel> task = repository.findById(Integer.parseInt(taskListID));
		
		if(task.isPresent()) 
		{
			TaskManagerModel taskManagerModel = task.get();
			
			taskManagerModel.setKanryoBi(formatDateTime("2"));
			taskManagerModel.setHenkobi(formatDateTime("1"));
			taskManagerModel.setJouKyou(constant.FINISHIED);

			taskManagerModel = repository.save(taskManagerModel);
			
			
		}else {
			throw new Exception("指定されたIDのタスクレコードは存在しません");
		}
	}
	
	public Map<String, String> addNewError(){
		Map<String, String> errorItem = new HashMap<String, String>();
		
		errorItem.put("taitoru","");
		errorItem.put("naiYou","");
		errorItem.put("yoteiBi","");
		errorItem.put("kanryoBi","");
		errorItem.put("yuusenjuni","");
		
		return errorItem;
	}
}