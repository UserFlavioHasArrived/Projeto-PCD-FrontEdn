package com.example.demo.DataInitializer; //TROCAR O NOME DO PACOTE

import java.util.Map;
import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Configuration
public class DataInitializer {
    
    @Autowired
    private ApplicationContext applicationContext;

    private void processField(Object bean, Field field, DataInitializerType annotation) {
        try {
            field.setAccessible(true);
            if (field.getType().isArray() && field.getType().getComponentType() == String.class) {
                String[] listagem = (String[]) field.get(bean);

                if (listagem != null) {        
                    Object repository = applicationContext.getBean(annotation.repository());
                    Class<?> modelClass = annotation.model();

                    Method countMethod = repository.getClass().getMethod("count");
                    long count = (long) countMethod.invoke(repository);

                    if(count == 0){
                        for(String item : listagem){
                            Object entity = modelClass.getDeclaredConstructor(String.class).newInstance(item);
    
                            Method saveMethod = repository.getClass().getMethod("save", Object.class);
                            saveMethod.invoke(repository, entity);
                        }     
                    }                       
                }
            }
        } catch (IllegalAccessException | InstantiationException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Component.class);
            for (Object bean : beans.values()) {
                Class<?> beanClass = bean.getClass();
                for (Field field : beanClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(DataInitializerType.class)) {
                        DataInitializerType annotation = field.getAnnotation(DataInitializerType.class);
                        processField(bean, field, annotation);
                    }
                }
            }
        };
    }

}
