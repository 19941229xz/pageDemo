package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 订单 对应数据表 goodOrder
 */
@ApiModel(value="goodOrder",description="订单")
@Data
public class GoodOrder {
	
        
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
        
    @ApiModelProperty(value="商品名称",name="goodsName")
    @NotEmpty(message="商品名称不能为空")
	    private String goodsName; // 商品名称
    	
        
    @ApiModelProperty(value="删除时间",name="deleteTime",hidden=true)
        private Date deleteTime; // 删除时间
    	
        
    @ApiModelProperty(value="删除人id",name="deleteUserId",hidden=true)
        private String deleteUserId; // 删除人id
    	
        
    @ApiModelProperty(value="最后一次更新时间",name="updateLastTime",hidden=true)
        private Date updateLastTime; // 最后一次更新时间
    	
        
    @ApiModelProperty(value="创建时间",name="createTime",hidden=true)
        private Date createTime; // 创建时间
    	
        
    @ApiModelProperty(value="是否被删除（0表示为删除，1表示已删除）",name="isDeleted",hidden=true)
        private Integer isDeleted; // 是否被删除（0表示为删除，1表示已删除）
    	
        
    @ApiModelProperty(value="交易金额",name="payAmount")
    @NotEmpty(message="交易金额不能为空")
	    private Double payAmount; // 交易金额
    	
        
    @ApiModelProperty(value="收货地址",name="address")
    @NotEmpty(message="收货地址不能为空")
	    private String address; // 收货地址
    	
        
    @ApiModelProperty(value="备注",name="comment")
        private String comment; // 备注
    	
        
    @ApiModelProperty(value="交易数量",name="count")
    @NotEmpty(message="交易数量不能为空")
	    private Integer count; // 交易数量
    	
        
    @ApiModelProperty(value="交易状态",name="status")
    @NotEmpty(message="交易状态不能为空")
	    private String status; // 交易状态
    

}
