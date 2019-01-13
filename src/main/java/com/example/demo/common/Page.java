package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="page",description="分页参数")
@Data
public class Page {

	@ApiModelProperty(value="当前页",name="从0开始")
	private int page;
	
	@ApiModelProperty(value="每页数据量",name="")
	private int size;
	
}
