package com.ltsh.chat.web.controller.basics;

import com.ltsh.chat.service.api.BasicsService;

import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.service.resp.basics.RandomResp;
import com.ltsh.chat.web.common.controller.BaseController;
import com.ltsh.common.entity.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fengjianbo on 2018/1/30.
 */
@Controller
@RequestMapping("/chat/basics")
public class BasicsController extends BaseController {
    @Autowired
    private BasicsService basicsService;
    @ResponseBody
    @RequestMapping("/getRandomStr")
    public Result<RandomResp> getRandomStr(@RequestBody RequestContext<String> req){
        return basicsService.getRandomStr(req);
    }

}
