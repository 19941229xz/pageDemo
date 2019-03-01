package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.Banji;
import com.example.demo.service.BanjiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="BanjiController相关api",description="班级接口详细描述信息")
@RestController
@RequestMapping("banji")
public class BanjiController implements BaseController<Banji>{

	@Autowired
    private BanjiService banjiService;

	@ApiOperation(value = "查询班级的信息",notes = "在searchParam中添加班级的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<Banji> param){
        return banjiService.searchWithPage(param);
    }

    @ApiOperation(value = "添加班级信息",notes = "需要在addParam中添加完整班级信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<Banji> param) {
		return banjiService.addOne((Banji)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取班级",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<Banji> param) {
		return banjiService.getOne((Banji)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除班级",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<Banji> param) {
		return banjiService.delete((Banji)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改班级",notes = "只需要在updateParam中添加 ")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<Banji> param) {
		return banjiService.update((Banji)param.getUpdateParam());
	}
	
}

