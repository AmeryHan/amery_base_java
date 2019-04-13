package amery.interview;

import java.util.*;

public class RpnCalculator {

    static Stack<String> eval = new Stack<>();

    public static void main(String[] args) {

        Queue<String> infixQueue = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.println(""); // 让用户换一行输入
            input = sc.nextLine();
            if (input.equals("quit")) {
                System.exit(0);
            }
            String[] value = input.split(" "); //切分字符串
            for (String var : value) {
                infixQueue.add(var);
            }
            postfixEvaluation(infixQueue);
        } while (!input.equals("quit"));
    }

    public static void postfixEvaluation(Queue<String> postQueue) {
        String t;
        Double headNumber, nextNumber = 0.0;
        Double result = 0.0;
        while (!postQueue.isEmpty() && postQueue.size() >= 1 ) {
            t = postQueue.poll();
            boolean addToStack = true;
            try {
                Double.parseDouble(t);
                eval.add(t);
            } catch (NumberFormatException nfe) {
                headNumber = Double.parseDouble(eval.peek());
                eval.pop();
                try {
                    nextNumber = Double.parseDouble(eval.peek());
                    eval.pop();
                } catch (EmptyStackException e) {
                    //e.printStackTrace();
                    //没有那么的字符串 特意什么也不做
                }
                switch (t) {
                    case "+":
                        result = nextNumber + headNumber;
                        break;
                    case "-":
                        result = nextNumber - headNumber;
                        break;
                    case "*":
                        result = nextNumber * headNumber;
                        break;
                    case "/":
                        if (headNumber == 0) {
                            System.out.println("\nERROR: Cannot Divide by zero!\n");
                            return;
                        } else {
                            result = nextNumber / headNumber;
                            break;
                        }
                    case "%":
                        result = nextNumber % headNumber;
                        break;
                    case "^":
                        result = Math.pow(nextNumber, headNumber);
                        break;
                    case "undo":
                        if (!eval.isEmpty()) {
                            eval.pop();
                        }
                        break;
                    case "sqrt":
                        result = Math.sqrt(headNumber);
                        break;
                    case "clear":
                        while (!eval.isEmpty()) {
                            eval.pop();
                        }
                        addToStack = false; //avoid add 0 to stack
                        break;
                }
                if (addToStack) {
                    eval.push(result.toString());
                }
            }

        }

        System.out.print("stack: ");
        System.out.print(eval);
    }

}
