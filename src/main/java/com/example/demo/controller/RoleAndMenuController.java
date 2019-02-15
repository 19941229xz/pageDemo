package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.RoleAndMenu;
import com.example.demo.service.RoleAndMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="RoleAndMenuController相关api",description="角色对应的权限接口详细描述信息")
@RestController
@RequestMapping("roleAndMenu")
public class RoleAndMenuController implements BaseController<RoleAndMenu>{

	@Autowired
    private RoleAndMenuService roleAndMenuService;

	@ApiOperation(value = "查询角色对应的权限的信息",notes = "在searchParam中添加角色对应的权限的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<RoleAndMenu> param){
        return roleAndMenuService.searchWithPage(param);
    }

    @ApiOperation(value = "添加角色对应的权限信息",notes = "需要在addParam中添加完整角色对应的权限信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<RoleAndMenu> param) {
		return roleAndMenuService.addOne((RoleAndMenu)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取角色对应的权限",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<RoleAndMenu> param) {
		return roleAndMenuService.getOne((RoleAndMenu)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除角色对应的权限",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<RoleAndMenu> param) {
		return roleAndMenuService.delete((RoleAndMenu)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改角色对应的权限",notes = "只需要在updateParam中添加")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<RoleAndMenu> param) {
		return roleAndMenuService.update((RoleAndMenu)param.getUpdateParam());
	}
	
}

