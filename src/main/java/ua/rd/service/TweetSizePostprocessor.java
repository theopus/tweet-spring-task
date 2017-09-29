package ua.rd.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ua.rd.domain.TweetSize;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by irina on 24.09.17.
 */
@Component
public class TweetSizePostprocessor implements BeanPostProcessor {

    Map<String, Class<?>> classMap = new HashMap<>();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (Arrays.stream(bean.getClass().getInterfaces())
                .anyMatch(aClass -> Arrays.stream(aClass.getMethods())
                        .anyMatch(m-> Arrays.stream(m.getParameters())
                                .anyMatch(p-> p.isAnnotationPresent(TweetSize.class)))))
            classMap.put(beanName, bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = classMap.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                if (Arrays.stream(method.getParameters()).noneMatch(p -> p.isAnnotationPresent(TweetSize.class)))
                    return method.invoke(bean,args);

                Object[] checkedArgs = new Object[args.length];
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    TweetSize an = parameters[i].getAnnotation(TweetSize.class);
                    if (an != null)
                        checkedArgs[i] = checkTweetSize((String) args[i], an.size());
                    else
                        checkedArgs[i] = args[i];
                }

                Object invoke = method.invoke(bean, args);
                return invoke;
            });
        }
        return bean;
    }


    private String checkTweetSize(String txt, int size){
        if (txt.trim().length() > size)
            throw new TweetSizeException("Tweet declined. Lenght of tweet is " + txt.trim().length() + ". (expected not more then " + size + ")");
        else return txt.trim();
    }
}
