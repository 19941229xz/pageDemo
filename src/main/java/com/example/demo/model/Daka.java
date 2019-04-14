package com.example.demo.model;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Daka {

	@ApiModelProperty(value = "打卡人姓名", name = "name")
	private String name; // 打卡人姓名

	@ApiModelProperty(value = "打卡时间", name = "time")
	@NotEmpty(message = "班级名称@Name不能为空")
	private String time; // 打卡时间

}
