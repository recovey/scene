package com.example.scene01.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomUtils {

    // Utility 工具

    // CTRL + SHIFT + U 批量转换大小写
    // static 修饰的内容，是不依赖于这个类的任何实例的，当这个类一旦在运行期间加载进来，它就已经存在，有且只有一份，大家共享的
    // static修饰的方法只能访问static修饰的变量
    private final static String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // 字典数据
    private static final Random ran = new Random(); // 对于引用类型，只要它自己的地址不发生改变，也可以修饰为final的

    // private static final String[] genders = new String[]{"male, female"};
    private static final String[] genders = {"male", "female"}; // 利用了编译器的优化机制(语法糖)进行简化，和上面这一行等效

    public static void longSleep(int min, int max) {
        int time = randomNumber(min, max);
        try {
            // Thread.sleep(time);
            TimeUnit.MILLISECONDS.sleep(time); // 代码易读，语义更清晰
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void longSleep(int sec) {
        try {
            // Thread.sleep(time);
            TimeUnit.MILLISECONDS.sleep(sec); // 代码易读，语义更清晰
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>生成步骤</p>
     * <ul>
     *     <li>对字典字符串拆分，得到一个字符数组</li>
     *     <li>产生某一个合适范围的索引值</li>
     *     <li>把字符数组变换成一个完整的字符串</li>
     * </ul>
     *
     * @param len 表示要生成的字符串长度
     * @return 返回一个随机内容的字符串
     */
    public static String randomString(int len) {
        // String[] split = data.split("");
        char[] chars = data.toCharArray(); // 'A'
        // 循环从split数组中随机获取一个字符，然后组装成一个完整字符串
        // 创建一个临时存放零散字符的数组
        char[] temp = new char[len]; // 用来构造一个完整的字符串的
        for (int j = 0; j < len; j++) {
            char c = chars[ran.nextInt(data.length())]; // [0, data.length())
            temp[j] = c;
        }
        return new String(temp); // 不使用字符串的拼串是因为会产生大量的内存零时垃圾
    }

    public static String randomString() {
        return randomString(5); // 重用上面的逻辑
    }

    /**
     * 产生从from到to之间的某一个数字，包左，不包右
     *
     * @param min 最小值
     * @param max 最大值
     */
    public static int randomNumber(int min, int max) { // 3 - 10
        if (max < min) {
            System.out.printf("%d 不能 比 %d 小\r\n", max, min);
        }
        return ran.nextInt(max - min) + min;
    }

    /**
     * 产生从 1 到to之间的某一个数字，包左，不包右
     *
     * @param max 最大值
     */
    public static int randomNumber(int max) {
        return randomNumber(1, max);
    }

    public static String randomGender() {
        // return ran.nextBoolean() ? "male" : "female";
        int index = ran.nextInt(2); // 0 1
        return genders[index];
        // return genders[ran.nextInt(2)];
    }
}
