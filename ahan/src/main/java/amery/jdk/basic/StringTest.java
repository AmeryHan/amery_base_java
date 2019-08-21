package amery.jdk.basic;

/**
 * Created by ahan on 23/05/2017.
 */
public class StringTest {

    public static void main(String[] args) {

        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");

        System.out.println(str5.equals(str3));
        System.out.println(str5 == str3);
        System.out.println(str5.intern() == str3);
        System.out.println(str5.intern() == str4);

    }
}
