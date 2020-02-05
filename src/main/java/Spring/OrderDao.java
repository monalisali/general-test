package Spring;

import org.springframework.stereotype.Service;

//@Service，加了@Service，main函数也没有能里自动构建出对象，因为main函数不是一个Spring容器
public class OrderDao {
    public void select(){
        System.out.println("select!");
    }
}
