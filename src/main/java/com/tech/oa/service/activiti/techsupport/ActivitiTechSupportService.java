package com.tech.oa.service.activiti.techsupport;

import com.baitian.fourb.common.result.FbResult;
import com.tech.oa.service.techsupport.TTechSupportTask;
import com.tech.oa.service.techsupport.TechSupport;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chengli on 2016/11/17.
 */
@Service
public class ActivitiTechSupportService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;

    private static final String PROCESS_KEY = "techsupport";

    @PostConstruct
    public void init() {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(PROCESS_KEY).singleResult();
        if (processDefinition == null) {
            repositoryService.createDeployment().addClasspathResource("techsupport.bpmn").deploy();
            System.out.println("techsupport 流程部署成功");
        }
    }

    public String startNewProcess(int techSupportId, String applyUserId) {
        identityService.setAuthenticatedUserId(applyUserId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, String.valueOf(techSupportId));
        if (processInstance != null) {
            return processInstance.getProcessInstanceId();
        }
        return null;
    }

    public List<Task> queryTask(String userId) {
        return taskService.createTaskQuery().taskCandidateUser(userId).list();
    }

    public void handleNewApplyTask(String taskId, String userId, int handleResult) {
        this.claim(taskId, userId);
        Map<String, Object> vars = new HashMap<>();
        vars.put("handleResult", String.valueOf(handleResult));
        this.complete(taskId, vars);
    }

    private void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    private void complete(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }

}
