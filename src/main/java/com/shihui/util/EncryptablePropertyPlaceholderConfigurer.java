package com.shihui.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import sun.security.krb5.internal.crypto.Des;

import java.util.Properties;

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
    private static final String key = "0002000200020002";

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        Des des = new Des();
        String username = props.getProperty("jdbc.username");
    }
}
