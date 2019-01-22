package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("MenuController相关api")
@RestController
@RequestMapping("menu")
public class MenuController implements BaseController<Menu>{

	@Autowired
    private MenuService menuService;

	@ApiOperation(value = "查询菜单的信息",notes = "在searchParam中添加菜单的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<Menu> param){
        return menuService.searchWithPage(param);
    }

    @ApiOperation(value = "添加菜单信息",notes = "需要在addParam中添加完整菜单信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<Menu> param) {
		return menuService.addOne((Menu)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取菜单",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<Menu> param) {
		return menuService.getOne((Menu)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除菜单",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<Menu> param) {
		return menuService.delete((Menu)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改菜单",notes = "只需要在updateParam中添加")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<Menu> param) {
		return menuService.update((Menu)param.getUpdateParam());
	}
	
}

