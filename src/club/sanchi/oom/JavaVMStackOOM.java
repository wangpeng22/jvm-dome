package club.sanchi.oom;

/**
 * 虚拟机栈 和 本地方法栈 溢出 (不断创建线程)
 *
 * 如果虚拟机栈在扩展栈时无法申请到足够的内存，将抛出 OutOfMemoryError
 *
 * -Xss参数 设置本地方法栈大小
 *
 * vm options: -Xss2M
 * Created by wangpeng on 2019/4/3 20:34
 */
public class JavaVMStackOOM {

    private void dontStop(){
        while (true){
        }
    }

    public void stackLeakByThread(){
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
/**
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 */