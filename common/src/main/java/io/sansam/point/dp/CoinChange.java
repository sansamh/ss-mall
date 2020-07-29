package io.sansam.point.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * CoinChange
 * </p>
 *
 * @author houcb
 * @since 2020-07-24 11:26
 */
public class CoinChange {

    static Map<Integer, Integer> map = new HashMap<>();

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;

        if (map.get(amount) != null) {
            return map.get(amount);
        }

        for (int i = 0; i < coins.length; i++) {
            int sub = coinChange(coins, amount - coins[i]);
            if (sub == -1) continue;

            res = Math.min(res, 1 + sub);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        map.put(amount, res);
        return map.get(amount);
    }

    public static void main(String[] args) {
        int[] coins = {186, 419, 83, 408};
        int amount = 6249;
        System.out.println(coinChange(coins, amount));
    }
}
