package Spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        //BeanFactory就是Spring容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:config.xml");
        OrderDao orderDao = (OrderDao)beanFactory.getBean("orderDao");
        OrderService orderService = (OrderService)beanFactory.getBean("orderService");
        orderService.doSomething();
    }
}
