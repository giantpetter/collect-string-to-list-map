package com.github.hcsp.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public class Main {
    // 请编写一个方法，对传入的List<User>进行如下处理：
    // 返回一个从部门名到这个部门的所有用户的映射。同一个部门的用户按照年龄进行从小到大排序。
    // 例如，传入的users是[{name=张三, department=技术部, age=40 }, {name=李四, department=技术部, age=30 },
    // {name=王五, department=市场部, age=40 }]
    // 返回如下映射：
    //    技术部 -> [{name=李四, department=技术部, age=30 }, {name=张三, department=技术部, age=40 }]
    //    市场部 -> [{name=王五, department=市场部, age=40 }]
    public static Map<String, List<User>> collect(List<User> users) {

        //我好想用Multimap啊,别扭的实现
        Map<String, PriorityQueue<User>> cacheMap = new HashMap<>();
        Map<String, List<User>> map = new HashMap<>();
        for (User user : users) {
            mapPutUser(cacheMap, user);
        }
        cacheMap.forEach((key, val) -> map.put(key, new ArrayList<>(val)));
        return map;
    }

    public static void mapPutUser(Map<String, PriorityQueue<User>> map, User user) {
        String department = user.getDepartment();
        if (map.containsKey(department)) {
            map.get(department).add(user);
        } else {
            PriorityQueue<User> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(user);
            map.put(department, priorityQueue);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                collect(
                        Arrays.asList(
                                new User(1, "张三", 40, "技术部"),
                                new User(2, "李四", 30, "技术部"),
                                new User(3, "王五", 40, "市场部"))));
    }
}
