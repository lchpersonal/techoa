package com.tech.oa.controller;

import com.tech.oa.service.activiti.ActivitiLeaveService;
import com.tech.oa.service.leave.Leave;
import com.tech.oa.service.leave.LeaveService;
import com.tech.oa.service.user.User;
import com.tech.oa.service.user.UserService;
import com.baitian.fourb.common.result.Result;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by chengli on 2016/11/16.
 */
@Controller
@RequestMapping("/leave")
@Transactional
public class LeaveController {

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivitiLeaveService activitiLeaveService;

    @RequestMapping("/apply")
    public Result<?> leave(Leave leave, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        leave.setUsername(username);
        int id = leaveService.newLeave(leave);
        leave.setId(id);
        String processId = activitiLeaveService.startNewLeaveProcess(leave);
        if (!StringUtils.isEmpty(processId)) {
            leaveService.updateProcessId(id, processId);
            return new Result<>(0);
        }
        return new Result<Object>(-1, "失败");
    }

    @RequestMapping("/getLeaveMsg")
    public ModelAndView getLeave(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        User user = userService.queryById(username);
        String role = user.getRole();
        Task task = activitiLeaveService.queryAndClaim(role);
        ModelAndView mv = new ModelAndView("/task");
        if (!StringUtils.isEmpty(task.getProcessDefinitionId())) {
            Leave leave = leaveService.queryByProcessId(task.getProcessDefinitionId());
            mv.addObject("leave", leave);
            mv.addObject("taskId", task.getId());
        }
        return mv;
    }

    @RequestMapping("/oper")
    public Result doOper(HttpServletRequest request, String taskId, String leaveId, boolean result) {
        activitiLeaveService.complete(taskId,result);
        return new Result(0);
    }
}
