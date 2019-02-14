package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.GoodOrder;
import com.example.demo.service.GoodOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="GoodOrderController相关api",description="订单接口详细描述信息")
@RestController
@RequestMapping("goodOrder")
public class GoodOrderController implements BaseController<GoodOrder>{

	@Autowired
    private GoodOrderService goodOrderService;

	@ApiOperation(value = "查询订单的信息",notes = "在searchParam中添加订单的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<GoodOrder> param){
        return goodOrderService.searchWithPage(param);
    }

    @ApiOperation(value = "添加订单信息",notes = "需要在addParam中添加完整订单信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<GoodOrder> param) {
		return goodOrderService.addOne((GoodOrder)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取订单",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<GoodOrder> param) {
		return goodOrderService.getOne((GoodOrder)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除订单",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<GoodOrder> param) {
		return goodOrderService.delete((GoodOrder)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改订单",notes = "只需要在updateParam中添加")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<GoodOrder> param) {
		return goodOrderService.update((GoodOrder)param.getUpdateParam());
	}
	
}

