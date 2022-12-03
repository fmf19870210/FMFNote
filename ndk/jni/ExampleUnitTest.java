package com.dongnao.jnitest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        //加载动态库
        System.load("C:\\Users\\Administrator\\CMakeBuilds\\aa8c6318-312a-e73d-a50d-2e7e86cf40bb\\build\\x64-Debug\\lsn7_example\\lsn7.dll");
        test(1, "java");
       test2(1, "haha")
    }

    native void test(int i, String j);
    native void test2(int i, String j);
}