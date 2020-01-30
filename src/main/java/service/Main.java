package service;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatcher;

import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static List<String> methodsWithLog = Stream.of(MyService.class.getMethods())
            .filter(FilterMethodAnnotateWithLogMatcher::isAnnotatedWithLog)
            .map(Method::getName)
            .collect(Collectors.toList());

    public static void main(String[] args) {
        MyService service = null;
        try {
            service = enhanceByAnnotation();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        service.queryDatabase(1);
        service.provideHttpResponse("abc");

    }

    private static MyService enhanceByAnnotation() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return  new ByteBuddy()
                .subclass(MyService.class) //动态生成MyService的子类
                .method(new FilterMethodAnnotateWithLogMatcher()) //要拦截哪些方法，method()接收一个ElementMatcher接口参数，所以我们要实现这个接口
                //.method(m->methodsWithLog.contains(m.getName())) //不实现FilterMethodAnnotateWithLogMatcher，使用lambda
                .intercept(MethodDelegation.to(LoggerInterceptor.class))//获取都需要拦截的方法后，把它们委托给LoggerInterceptor
                .make()
                .load(Main.class.getClassLoader())
                .getLoaded()
                .getConstructor()
                .newInstance();


    }

    //实现一个只识别@Log方法的匹配器
    //ElementMatcher接口中只有一个抽象方法matches，而且只剩一句代码了，所以根本不用实现FilterMethodAnnotateWithLogMatcher，直接使用lambda就可以了。保留这个类是为了说明问题时清楚点
    static class FilterMethodAnnotateWithLogMatcher implements ElementMatcher<MethodDescription>{
        //根据传入target方法，判断这个方法是不是应该被拦截和处理
        @Override
        public boolean matches(MethodDescription target) {
            //应该变成全局的
//            List<String> methodsWithLog = Stream.of(MyService.class.getMethods())
//                    .filter(m -> isAnnotatedWithLog(m))
//                    .map(Method::getName)
//                    .collect(Collectors.toList());

            return methodsWithLog.contains(target.getName());
        }

        //判断Method是否有@Log
        private static boolean isAnnotatedWithLog(Method method){
            //1. getAnnotation()要求传入的Class是继承自Annotation,但Log在创建时并没有写extends Annotation, 那为什么Log.class可以传入进去呢？
            //原因是Java中所有的注解都是默认继承自Annotation的
            //2. @Log创建的地方一定要写：@Retention(RetentionPolicy.RUNTIME)，不然getAnnotation()是拿不到带@Log的方法的
            //因为isAnnotatedWithLog()是在运行时动态获取方法上的@Log, 而@Log默认的Retation是CLASS，CLASS表示注解在运行时是会被JVM忽略的。而RUNTIME在运行时就会被保留
           return method.getAnnotation(Log.class) != null;
        }

    }

    public static class LoggerInterceptor {
        public static void log(@SuperCall Callable<List<String>> zuper)
                throws Exception {
            System.out.println("*********START***********");
            try {
                zuper.call();
            } finally {
                System.out.println("***********END************");
            }
        }
    }


}
