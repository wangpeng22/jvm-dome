package club.sanchi.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 堆溢出 （只要不断的创造对象，并且保证GC Roots 到对象之间有可达路径来避免垃圾回收清除这些对象）
 *
 * 在对象数量达到最大堆的容量后就会产生内存溢出异常
 *
 * -Xms参数 设置堆的最小值
 * -Xmx参数 设置堆的最大值
 * -XX:+HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照，以便事后进行分析
 *
 * vm options: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by wangpeng on 2019/4/3 17:19
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}

/**
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to java_pid15488.hprof ...
 * ...
 * Heap dump file created [26738156 bytes in 0.425 secs]
 */