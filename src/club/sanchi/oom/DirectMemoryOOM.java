package club.sanchi.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出
 *
 * 直接内存可以通过 -XX:MaxDirectMemorySize 指定，如果不指定，则默认与Java堆最大值一样
 *
 * vm options: -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * Created by wangpeng on 2019/4/4 11:46
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
/**
 * Exception in thread "main" java.lang.OutOfMemoryError
 * ...
 */