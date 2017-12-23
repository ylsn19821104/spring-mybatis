package com.shihui.dao;

import com.shihui.model.Payment;
import com.shihui.provider.PaymentSqlProvider;
import org.apache.ibatis.annotations.*;

public interface PaymentMapper {

    @Insert("insert into payment(trans_id,payment_type,price,user_id) " +
            "values(#{transId},#{paymentType},#{price},#{userId})")
    int insertPaymentWithTransId(Payment payment);

    @Insert("insert into payment(trans_id,payment_type,price,user_id) " +
            "values(#{transId},#{paymentType},#{price},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "transId")
    int insertPayment(Payment payment);

    @Update("update payment set price= #{price} where trans_id = #{transId}")
    int updatePayment(Payment payment);

    @SelectProvider(type = PaymentSqlProvider.class, method = "queryByTransIdSql")
    @ResultType(Payment.class)
    Payment queryByTransId(@Param("transId") long transId);

    @InsertProvider(type = PaymentSqlProvider.class, method = "insertSql")
    @Options(useGeneratedKeys = true, keyProperty = "transId")
    int insert(Payment payment);
}
