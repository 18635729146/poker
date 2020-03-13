package test;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* KaPai Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 13, 2020</pre> 
* @version 1.0 
*/ 
public class KaPaiTest { 

@Before
public void before() throws Exception {
    System.out.println("begin");
} 

@After
public void after() throws Exception {
    System.out.println("over");
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here...
   // System.out.println("主函数");
} 

/** 
* 
* Method: buyCard() 
* 
*/ 
@Test
public void testBuyCard() throws Exception { 
//TODO: Test goes here...
    System.out.println("准备一副扑克");
} 

/** 
* 
* Method: XiPai(List<Pai> list) 
* 
*/ 
@Test
public void testXiPai() throws Exception { 
//TODO: Test goes here...
    System.out.println("洗牌");
}

/** 
* 
* Method: match(List<List<Pai>> players) 
* 
*/ 
@Test
public void testMatch() throws Exception { 
//TODO: Test goes here...
    System.out.println("比较牌的大小");
} 


/** 
* 
* Method: swap(String[] arr) 
* 
*/ 
@Test
public void testSwap() throws Exception { 
//TODO: Test goes here...
    System.out.println("冒泡排序");
/* 
try { 
   Method method = KaPai.getClass().getMethod("swap", String[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: compaire(String[] arr) 
* 
*/ 
@Test
public void testCompaire() throws Exception { 
//TODO: Test goes here...
    System.out.println("为每个牌面设置一个权值");

/* 
try { 
   Method method = KaPai.getClass().getMethod("compaire", String[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
