package io.sansam.point.array;

/**
 * <p>
 * P61ContinousCards
 * </p>
 *
 * @author houcb
 * @since 2020-06-10 11:07
 */

import java.util.Arrays;

/**
 * 题目
 * 　　从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
 * <p>
 * 思路
 * 　　输入为大小等于5的数组（大小王记为0），输出为布尔值。具体步骤如下：
 * <p>
 * 　　1）进行对5张牌进行排序；
 * <p>
 * 　　2）找出0的个数；
 * <p>
 * 　　3）算出相邻数字的空缺总数；
 * <p>
 * 　　4）如果0的个数大于等于空缺总数，说明连续，反之不连续；
 * <p>
 * 　　5）记得判断相邻数字是否相等，如果有出现相等，说明不是顺子。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（没有/有一个/多个大小王，有对子，连续/不连续）
 * <p>
 * 　　2.特殊测试（null）
 */
public class P61ContinousCards {

    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return false;
        }
        // 统计里面大小王的个数 大小王用0表示
        int numOfZero = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                numOfZero++;
            }
        }

        // 先排序数组 找出相邻数字的空缺总数
        Arrays.sort(numbers);
        int gap = 0;

        int left = numOfZero;
        int right = numOfZero + 1;
        // right领先left一步 每次计算中间的差 right - left + 1 如果顺序挨着的 差为 4 - 3 + 1 = 0
        while (right < numbers.length) {
            // 相同的牌 不算顺子
            if (numbers[left] == numbers[right]) {
                return false;
            }
            gap += numbers[right++] - numbers[left++] + 1;
        }

        if (gap > numOfZero) return false;

        return true;
    }
}
