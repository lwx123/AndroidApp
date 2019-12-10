package com.lwx.fish.myapplication.test;


public class AA {
    public static void main(String[] args) {

        A a = new AA().new A();
        new AA().save(a);
        System.out.println(a.a);
        //2
    }
    public void save(A a){
        a.a = 2;
    }

    class A{
        public int a = 1;
    }
}
