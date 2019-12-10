package com.lwx.fish.myapplication.test;


/**
 * Created by wuxing on 2019/11/19.
 */
public class TestClass {
    public static void main(String[] args) {
//        TestClass.AA  aa = new TestClass().new AA();
//        aa.show();
//
//        new TestClass2().setInter(new TestClass2.testInterface() {
//            @Override
//            public void show(String str) {
//
//            }
//        });

        TestClass t = new TestClass();
        TestClass2 t2 = new TestClass2();
        t.save(t2);
        System.out.println(t2.a);

    }

    class AA{
        public void show(){
            System.out.println("AA");
        }
    }

    protected class BB {
        public void show(){
            System.out.println("BB");
        }
    }

    private void save(TestClass2 testClass2){
        testClass2.a = 2;
    }
}
