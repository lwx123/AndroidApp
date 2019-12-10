package com.lwx.fish.myapplication.test;

/**
 * Created by wuxing on 2019/11/27.
 */
class TestClass2 {
    public int a = 1;
    private testInterface inter;

    public void show(){
        System.out.println("TestClass2");
    }

    interface testInterface{
        void show(String str);
    }

    public testInterface getInter() {
        return inter;
    }

    public void setInter(testInterface inter) {
        this.inter = inter;
    }
}
