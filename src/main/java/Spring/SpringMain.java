package Spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

public class SpringMain {
//    @Autowired
//    static OrderService orderService;
    public static void main(String[] args) {
        //BeanFactory就是Spring容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:config.xml");
        OrderDao orderDao = (OrderDao)beanFactory.getBean("orderDao");
        OrderService orderService = (OrderService)beanFactory.getBean("orderService");
        orderService.doSomething();

        //重要！！！
        //只有Spring容器可以利用@Service把Java类变成Java Bean
        //而main函数不是Spring容器，所以即使在OrderService中使用了@Service，然后在main函数中把它注入进来，main函数是没有能力
        //帮你创建UserService对象的。
        //换句话说，在main函数中就用不了@Service的特性，必须得用上面的代码来自己实例化对象
        //orderService.doSomething();

    }
}
