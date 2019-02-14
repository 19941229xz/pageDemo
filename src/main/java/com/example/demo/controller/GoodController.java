package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.Good;
import com.example.demo.service.GoodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="GoodController相关api",description="商品接口详细描述信息")
@RestController
@RequestMapping("good")
public class GoodController implements BaseController<Good>{

	@Autowired
    private GoodService goodService;

	@ApiOperation(value = "查询商品的信息",notes = "在searchParam中添加商品的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<Good> param){
        return goodService.searchWithPage(param);
    }

    @ApiOperation(value = "添加商品信息",notes = "需要在addParam中添加完整商品信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<Good> param) {
		return goodService.addOne((Good)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取商品",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<Good> param) {
		return goodService.getOne((Good)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除商品",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<Good> param) {
		return goodService.delete((Good)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改商品",notes = "只需要在updateParam中添加")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<Good> param) {
		return goodService.update((Good)param.getUpdateParam());
	}
	
}

