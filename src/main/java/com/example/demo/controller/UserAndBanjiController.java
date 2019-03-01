package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.UserAndBanji;
import com.example.demo.service.UserAndBanjiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="UserAndBanjiController相关api",description="用户（老师或者学生）关联班级接口详细描述信息")
@RestController
@RequestMapping("userAndBanji")
public class UserAndBanjiController implements BaseController<UserAndBanji>{

	@Autowired
    private UserAndBanjiService userAndBanjiService;

	@ApiOperation(value = "查询用户（老师或者学生）关联班级的信息",notes = "在searchParam中添加用户（老师或者学生）关联班级的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<UserAndBanji> param){
        return userAndBanjiService.searchWithPage(param);
    }

    @ApiOperation(value = "添加用户（老师或者学生）关联班级信息",notes = "需要在addParam中添加完整用户（老师或者学生）关联班级信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<UserAndBanji> param) {
		return userAndBanjiService.addOne((UserAndBanji)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取用户（老师或者学生）关联班级",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<UserAndBanji> param) {
		return userAndBanjiService.getOne((UserAndBanji)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除用户（老师或者学生）关联班级",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<UserAndBanji> param) {
		return userAndBanjiService.delete((UserAndBanji)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改用户（老师或者学生）关联班级",notes = "  用户（老师或者学生）关联班级不支持update接口 ")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<UserAndBanji> param) {
		return userAndBanjiService.update((UserAndBanji)param.getUpdateParam());
	}
	
}

