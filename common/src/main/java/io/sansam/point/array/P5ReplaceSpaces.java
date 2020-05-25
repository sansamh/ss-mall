package io.sansam.point.array;

/**
 * <p>
 * P5ReplaceSpaces
 * </p>
 *
 * @author houcb
 * @since 2020/5/15 2:58 下午
 */
public class P5ReplaceSpaces {

    public static void main(String[] args) {
        P5ReplaceSpaces rs = new P5ReplaceSpaces();
        rs.test1();
        rs.test2();
        rs.test3();
        rs.test4();
    }

    /**
     * 收获：如果在从前往后进行复制时，需要多次移动数据，则可以考虑从后往前复制，从而减小移动次数，提高效率。
     * <p>
     * 计算出新字符串的长度 新长度 = 原长度 遇到一个空格则+2
     * 从尾到头遍历原字符串 一个指针指向新字符串尾巴
     * 遇到空格 指针-- 插入0 2 %
     * 遇到字符 指针-- 插入字符
     *
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        String old;
        if (str == null || (old = str.toString()).length() == 0) return "wrong String";
        char[] oldChars = old.toCharArray();
        int len = old.length();
        for (char c : oldChars) {
            if ((int) c == 32) {
                len += 2;
            }
        }
        char[] res = new char[len];
        int p = len - 1;
        for (int i = old.length() - 1; i >= 0; i--) {
            if ((int) old.charAt(i) == 32) {
                res[p--] = '0';
                res[p--] = '2';
                res[p--] = '%';
            } else {
                res[p--] = old.charAt(i);
            }
        }


        return String.valueOf(res);

    }


    /**
     * 输入为null
     */
    public void test1() {
        System.out.print("Test1：");
        StringBuffer sBuffer = null;
        String s = replaceSpace(sBuffer);
        System.out.println(s);
    }

    /**
     * 输入为空字符串
     */
    public void test2() {
        System.out.print("Test2：");
        StringBuffer sBuffer = new StringBuffer("");
        String s = replaceSpace(sBuffer);
        System.out.println(s);
    }

    /**
     * 输入字符串无空格
     */
    public void test3() {
        System.out.print("Test3：");
        StringBuffer sBuffer = new StringBuffer("abc");
        String s = replaceSpace(sBuffer);
        System.out.println(s);
    }

    /**
     * 输入字符串为首尾空格，中间连续空格
     */
    public void test4() {
        System.out.print("Test4：");
        StringBuffer sBuffer = new StringBuffer(" a b  c  ");
        String s = replaceSpace(sBuffer);
        System.out.println(s);
    }


}
