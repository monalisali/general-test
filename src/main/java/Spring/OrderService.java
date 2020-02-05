package Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service 加了@Service，main函数也没有能里自动构建出对象，因为main函数不是一个Spring容器
public class OrderService {
    //@Autowired 告诉Spring, 这个OrderDao对象是要自动装配的
    @Autowired
    private OrderDao orderDao;

    public void doSomething(){
        orderDao.select();
        System.out.println("user service do something");
    }
}
