package amery.interview;

/**
 * @author ameryhan
 * @date 2019/8/27 16:26
 */
public class PrintFib {

    public static void main(String[] args) {
        method1();
        System.out.println();
        method2();
        System.out.println();
        for (int i = 1; i <= 10; i++) {
            //调用函数进行打印
            System.out.print(fib(i) + "\t");
        }

    }

    private static void method1() {
        //定义第一个加数a，初始值为1；定义第二个加数b，初始值为1；定义两个加数之和为c，初始值为0
        int a = 1;
        int b = 1;
        int c = 0;
        //首先在控制台打印出数列中第一个数和第二个数的值
        System.out.print(a + "\t" + b + "\t");
        //建立一个for循环，用于循环输出数列中第三位至第十位的数字
        for (int i = 3; i <= 10; i++) {
            //第三个数即为c，a+b等于c的值
            c = a + b;
            //将第一个加数a赋值为数列中的第二个数b的值
            a = b;
            //将第二个加数b赋值为数列中的第三个数c的值
            b = c;
            //在第二次循环打印时，将打印数列中的第四个数为：b + c = b + (a + b)
            System.out.print(c + "\t");
        }
    }

    private static void method2() {
        int a = 1;
        int b = 1;
        for (int i = 1; i <= 5; i++) {
            //循环打印a,b两个数，即两个两个打印
            System.out.print(a + "\t" + b + "\t");
            //打印第三、四个数
            a = a + b;
            b = a + b;
        }
    }

    public static int fib(int num) {
        //判断：是否是第一个数和第二个数
        if (num == 1 || num == 2) {
            return 1;
        } else {
            //循环调用本函数
            return fib(num - 2) + fib(num - 1);
        }
    }
}
