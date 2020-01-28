import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamTest {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User(5,"张1","AA"),new User(2, "张2","BB")
        , new User(3,"李四","CC"));

        List<User> zhangs = new ArrayList<>();
        for (User u:users) {
            if(u.getUserName().startsWith("张")){
                zhangs.add(u);
            }
        }

        Collections.sort(zhangs, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getID() - o2.getID() > 0){
                    return  1;
                }else if(o1.getID() - o2.getID() < 0){
                    return -1;
                }else {
                    return 0;
                }
            }
        });

        List<String> sortedUsers = new ArrayList<>();
        for(User u : zhangs){
            sortedUsers.add(u.getUserName());
        }

        System.out.println("********************没有用Stream*******************");
        for(String u : sortedUsers){
            System.out.println("UserName: " + u);
        }


        System.out.println("******************用了Stream************************");
        List<String> useStreams =  users.stream().filter(user->user.getUserName().startsWith("张"))
                .sorted(comparing(User::getID))
                .map(User::getUserName)
                .collect(toList());

        for(String u : useStreams){
            System.out.println("UserName：" + u);
        }

        System.out.println("******************IntStream.rang************************");
        printOddNumberBetween(1,10);

        System.out.println("******************String.chars************************");
        System.out.println("有大写字母" + countUpperCaseChars("asfsdDDFddDDWweSdDF") + "个");

        System.out.println("******************findAny************************");
        Optional<User> optionalUser = users.stream().filter(u->u.getUserName().startsWith("张")).findAny();
        //Optional<T>的推荐用法
        optionalUser.orElseThrow(IllegalStateException::new);
        optionalUser.ifPresent(u-> System.out.println(u.getUserName()));

        //Optional<T>的推荐用法，可以级联
        User u1 = users.stream().filter(u->u.getUserName().startsWith("张")).findAny().orElseThrow(IllegalAccessError::new);
        System.out.println(u1.getUserName());

        //Option<T>不推荐用法
        if(optionalUser.isPresent()){
            System.out.printf(optionalUser.get().getUserName());
        }else {
            throw new IllegalStateException();
        }
    }

    private static void printOddNumberBetween(int start,int end)
    {
        IntStream.range(start,end + 1).filter(i->i%2!=0).forEach(i-> System.out.println(i));
    }

    private static int countUpperCaseChars(String str)
    {
        return (int)str.chars().filter(c->Character.isUpperCase(c)).count();
    }

    private static void optionalMethod(Optional<String> str ){
        System.out.println(str);
    }

}
