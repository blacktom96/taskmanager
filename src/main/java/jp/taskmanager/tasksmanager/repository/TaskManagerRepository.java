package jp.taskmanager.tasksmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jp.taskmanager.tasksmanager.model.TaskManagerModel;

@Repository
public interface TaskManagerRepository extends CrudRepository<TaskManagerModel, Integer> {
	
	@Query(value = "SELECT * FROM taskmanager u WHERE u.joukyou = 1 and u.kaijo = 0 ORDER BY u.yoteibi ASC", 
			  nativeQuery = true)
			List<TaskManagerModel> getAllTaskUnfinished();
	
	
	@Query(value = "SELECT * FROM taskmanager u WHERE u.joukyou = 2 and u.kaijo = 0 ORDER BY u.kanryobi DESC", 
			  nativeQuery = true)
			List<TaskManagerModel> getAllTaskFinished();
}
