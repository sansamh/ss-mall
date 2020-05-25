package io.sansam.config.lc;

import java.util.Stack;

/**
 * <p>
 * Lc20
 * </p>
 *
 * @author houcb
 * @since 2020/5/14 3:35 下午
 */
public class Lc20 {

    public static void main(String[] args) {
        System.out.println(isValid(""));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Character character;
        for (int i = 0; i < s.length(); i++) {
            character = s.charAt(i);
            if (character == '[') {
                stack.push(']');
            } else if (character == '(') {
                stack.push(')');
            } else if (character == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != character) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
