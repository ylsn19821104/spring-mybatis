package com.shihui.provider;

import com.shihui.model.Payment;
import org.apache.ibatis.jdbc.SQL;

public class PaymentSqlProvider {
    public String queryByTransIdSql() {
        return new SQL() {
            {
                SELECT("trans_id,payment_no,from_account,to_account,price,from_account,to_account");
                FROM("payment");
                WHERE("trans_id=#{transId}");
            }
        }.toString();
    }

    public String insertSql(final Payment payment) {
        return new SQL() {
            {
                INSERT_INTO("payment");
                if (payment.getPaymentType() != null)
                    VALUES("payment_type", "#{paymentType}");
                if (payment.getPrice() > 0)
                    VALUES("price", "#{price}");
                if (payment.getFromAccount() != null)
                    VALUES("from_account", "#{fromAccount}");
                if (payment.getUserId() != null)
                    VALUES("user_id", "#{userId}");
            }
        }.toString();
    }
}
