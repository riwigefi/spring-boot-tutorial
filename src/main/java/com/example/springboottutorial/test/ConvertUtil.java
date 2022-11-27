package com.example.springboottutorial.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConvertUtil {

    /**
     * 将List转为Map
     * @param list 原始数据
     * @param keyExtractor Key的抽取规则
     * @return 转换的map
     * @param <K> Key
     * @param <V> Value
     */
    public static <K,V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for(V element: list) {
            K key = keyExtractor.apply(element);
            if (key == null) {
                continue;
            }
            map.put(key, element);
        }
        return map;
    }

    /**
     * 将list转为map，可以指定过滤规则
     * @param list 原数据
     * @param keyExtractor key的抽取规则
     * @param predicate 过滤规则
     * @return 转换后的map
     * @param <K> Key
     * @param <V> Value
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor, Predicate<V> predicate) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for(V element: list) {
            K key = keyExtractor.apply(element);
            if(key == null || !predicate.test(element)) {
                continue;
            }
            map.put(key, element);
        }
        return map;
    }

    public static <T, R> List<R> listTransform(List<T> originList, Function<T, R> mapper) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        List<R> newList = new ArrayList<>(originList.size());
        for(T originElement:originList) {
            R newElement = mapper.apply(originElement);
            if (newElement == null) {
                continue;
            }
            newList.add(newElement);
        }
        return newList;
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private Integer age;
    private String address;
    private Double salary;
}