package amery.interview;

import java.util.*;

public class Rpncalculator2 {

    static final HashMap<String, Integer> prec;

    static Stack<String> eval = new Stack<>();

    static {
        prec = new HashMap<>();
        prec.put("^", 3);
        prec.put("%", 2);
        prec.put("*", 2);
        prec.put("/", 2);
        prec.put("+", 1);
        prec.put("-", 1);

    }

    public static void main(String[] args) {

        Queue<String> infixQueue = new LinkedList<>(); //Standard Queue class provided by Java Framework.
        Scanner sc = new Scanner(System.in);
        Double number = 0.0;
        Character c, cNext = ' ';
        String input;
        String multiDigit = "";
        do {
            System.out.println("");
            input = sc.nextLine();
            //input = input.replaceAll(" ", ""); //ignore spaces in input infix expression
            if (input.equals("quit")) {
                System.exit(0);
            }

            String[] value = input.split(" ");
            for (String var : value) {
                infixQueue.add(var);
            }
            //infixQueue.add(c.toString());

//            for (int i = 0; i < input.length(); i++) {
//                c = input.charAt(i);
//                if (c.toString().trim().isEmpty()) {
//                    continue;
//                }
//                if (i + 1 < input.length()) {
//                    cNext = input.charAt(i + 1);
//                }
//
//                if (c.equals('(') || c.equals(')')) {
//                    if (c.equals('(') && cNext.equals('-')) {
//                        System.out.println("NEGATIVE Numbers not allowed");
//                        main(args);
//                    } else {
//                        infixQueue.add(c.toString());
//                    }
//                } else if (!Character.isDigit(c)) {
//                    if (infixQueue.isEmpty() && c.equals('-')) {
//                        System.out.println("NEGATIVE Numbers not allowed");
//                        main(args);
//                    } else if (cNext.equals('-')) {
//                        System.out.println("NEGATIVE Numbers not allowed");
//                        main(args);
//                    } else {
//                        infixQueue.add(c.toString());
//                    }
//                } else if (Character.isDigit(c)) {
//                    if (i + 1 < input.length() && input.charAt(i + 1) == '.') //to handle decimal
//                    {
//                        int j = i + 1;
//                        multiDigit = c.toString() + input.charAt(j); //to handle multidigit
//                        while (j + 1 <= input.length() - 1 && Character.isDigit(input.charAt(j + 1))) {
//                            multiDigit = multiDigit + input.charAt(j + 1);
//                            j++;
//                        }
//                        i = j;
//                        infixQueue.add(multiDigit);
//                        multiDigit = "";
//                    } else if (i + 1 <= input.length() - 1 && Character.isDigit(input.charAt(i + 1))) {
//                        int j = i;
//                        //multiDigit=c.toString()+input.charAt(i);
//                        while (j <= input.length() - 1 && Character.isDigit(input.charAt(j))) {
//                            multiDigit = multiDigit + input.charAt(j);
//                            j++;
//                        }
//                        i = j - 1;
//                        infixQueue.add(multiDigit);
//                        multiDigit = "";
//                    } else {
//                        infixQueue.add(c.toString());
//                    }
//
//                }
//            }

            postfixEvaluation(infixQueue);
        } while (!input.equals("quit"));
    }

    //method to calculate the reuslt of postfix expression.
    public static void postfixEvaluation(Queue<String> postQueue) {
        //Stack<String> eval = new Stack<>(); //Standard Stack class provided by Java Framework.
        String t;
        Double headNumber, nextNumber = 0.0, result = 0.0;
        while (!postQueue.isEmpty() && postQueue.size() >= 1 ) {
            t = postQueue.poll();
            boolean addToStack = true;
            try {
                double num = Double.parseDouble(t);
                eval.add(t);
            } catch (NumberFormatException nfe) {
                headNumber = Double.parseDouble(eval.peek());
                eval.pop();
                try {
                    nextNumber = Double.parseDouble(eval.peek());
                    eval.pop();
                } catch (EmptyStackException e) {
                    //e.printStackTrace();
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
                        //in java, there is no exception generated when divided by zero and thus checking
                        //for 
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
                    case "sqrt":
                        result = Math.sqrt(headNumber);
                        break;
                    case "clear":
                        while (!eval.isEmpty()) {
                            eval.pop();
                        }
                        addToStack = false;
                        break;
                }
                if (addToStack) {
                    eval.push(result.toString());
                }
            }

        }

        System.out.print("stack: ");
        System.out.print(eval);
        //DecimalFormat df = new DecimalFormat("0.000");
    }

}
