package com.tech.oa.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chengli on 2016/11/17.
 */
@Controller
public class SysManagerController {

    @RequestMapping({"/jst/{sys}/{module}/{jspFile}"})
    public String jspUrlRewrite(@PathVariable String sys, @PathVariable String module, @PathVariable String jspFile) {
        return "jst/" + sys + "/" + module + "/" + jspFile;
    }
}
