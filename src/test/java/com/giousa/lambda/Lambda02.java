package com.giousa.lambda;

import com.giousa.lambda.custome.CustomeConsumer;
import com.giousa.lambda.custome.CustomeFunction;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda02 extends BaseTest {

    @Test
    public void testRunnable() {
        Runnable runnable = () -> System.out.println("this is runnable");
        runnable.run();

        System.out.println("-------------------");
        Runnable runnable1 = () -> {
            System.out.println("run first");
            System.out.println("run last");
        };
        runnable1.run();

        System.out.println("-------------------");
        ActionListener actionListener = it -> System.out.println(it.toString());
        actionListener.actionPerformed(new ActionEvent("hello world",1,"222"));

        System.out.println("-------------------");
        BinaryOperator<Long> dd = (x, y) -> x + y;
        System.out.println("dd = "+dd.apply(111L,222L));

        System.out.println("-------------------");
        BinaryOperator<Long> add = (Long x,Long y) -> {
            System.out.println("计算过程x="+x+",y="+y);
            return x+y;
        };
        System.out.println("add = "+add.apply(10L,20L));

    }

    @Test
    public void testRunnable2(){
        try {
            String name = getUserName();
//            name = "211";
            Runnable runnable = () -> System.out.println("hello + "+ name);
            runnable.run();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private String getUserName(){

        return "不笑猫";
    }

    /**
     * 判断boolean
     */
    @Test
    public void testPredicate(){
        Predicate<String> predicate = it -> Objects.equals("hello",it);
        Predicate<String> predicate2 = it -> Objects.equals("world",it);
        System.out.println(predicate);
        System.out.println(predicate2);
        System.out.println(predicate.test("hello"));
        System.out.println(predicate.test("world"));

        System.out.println(predicate.and(predicate2));
        System.out.println(predicate.or(predicate2));
        System.out.println(predicate.negate());

        System.out.println("----------------");
        Predicate<String> predicate3 = predicate.or(predicate2);
        System.out.println(predicate3.test("hello"));
        System.out.println(predicate3.test("world"));

        System.out.println("----------------");

        /**
         * Predicate 校验数据，and方式
         */
        Predicate<Integer> pre1 = it -> it > 10;
        Predicate<Integer> pre2 = it -> it < 20;

        System.out.println(pre1.and(pre2).test(9));
        System.out.println(pre1.and(pre2).test(15));
        System.out.println(pre1.and(pre2).test(21));


    }

    /**
     * 有入参，无出参
     * 简单来说，就是执行操作
     */
    @Test
    public void testConsumer(){
        Consumer<Long> consumer1 = it -> System.out.println("it1 = "+it);
        Consumer<Long> consumer2 = it -> System.out.println("it2 = "+it);
        Consumer<String> consumer3 = it -> System.out.println("it3 = "+it);
        consumer1.accept(100L);
        System.out.println("----------------");
        consumer1.andThen(consumer2).accept(999L);
        System.out.println("----------------");
    }

    /**
     * 入参一个值，返回一个值
     */
    @Test
    public void testFunction(){
        Function<Long,String> function = it -> "this is function return, param = "+it;
        String apply = function.apply(100L);
        System.out.println(apply);
    }

    /**
     * 入参无值，输出一个值
     */
    @Test
    public void testSupplier(){
        Supplier<String> supplier = ()-> "this is you need data";
        String s = supplier.get();
        System.out.println(s);
    }

    /**
     * 输出
     */
    @Test
    public void testUnaryOperator(){
        UnaryOperator<String> unaryOperator = it -> "it = "+it;
        System.out.println(unaryOperator.apply("hello"));
    }

    /**
     * 两个数据，输出1个
     */
    @Test
    public void testBinaryOperator(){
        BinaryOperator<String> binaryOperator = (x,y) -> "x = "+x+",y = "+y;
        String sss = binaryOperator.apply("不笑猫", "o(*￣︶￣*)o");
        System.out.println(sss);

    }

    public final static ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    @Test
    public void testThread(){
        Supplier<Long> supplier = () -> 100L;

        ThreadLocal<Long> threadLocal = ThreadLocal.withInitial(supplier);

        Long aLong = threadLocal.get();
        System.out.println(aLong);

    }

    @Test
    public void testActionListener(){
        ActionListener actionListener = it -> System.out.println(it.getActionCommand());
        actionListener.actionPerformed(new ActionEvent("!!!",100,"test"));
    }

    @Test
    public void forTest(){
        List<String> list = Lists.newArrayList("a","b","c","d","e","f");
        for(String s : list){
            System.out.println(s);
        }

        System.out.println("----------------------");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void testFilter(){
        List<String> list = Lists.newArrayList("a","b","c","d","e","f");

        List<String> c = list.stream().filter(it -> Objects.equals("c", it)).collect(Collectors.toList());
        System.out.println(c);

        List<String> collect = list.stream().map(it -> it + "HLLO").collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void flatMapTest(){
        List<String> list1 = Lists.newArrayList("a","b","c","d","e","f");
        List<String> list2 = Lists.newArrayList("1","2","3");

        List<String> collect = Stream.of(list1, list2).flatMap(it -> it.stream()).map(it -> "HOL-" + it).collect(Collectors.toList());

        System.out.println(collect);


    }

    @Test
    public void minTest(){
        List<String> list = Lists.newArrayList("afff","bsF","c112","df","es213","f335f");
//        List<String> list = Lists.newArrayList();
        String minStr = list.stream().min(Comparator.comparing(it -> it.length())).get();
        System.out.println("minStr = "+minStr);
    }

    @Test
    public void maxTest(){
        List<String> list = Lists.newArrayList("afff","bsF","c112","df","es213","f335f");
        String maxStr = list.stream().max(Comparator.comparing(it -> it.length())).get();
        System.out.println("maxStr = "+maxStr);
    }

    @Test
    public void reduceTest(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        Integer integer = list.stream().reduce((x, y) -> x + y).get();
        System.out.println(integer);

        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        Integer integer1 = list.stream().reduce((x, y) -> x * y).get();
        System.out.println(integer1);

    }

    @Test
    public void longToTest(){

        ToLongFunction<Long> toLongFunction = it -> it;
        long l = toLongFunction.applyAsLong(100L);
        System.out.println(l);

        List<Long> list = Lists.newArrayList(100L,101L);
    }

    private void overloadMethod(Object o){
        System.out.println("object: "+o);
    }

    private void overloadMethod(String str){
        System.out.println("str: "+str);
    }



    private interface InterfaceBiFuncation extends BinaryOperator<Integer>{

    }

    private void overloadMethod(BinaryOperator<Integer> param){
        System.out.println("BinaryOperator<Integer>");
    }

    private void overloadMethod(InterfaceBiFuncation param){
        System.out.println("InterfaceBiFuncation");
    }


    @Test
    public void overloadTest(){
        overloadMethod("hello world");
        overloadMethod(new Integer(111));
        System.out.println("----------");

        overloadMethod((x,y) -> x+y);
    }

    @Test
    public void testCustomeFunction(){
        CustomeFunction<String,Long> customeFunction = it -> {
            System.out.println("it = "+it);
            return 100L;
        };
        customeFunction.exec("22222");

        CustomeConsumer<Long> consumer = it -> System.out.println("this is result : "+it);
        consumer.gettt(10000L);
    }

    @Test
    public void testForeach(){
        List<String> list = Lists.newArrayList("a","b","c","d","e","f");

        list.stream().forEach(it -> System.out.println("this is : "+it));

    }

    @Test
    public void testOptional(){
        Optional<Integer> optionalInteger = Optional.of(10);
        System.out.println(optionalInteger.get());

        boolean present = optionalInteger.isPresent();
        System.out.println(present);

        System.out.println("--------------");
        Optional<Object> optional1 = Optional.empty();
        Optional<Object> optional2 = Optional.ofNullable(null);
        System.out.println(optional1.isPresent());
        System.out.println(optional2.isPresent());
        System.out.println("--------------");
//        System.out.println(optional1.get());
//        System.out.println(optional2.get());
        System.out.println("--------------");
        System.out.println(optionalInteger.orElse(99));
        System.out.println(optionalInteger.orElseGet(() -> 19));
        System.out.println(optional1.orElse(100));
        System.out.println(optional1.orElseGet(() -> 101));
        System.out.println(optional2.orElse(200));
        System.out.println(optional2.orElseGet(() -> 201));


    }

    @Test
    public void testListSort(){
        List<String> list = Lists.newArrayList("a","b","c","d","e","f");
        System.out.println(list);

        List<String> collect = list.stream().collect(Collectors.toList());
        System.out.println(collect);


    }

    @Test
    public void testCollectors(){
        List<String> list = Lists.newArrayList("a","b","c","d","e","f");

        HashSet<String> collect = list.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect);

        String s = list.stream().max(String::compareTo).get();
        System.out.println(s);

    }

    /**
     * 自动分组
     */
    @Test
    public void testPartitioningBy(){
        List<User> list = Lists.newArrayList(
          new User(1L,"AAA",12,"doctor"),
          new User(2L,"BBB",23,"user"),
          new User(3L,"CCC",45,"doctor"),
          new User(4L,"hello",9,"user"),
          new User(5L,"world",42,"doctor")
        );

        Map<Boolean, List<User>> userList = list.stream().collect(Collectors.partitioningBy(it -> Objects.equals(it.getType(), "user")));
        System.out.println(userList);
    }

    /**
     * 自动分组
     * groupingBy 底层是function，比partitioningBy使用的Predicate更广泛
     */
    @Test
    public void testGroupBy(){
        List<User> list = Lists.newArrayList(
                new User(1L,"AAA",12,"doctor"),
                new User(2L,"BBB",23,"user"),
                new User(3L,"CCC",45,"doctor"),
                new User(4L,"hello",9,"user"),
                new User(5L,"world",42,"doctor"),
                new User(6L,"不笑猫",15,"user"),
                new User(7L,"灵梦",15,"wunv")
        );

        Map<Boolean, List<User>> userList = list.stream().collect(Collectors.groupingBy(it -> Objects.equals(it.getType(), "user")));
        System.out.println("根据类型是否是user分组：");
        System.out.println(userList);

        System.out.println("根据类型分组:");
        Map<String, List<User>> collect1 = list.stream().collect(Collectors.groupingBy(User::getType));
        System.out.println(collect1);
        System.out.println("根据年龄分组:");
        Map<Integer, List<User>> collect2 = list.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println(collect2);

        System.out.println("组装新的map：");
        Map<String, Long> collect3 = list.stream().collect(Collectors.groupingBy(User::getType, Collectors.counting()));
        System.out.println(collect3);

        System.out.println("组装新map，mapping方式：");
        Map<Long, List<String>> collect4 = list.stream().collect(Collectors.groupingBy(User::getId, Collectors.mapping(User::getType, Collectors.toList())));
        System.out.println(collect4);

    }

    @Test
    public void testJoining(){
        List<User> list = Lists.newArrayList(
                new User(1L,"AAA",12,"doctor"),
                new User(2L,"BBB",23,"user"),
                new User(3L,"CCC",45,"doctor"),
                new User(4L,"hello",9,"user"),
                new User(5L,"world",42,"doctor")
        );

        String collect = list.stream().map(User::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect);
    }

    @Test
    public void testSupplierAdd(){
        Supplier<Long> supplier = () -> 100L;

        BiConsumer<Long,String> biConsumer = (x,y) -> System.out.println("11");

        BinaryOperator<Long> binaryOperator = (x,y) -> 100L;
    }

    @Test
    public void testParalle(){
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        System.out.println("方式一：");
        long pre1 = System.currentTimeMillis();
        List<String> collect1 = list.stream().map(Objects::toString).collect(Collectors.toList());
        System.out.println("耗时1："+(System.currentTimeMillis() - pre1));

        System.out.println("方式二：");
        long pre2 = System.currentTimeMillis();
        List<String> collect2 = list.parallelStream().map(Objects::toString).collect(Collectors.toList());
        System.out.println("耗时1："+(System.currentTimeMillis() - pre2));

    }
}
