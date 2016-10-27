package data.structure;

import java.util.Scanner;
public class Infix2Postfix extends Stack<Character>
{
	public Infix2Postfix() {
        super();
    }
   
    public String InToPost(String infixString) {
        String postfixString = " ";

        for (int index = 0; index < infixString.length(); ++index) {
            char chValue = infixString.charAt(index);
            if (chValue == '(') {
                push('(');
            } else if (chValue == ')') {
                Character oper = peek();
                while (!(oper.equals('(')) && !(isEmpty())) {
                    postfixString += oper.charValue();
                    pop();
                    oper = peek();
                }
                pop();
            } else if (chValue == '+' || chValue == '-') {
                //Stack is empty
                if (isEmpty()) {
                    push(chValue);
                    //current Stack is not empty
                } else {
                    Character oper = peek();
                    while (!(isEmpty() || oper.equals(new Character('(')) || oper.equals(new Character(')')))) {
                        pop();
                        postfixString += oper.charValue();
                    }
                    push(chValue);
                }
            } else if (chValue == '*' || chValue == '/') {
                if (isEmpty()) {
                    push(chValue);
                } else {
                    Character oper = peek();
                    while (!oper.equals(new Character('+')) && !oper.equals(new Character('-')) && !isEmpty()) {
                        pop();
                        postfixString += oper.charValue();
                    }
                    push(chValue);
                }
            } else {
                postfixString += chValue;
            }
        }
        while (!isEmpty()) {
            Character oper = peek();
            if (!oper.equals(new Character('('))) {
                pop();
                postfixString += oper.charValue();
            }
        }
        return postfixString;
    }

    public static void main(String[] args) {
        Infix2Postfix mystack = new Infix2Postfix();
        System.out.println("Type in an expression");     
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        scan.close();
        System.out.println("The Expression you have typed in infix form :\n"+str);
        System.out.println("The Equivalent Postfix Expression is :\n"+mystack.InToPost(str));
    }
}

