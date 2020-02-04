package Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    //@Autowired 告诉Spring, 这个OrderDao对象是要自动装配的
    @Autowired
    private OrderDao orderDao;

    public void doSomething(){
        orderDao.select();
        System.out.println("user service do something");
    }
}
