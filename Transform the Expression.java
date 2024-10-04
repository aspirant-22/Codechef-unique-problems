import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		while (t > 0){
		    String str = sc.next();
		    postfix(str);
		    t--;
		}

	}
	
	public static int precedence(char x){
	    if (x == '+' || x == '-') return 1;
	    if (x == '*' || x == '/') return 2;
	    if (x == '^') return 3;
	    return -1;
	}
	
	public static void postfix(String exp){
	    Stack <String> st1 = new Stack<>();    //operands
	    Stack <Character> st2 = new Stack<>();    //operators + Brack
	    
	    for (int i = 0 ; i < exp.length() ; i++){
	        char ch = exp.charAt(i);
	        
	        if (ch == '('){
	            st2.push(ch);
	        }
	        else if (ch >= 'a' && ch <= 'z'){
	            st1.push(ch + "");
	        }
	        else if (ch == ')'){
	            while(st2.peek() != '('){
	                String op2 = st1.pop();
	                String op1 = st1.pop();
	                char opr = st2.pop();
	                    
	                String ans = op1 + op2 + opr;
	                st1.push(ans);
	            }
	            st2.pop();
	            
	        }
	        else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^'){
	            while (st2.size() > 0 && precedence(st2.peek()) >= precedence(ch)){
	                // Handle right associativity of '^'
                    if (ch == '^' && st2.peek() == '^') break;
	                String op2 = st1.pop();
	                String op1 = st1.pop();
	                char opr = st2.pop();
	                    
	                String ans = op1 + op2 + opr;
	                st1.push(ans);
	            }
	            st2.push(ch);
	        }
	    }
	    while (st2.size() > 0){
	        String op2 = st1.pop();
	        String op1 = st1.pop();
	        char opr = st2.pop();
	        
	        st1.push(op1 + op2 + opr);
	    }
	    System.out.println(st1.pop());
	}
}
