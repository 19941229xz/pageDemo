package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.WxAccount;
import com.example.demo.service.WxAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="WxAccountController相关api",description="微信账号接口详细描述信息")
@RestController
@RequestMapping("wxAccount")
public class WxAccountController implements BaseController<WxAccount>{

	@Autowired
    private WxAccountService wxAccountService;

	@ApiOperation(value = "查询微信账号的信息",notes = "在searchParam中添加微信账号的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<WxAccount> param){
        return wxAccountService.searchWithPage(param);
    }

    @ApiOperation(value = "添加微信账号信息",notes = "需要在addParam中添加完整微信账号信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<WxAccount> param) {
		return wxAccountService.addOne((WxAccount)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取微信账号",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<WxAccount> param) {
		return wxAccountService.getOne((WxAccount)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除微信账号",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<WxAccount> param) {
		return wxAccountService.delete((WxAccount)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改微信账号",notes = "只需要在updateParam中添加")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<WxAccount> param) {
		return wxAccountService.update((WxAccount)param.getUpdateParam());
	}
	
}

