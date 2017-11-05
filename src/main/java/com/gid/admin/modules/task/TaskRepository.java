package com.gid.admin.modules.task;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.gid.admin.model.Task;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, String> {
	List<Task> findByTaskId(String taskId);
}
