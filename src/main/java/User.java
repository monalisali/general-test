import service.MySpringBootApplication;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class User {
    private Integer id;
    private String userName;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                userName.equals(user.userName) &&
                title.equals(user.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, title);
    }

    public User()
    {

    }

    public User(Integer ID, String userName,String title) {
        this.id = ID;
        this.userName = userName;
        this.title = title;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static Object Create(Supplier<Object> obj)
    {
        return obj.get();
    }


    public static String map(User user, Function<User, String> function) {
        return function.apply(user);
    }

    public static void main(String[] args) {

        Create(new Supplier<Object>() {
            @Override
            public User get() {
                return new User(2,"Jack","FootballPlayer");
            }
        });


        Create(()->new User(2,"Jack","FootballPlayer"));
        Create(() -> new Object());
        Create(Object::new);
        Create(User::new);

        String result = map(new User(1, "Tom","Worker"), User::getTitle);

        System.out.println(result);
    }
}
