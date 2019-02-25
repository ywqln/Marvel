package com.ywqln.marvel;

import static org.junit.Assert.assertEquals;

import com.ywqln.marvel.webkit.base.UrlResolve;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        new TestAnnotation().testAnnotation();

        AAA aaa = new AAA();
        aaa.setName("张三");
        aaa.setAge(23);
        aaa.setAddress("河南");

        String result = UrlResolve.toUrlParam("http://www.aa.cc?a=3&h=1", aaa);
        System.out.println(result);
        AAA aa = UrlResolve.toObject(result, AAA.class);


        assertEquals(4, 2 + 2);
    }

    class AAA {
        private String name;
        private int age;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}