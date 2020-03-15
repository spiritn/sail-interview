package stack;

/**
 * @author Jun
 * 创建时间： 2020/2/29
 */
public class StackAlgo {

    public static void main(String[] args) {
        String str = "[{()}]123([])78";
        System.out.println(match(str));
    }

    /**
     * 我们假设表达式中只包含三种括号字符，圆括号 ()、方括号[]和花括号{}，并且它们可以任意嵌套。比如，{[] ()[{}]}或[{()}([])]等都为合法格式，
     * 而{[}()]或[({)]为不合法的格式。现在给你一个包含三种括号的表达式字符串，如何检查它是否合法呢？
     */
    public static boolean match(String str){
        if(str == null || str.length() == 1){
            return false;
        }
        StackArray stack = new StackArray(str.length());
        //开始扫描字符串
        for(char c : str.toCharArray()){
            String s = new String(new char[]{c});
            //遇到左括号的时候，就压入栈
            if("{".equals(s) || "[".equals(s) || "(".equals(s)){
                stack.push(s);

             //遇到右括号的时候，就和栈顶元素进行比较
            }else if("}".equals(s) || "]".equals(s) || ")".equals(s)){
                String left = stack.pop();
                //看是否匹配
                boolean match = ("}".equals(s) && "{".equals(left))
                        || ("]".equals(s) && "[".equals(left))
                        || (")".equals(s) && "(".equals(left));
                //如果匹配就继续扫描，如果不匹配就直接返回false
                if (!match){
                    return false;
                }
            }
        }
        if(stack.size() != 0){
            return false;
        }
        return true;
    }


}
