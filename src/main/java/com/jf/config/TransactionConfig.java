package com.jf.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class TransactionConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.jf.service.*.*(..))";

    @Bean
    public TransactionInterceptor txAdvice(TransactionManager transactionManager) {
        RuleBasedTransactionAttribute txAttr_REQUIRED = new RuleBasedTransactionAttribute();
        txAttr_REQUIRED.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED.setTimeout(60);

        RuleBasedTransactionAttribute txAttr_REQUIRED_READONLY = new RuleBasedTransactionAttribute();
        txAttr_REQUIRED_READONLY.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);
        txAttr_REQUIRED_READONLY.setTimeout(60);

        Map<String, TransactionAttribute> txMap = new HashMap<>();
        // 增删改方法
        txMap.put("add*", txAttr_REQUIRED);
        txMap.put("save*", txAttr_REQUIRED);
        txMap.put("insert*", txAttr_REQUIRED);
        txMap.put("update*", txAttr_REQUIRED);
        txMap.put("delete*", txAttr_REQUIRED);
        txMap.put("remove*", txAttr_REQUIRED);
        // 查询方法
        txMap.put("get*", txAttr_REQUIRED_READONLY);
        txMap.put("query*", txAttr_REQUIRED_READONLY);
        txMap.put("find*", txAttr_REQUIRED_READONLY);
        txMap.put("select*", txAttr_REQUIRED_READONLY);
        txMap.put("list*", txAttr_REQUIRED_READONLY);
        txMap.put("count*", txAttr_REQUIRED_READONLY);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.setNameMap(txMap);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
} 