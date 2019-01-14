package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("RoleController相关api")
@RestController
@RequestMapping("role")
public class RoleController implements BaseController<Role>{

	@Autowired
    private RoleService roleService;

	@ApiOperation(value = "查询角色的信息",notes = "")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<Role> param){
        return roleService.searchWithPage(param);
    }

    @ApiOperation(value = "添加角色信息",notes = "需要在addParam中添加完整角色信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<Role> param) {
		return roleService.addOne((Role)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取角色",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<Role> param) {
		return roleService.getOne((Role)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除角色",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<Role> param) {
		return roleService.delete((Role)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改角色",notes = "只需要在updateParam中添加")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<Role> param) {
		return roleService.update((Role)param.getUpdateParam());
	}
	
}

