package club.sanchi.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区溢出 (运行时产生大量的类去填满方法区，直至溢出)
 *
 * 方法区用于存放Class的相关信息，如类名、访问修饰符、常量池、字段描述、方法描述等
 *
 * -XX:PermSize -XX:MaxPermSize 设置方法区大小
 *
 * vm options: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * Created by wangpeng on 2019/4/4 10:14
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            System.out.println(enhancer.create());
        }
    }

    static class OOMObject {
    }
}
