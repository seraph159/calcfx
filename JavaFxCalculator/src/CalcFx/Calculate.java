package CalcFx;

//errors: factorial
public class Calculate {

    myStack stack;
    String errorStr = "Invalid Expression ";
    public String calcMain(String str) {
        String[] jam = str.split("(?<=[()+*/^=-])|(?=[()+*/^=-])");
        String result = new String("");
        String strEmpty = " ";
        int k = 0, m = 0;

        jam[0] = jam[0].replaceFirst("!calc\\s*","");

        for (int j = 0; j < jam.length; j++)
            jam[j] = jam[j].replaceAll("\\s*","");

        //To check whether the parenthesis are missing
        for (int i = 0; i < jam.length; i++){
            if (jam[i].equalsIgnoreCase("(")) {
                m++;
            } else if(jam[i].equalsIgnoreCase(")"))
                k++;
        }

        if(!(k == m)){
            return "Invalid expression: Missing parentheses";
        }

        //Solves the issue to trig functions with parentheses
        for (int i = 0; i < jam.length; i++){
            if(jam[i].equalsIgnoreCase("√") || jam[i].equalsIgnoreCase("sin") || jam[i].equalsIgnoreCase("cos") || jam[i].equalsIgnoreCase("tan")) {
                jam[i] = jam[i] + jam[i + 1] + jam[i + 2] + jam[i + 3];
                jam[i + 1] = "";
                jam[i + 2] = "";
                jam[i + 3] = "";
            }
        }

/*        //Print out all the array elements after splitting them
        for (String str1 : jam
        ) {
            System.out.println(str1 + " = " + str1.matches("[0-9]+"));
        }*/

        stack = new myStack(jam.length);

        for (int i = 0; i < jam.length; i++) {

            if (jam[i].matches("[()+*/^-]") && stack.isEmpty())
                stack.push(jam[i]);
                //For factorial evaluation
            else if(jam[i].matches("[0-9]+!")) {
                jam[i] = megaFactorial(jam[i].replaceAll("!", ""));
                result = result + strEmpty + jam[i];
            }
            else if(jam[i].matches("sin\\(*([0-9.]+)\\)*")){
                jam[i] = jam[i].replaceAll("(sin\\(*)|(\\)*)","");
                if(Double.toString(Math.sin(Double.parseDouble(jam[i]))).matches("[\\-][0-9.]+")) {
                    jam[i] = Double.toString(Math.sin(Double.parseDouble(jam[i]))).replaceFirst("[\\-]?", "0 ") + "T";
                    result = result + strEmpty + jam[i].replaceFirst("T+", " -");
                }
                else {
                    jam[i] = jam[i].replaceAll("(sin\\(*)|(\\)*)", "");
                    result = result + strEmpty + Math.sin(Double.parseDouble(jam[i]));
                }
            }
            else if(jam[i].matches("cos\\(*([0-9.]+)\\)*")) {
                jam[i] = jam[i].replaceAll("(cos\\(*)|(\\)*)", "");
                if(Double.toString(Math.cos(Double.parseDouble(jam[i]))).matches("[\\-][0-9.]+")) {
                    jam[i] = Double.toString(Math.cos(Double.parseDouble(jam[i]))).replaceFirst("[\\-]?", "0 ") + "T";
                    result = result + strEmpty + jam[i].replaceFirst("T+", " -");
                }
                else {
                    jam[i] = jam[i].replaceAll("(cos\\(*)|(\\)*)", "");
                    result = result + strEmpty + Math.cos(Double.parseDouble(jam[i]));
                }
            }
            else if(jam[i].matches("tan\\(*([0-9.]+)\\)*")) {
                jam[i] = jam[i].replaceAll("(tan\\(*)|(\\)*)", "");
                if(Double.toString(Math.tan(Double.parseDouble(jam[i]))).matches("[\\-][0-9.]+")) {
                    jam[i] = Double.toString(Math.tan(Double.parseDouble(jam[i]))).replaceFirst("[\\-]?", "0 ") + "T";
                    result = result + strEmpty + jam[i].replaceFirst("T+", " -");
                }
                else {
                    jam[i] = jam[i].replaceAll("(tan\\(*)|(\\)*)", "");
                    result = result + strEmpty + Math.tan(Double.parseDouble(jam[i]));
                }
                //System.out.println(Math.tan(Double.parseDouble(jam[i])));
            }
            else if(jam[i].matches("√\\(*([0-9.]+)\\)*")) {
                jam[i] = jam[i].replaceAll("(√\\(*)|(\\)*)", "");
                result = result + strEmpty + Math.sqrt(Double.parseDouble(jam[i]));
            }
            else if ((jam[i].matches("[0-9.]+"))) {
                result = result + strEmpty + jam[i];
            }
            else if ((jam[i].matches("[0-9.]+%[0-9]+"))) {
                String[] modArray = new String[2];
                modArray = jam[i].split("%");
                jam[i] = Double.toString(Double.parseDouble(modArray[0]) % Double.parseDouble(modArray[1]));
                result = result + strEmpty + jam[i];
            }
            else if (jam[i].equalsIgnoreCase("("))
                stack.push(jam[i]);
            else if (jam[i].equalsIgnoreCase("π"))
                result = result + strEmpty + "3.14159";
            else if (jam[i].equalsIgnoreCase("e"))
                result = result + strEmpty + "2.71828";
            else if (jam[i].equalsIgnoreCase(")"))
                try {
                    while (!stack.peek().equalsIgnoreCase("(")) {
                        result = result + strEmpty + stack.peek();
                        stack.pop();
                    }
                    stack.pop();
                } catch (Exception e1) {
                    return errorStr;
                }
            else if (jam[i].matches("[()+*/^-]") && highPrecedence(jam, i))
                stack.push(jam[i]);
            else if (jam[i].matches("[()+*/^-]") && !highPrecedence(jam, i)) {
                while (!stack.isEmpty() && (highPrecedence(jam[i]) <= highPrecedence(stack.peek()))) {
                    result = result + strEmpty + stack.peek();
                    stack.pop();
                }
                stack.push(jam[i]);
            }
        }

        if (jam[jam.length-1].equalsIgnoreCase(")"))
            for (int i = 0; i <= stack.top; i++) //if the received elements ends with a ')' then it causes the stack to be empty at i = -1
                result = result + strEmpty + stack.pop();
        else
            for (int i = -1; i <= stack.top; i++)
                result = result + strEmpty + stack.pop();

        return ("= "+ megaCalc(result) + strEmpty);
    }

