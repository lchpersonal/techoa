package com.tech.oa.facade;

import com.baitian.fourb.common.selfdefbean.FourbFacade;
import com.tech.oa.service.activiti.techsupport.ActivitiTechSupportService;
import com.tech.oa.service.techsupport.TTechSupportTask;
import com.tech.oa.service.techsupport.TechSupport;
import com.tech.oa.service.techsupport.TechSupportService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chengli on 2016/11/17.
 */
@FourbFacade
public class TechSupportFacadeService {

    @Autowired
    private ActivitiTechSupportService activitiTechSupportService;
    @Autowired
    private TechSupportService techSupportService;

    public List<TTechSupportTask> queryTTechSupportTask(String userId) {
        List<Task> tasks = activitiTechSupportService.queryTask(userId);
        if (tasks == null || tasks.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> processIds = tasks.stream().map(task -> task.getProcessInstanceId()).collect(Collectors.toList());
        Map<String, Task> processId2Task = tasks.stream().collect(Collectors.toMap(Task::getProcessInstanceId, (task) -> task));
        List<TechSupport> datas = techSupportService.queryByProcessIds(processIds);
        List<TTechSupportTask> tTechSupportTasks = new ArrayList<>();
        for (TechSupport data : datas) {
            TTechSupportTask tTask = new TTechSupportTask();
            tTask.setId(data.getId());
            tTask.setTaskId(processId2Task.get(data.getProcessId()).getId());
            tTask.setApplyTime(data.getApplyTime());
            tTask.setApplyUserId(data.getApplyUserId());
            tTask.setContent(data.getContent());
            tTask.setProcessId(data.getProcessId());
            tTechSupportTasks.add(tTask);
        }
        return tTechSupportTasks;
    }

}
