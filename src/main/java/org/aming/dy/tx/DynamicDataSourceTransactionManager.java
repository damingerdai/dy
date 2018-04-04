package org.aming.dy.tx;

import org.aming.dy.core.DynamicDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.sql.DataSource;

/**
 * 动态数据源事务管理
 *
 * @author aming
 * @create 2018-04-03 8:51
 **/
public class DynamicDataSourceTransactionManager extends AbstractPlatformTransactionManager implements PlatformTransactionManager, InitializingBean {

    @Nullable
    private DynamicDataSource dynamicDataSource;

    public void setDynamicDataSource(@Nullable DataSource dataSource) {
        if (dataSource instanceof DynamicDataSource) {
            this.dynamicDataSource = (DynamicDataSource)dataSource;
        } else {

        }
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        return null;
    }

    @Override
    protected void doBegin(Object o, TransactionDefinition transactionDefinition) throws TransactionException {

    }

    @Override
    protected void doCommit(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {

    }

    @Override
    protected void doRollback(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {

    }



    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
