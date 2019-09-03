package amery.interview;

/**
 * @author ameryhan
 * @date 2019/9/3 9:34
 */
public class PermutationTest {
    static StringBuilder out = new StringBuilder();
    static String in = "xyz";
    static boolean[] used = new boolean[in.length()];

    public static void main(String[] args) {
        permutationString();
    }

    public static void permutationString() {
        if (out.length() == in.length()) {
            System.out.println(out.toString());
            return;
        }
        for (int i = 0; i < in.length(); i++) {
            if (used[i]) continue;
            out.append(in.charAt(i));
            used[i] = true;
            permutationString();
            used[i] = false;
            out.setLength(out.length() - 1);
        }
    }
}
