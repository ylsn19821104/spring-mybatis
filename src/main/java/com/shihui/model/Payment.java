package com.shihui.model;


import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 *
 */
@Builder
@Data
public class Payment implements Serializable {
    private static final long serialVersionUID = 3600532404093354905L;

    @Tolerate
    public Payment() {
    }

    //@Setter(value = AccessLevel.PROTECTED)
    private long transId;//交易ID
    private Integer paymentType;
    private String paymentNo;//交易号
    private String fromAccount;//打款方账户
    private String toAccount;//收款方账户
    private long price;//金额（毫）
    private String subject;//支付标题
    private String body;//支付描述
    private String ip;//IP
    private Long paymentTime;//支付时间
    private Long createTime;//创建时间
    private String ext;//扩展字段
    private Integer userId;//支付用户ID
    private Long orderId;//订单ID
}
