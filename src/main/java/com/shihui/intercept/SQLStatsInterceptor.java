package com.shihui.intercept;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

@Intercepts(
        @Signature(type = StatementHandler.class, method = "prepare",
                args = {Connection.class, Integer.class})
)
public class SQLStatsInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler sth = (StatementHandler) invocation.getTarget();
        String sql = sth.getBoundSql().getSql();
        Object[] args = invocation.getArgs();
        logger.info("mybatis intercept sql:{},args:{}", sql, args);
        return invocation.proceed();
    }

    /**
     * 此plugin只拦截StatementHandler
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler)
            return Plugin.wrap(target, this);
        else
            return target;
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        logger.info("mybatis intercept dialect:{}", dialect);
    }
}
