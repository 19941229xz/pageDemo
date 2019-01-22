package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.Email;
import com.example.demo.service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("EmailController相关api")
@RestController
@RequestMapping("email")
public class EmailController implements BaseController<Email>{

	@Autowired
    private EmailService emailService;

	@ApiOperation(value = "查询邮件的信息",notes = "在searchParam中添加邮件的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<Email> param){
        return emailService.searchWithPage(param);
    }

    @ApiOperation(value = "添加邮件信息",notes = "需要在addParam中添加完整邮件信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<Email> param) {
		return emailService.addOne((Email)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取邮件",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<Email> param) {
		return emailService.getOne((Email)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除邮件",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<Email> param) {
		return emailService.delete((Email)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改邮件",notes = "只需要在updateParam中添加")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<Email> param) {
		return emailService.update((Email)param.getUpdateParam());
	}
	
}

