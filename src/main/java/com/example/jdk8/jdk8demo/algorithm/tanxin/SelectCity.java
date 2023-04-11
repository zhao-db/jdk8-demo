package com.example.jdk8.jdk8demo.algorithm.tanxin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/4/10
 * @since 3.0.1
 */
public class SelectCity {

    public static void main(String[] args) {
        HashMap<String, Set> broadcast = new HashMap<>();   // 用于存放广播和覆盖的城市
        HashMap<String, Set> selectedList = new HashMap<>();    // 存放被选择的广播
        HashSet<String> cities = new HashSet<>();   // 城市表，存放的是未被广播覆盖的城市

        HashSet<String> k1_set = new HashSet<>();   // 广播 K1
        k1_set.add("北京");
        k1_set.add("上海");
        k1_set.add("天津");
        broadcast.put("K1", k1_set);

        HashSet<String> k2_set = new HashSet<>();   // 广播 K2
        k2_set.add("广州");
        k2_set.add("北京");
        k2_set.add("深圳");
        broadcast.put("K2", k2_set);

        HashSet<String> k3_set = new HashSet<>();   // 广播 K3
        k3_set.add("成都");
        k3_set.add("上海");
        k3_set.add("杭州");
        broadcast.put("K3", k3_set);

        HashSet<String> k4_set = new HashSet<>();   // 广播 K4
        k4_set.add("上海");
        k4_set.add("天津");
        broadcast.put("K4", k4_set);

        HashSet<String> k5_set = new HashSet<>();   // 广播 K5
        k5_set.add("杭州");
        k5_set.add("大连");
        broadcast.put("K5", k5_set);

        cities.addAll(k1_set);		// 在未选择广播站的状态下，所有的城市都未被覆盖
        cities.addAll(k2_set);
        cities.addAll(k3_set);
        cities.addAll(k4_set);
        cities.addAll(k5_set);
        System.out.println(cities);	// 打印城市集合

        while (cities.size() > 0){	// 如果城市表不为空，说明还有城市未被覆盖
            String maxKey = null;	// 记录覆盖了最多的未覆盖城市的广播
            for (String item : broadcast.keySet()){
                Set set = broadcast.get(item);  // 获取当前广播覆盖的城市
                set.retainAll(cities);  // 求当前广播覆盖的城市和城市列表中的交集，也就是找到广播中未覆盖的城市
                int num = set.size();   // 获取交集的个数
                if (maxKey == null && num > 0){ // 如果 maxKey 为空且 num > 0
                    maxKey = item;
                }else if (maxKey != null){  // 如果 maxKey 不为空，那就和当前的广播比较一下谁和未覆盖城市列表的交集多
                    Set maxSet = broadcast.get(maxKey); // 获取之前记录下来的最多未覆盖城市的广播的城市集合
                    maxSet.retainAll(cities);	// 找到原来的 maxKey 覆盖了哪些未覆盖的城市
                    int maxNum = maxSet.size();
                    if (maxNum < num){
                        maxKey = item;
                    }
                }
            }
            // 一轮 for 循环之后，将会得到覆盖城市最多的广播
            selectedList.put(maxKey, broadcast.get(maxKey));    // 添加广播到被选中列表
            cities.removeAll(broadcast.get(maxKey));    // 城市已经覆盖了，移除掉
            broadcast.remove(maxKey);   // 广播使用过了，移除掉
        }
        // 打印被选中的广播
        for (String item : selectedList.keySet()){
            System.out.print(item + " ");
        }
    }


}
