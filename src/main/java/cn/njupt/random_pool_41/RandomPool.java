package cn.njupt.random_pool_41;

import java.util.HashMap;

/**
 * 设计RandomPool结构
 * 【题目】 设计一种结构， 在该结构中有如下三个功能：insert(key)： 将某个key加入到该结构， 做到不重复加入。
 * delete(key)： 将原本在结构中的某个key移除。
 * getRandom()：等概率随机返回结构中的任何一个key。
 * 【要求】 Insert、 delete和getRandom方法的时间复杂度都是O(1)
 */
public class RandomPool {
    public static class Pool<K> {
        private HashMap<K, Integer> map1;
        private HashMap<Integer, K> map2;
        private int size;

        public Pool() {
            this.map1 = new HashMap<>();
            this.map2 = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key) {
            if (!map1.containsKey(key)) {
                map1.put(key, size);
                map2.put(size++, key);
            }
        }

        public K getRandom() {
            if (map1.isEmpty()) {
                return null;
            }

            int index = ((int) (Math.random() * size));

            return map2.get(index);
        }

        public void delete(K key) {
            if (map1.containsKey(key)) {
                int curIndex = map1.get(key);
                int lastIndex = --size;
                K lastKey = map2.get(lastIndex);
                map1.put(lastKey, curIndex);
                map2.put(curIndex, lastKey);
                map1.remove(key);
                map2.remove(lastIndex);
            }
        }

        public static void main(String[] args) {
            Pool<String> pool = new Pool<String>();
            pool.insert("zuo");
            pool.insert("cheng");
            pool.insert("yun");
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
        }
    }
}
