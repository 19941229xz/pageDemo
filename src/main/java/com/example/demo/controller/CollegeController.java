package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.College;
import com.example.demo.service.CollegeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="CollegeController相关api",description="学校接口详细描述信息")
@RestController
@RequestMapping("college")
public class CollegeController implements BaseController<College>{

	@Autowired
    private CollegeService collegeService;

	@ApiOperation(value = "查询学校的信息",notes = "在searchParam中添加学校的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<College> param){
        return collegeService.searchWithPage(param);
    }

    @ApiOperation(value = "添加学校信息",notes = "需要在addParam中添加完整学校信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<College> param) {
		return collegeService.addOne((College)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取学校",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<College> param) {
		return collegeService.getOne((College)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除学校",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<College> param) {
		return collegeService.delete((College)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改学校",notes = "只需要在updateParam中添加 ")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<College> param) {
		return collegeService.update((College)param.getUpdateParam());
	}
	
}