    public String megaFactorial(String str){
        long totalFacnum = 1, facnum = Integer.parseInt(str);
        for(int k = 1; k <= facnum; k++){
            totalFacnum = totalFacnum * k;
        }
        return Long.toString(totalFacnum);
    }

    public String megaCalc(String str){

        String[] megaStr = str.split(" ");
        String megaResult = "";
        myStack megaStack = new myStack(megaStr.length);
        double var, var1;

        for (int i = 0; i < megaStr.length; i++)
            try {
                if (megaStr[i].matches("[0-9.]+"))
                    megaStack.push(megaStr[i]);
                else if (megaStr[i].equalsIgnoreCase("+"))
                    megaStack.push(Double.toString(Double.parseDouble(megaStack.pop()) + Double.parseDouble(megaStack.pop())));
                else if (megaStr[i].equalsIgnoreCase("-")) {
                    var = Double.parseDouble(megaStack.pop());
                    var1 = Double.parseDouble(megaStack.pop());
                    megaStack.push(Double.toString(var1 - var));
                } else if (megaStr[i].equalsIgnoreCase("*"))
                    megaStack.push(Double.toString(Double.parseDouble(megaStack.pop()) * Double.parseDouble(megaStack.pop())));
                else if (megaStr[i].equalsIgnoreCase("/")) {
                    var = Double.parseDouble(megaStack.pop());
                    var1 = Double.parseDouble(megaStack.pop());
                    megaStack.push(Double.toString(var1 / var));
                } else if (megaStr[i].equalsIgnoreCase("^")) {
                    var = Double.parseDouble(megaStack.pop());
                    var1 = Double.parseDouble(megaStack.pop());
                    megaStack.push(Double.toString(Math.pow(var1, var)));
                }
            }catch (Exception e){
                return errorStr;
            }
        for (int i = -1; i <= megaStack.top; i++){
            megaResult = megaResult + megaStack.pop();
        }

        return megaResult;
    }

    public int highPrecedence(String str) {
        int num = 0;

        switch (str) {
            case "-":
                num = 1;
                break;
            case "+":
                num = 1;
                break;
            case "/":
                num = 3;
                break;
            case "*":
                num = 4;
                break;
            case "^":
                num = 5;
                break;
        }

        return  num;
    }

    public boolean highPrecedence(String[] array, int i) {

        int num = 0, num1 = 0;
        switch (array[i]) {
            case "-":
                num = 1;
                break;
            case "+":
                num = 1;
                break;
            case "/":
                num = 3;
                break;
            case "*":
                num = 4;
                break;
            case "^":
                num = 5;
                break;
        }

        switch (stack.peek()) {
            case "-":
                num1 = 1;
                break;
            case "+":
                num1 = 1;
                break;
            case "/":
                num1 = 3;
                break;
            case "*":
                num1 = 4;
                break;
            case "^":
                num1 = 5;
                break;
        }

        if (num > num1)
            return true;
        else
            return false;

    }
}

class myStack {

    int size;
    String arr[];
    int top;

    public myStack(int size) {
        this.size = size;
        this.arr = new String[size];
        this.top = -1;
    }

    public boolean isFull() {
        return (size - 1 == top);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public String peek() {
        return arr[top];
    }


    public void push(String str) {

        if (!isFull()) {
            arr[++top] = str;
            System.out.println("Element Pushed " + str);
        } else {
            System.out.println("Stack is full : Overflow ");
        }
    }

    public String pop() {
        if (!isEmpty()) {
            String val = arr[top];
            top--;
            System.out.println("Popped element is " + val);
            return val;
        } else {
            System.out.println("Stack is empty : Underflow ");
            return "Invalid Expression";
        }

    }
}
