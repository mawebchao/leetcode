package com.test.fuckmsb.thread;

class Outer{
    Integer id;
    Inner inner;

    public Outer(Integer id, Inner inner) {
        this.id = id;
        this.inner = inner;
    }
}
class Inner{
    public Inner(String name) {
        this.name = name;
    }

    String name;
}

public class Test_Machap {

    public static void main(String[] args) {
        Outer outer=new Outer(1,new Inner("hhh"));
        System.out.println(outer.inner);
        alterInner(outer);
        System.out.println(outer.inner);

    }

    private static void alterInner(Outer outer) {
        outer.inner=null;
    }
}
