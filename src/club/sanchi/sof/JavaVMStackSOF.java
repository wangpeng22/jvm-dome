package club.sanchi.sof;

/**
 *
 * 虚拟机栈 和 本地方法栈 溢出 （没有结束的递归调用）
 *
 * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出 StackOverflowError
 *
 * -Xss参数 设置本地方法栈大小
 *
 * vm options: -Xss128k
 * Created by wangpeng on 2019/4/3 17:50
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

   public void stackLeak() {
        stackLength++;
        stackLeak();
   }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + sof.stackLength);
            throw e;
        }
    }
}

/**
 * Exception in thread "main" java.lang.StackOverflowError
 * ...
 */
