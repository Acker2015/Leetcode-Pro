package javapg;

/**
 * 基于静态内部类来实现单例模式
 * 1. 线程安全 - 靠JVM保证类的静态成员只能被加载一次的特点，这样就从JVM层面保证了只会有一个实例对象
 * 2. Lazy 加载一个类时，其内部类不会同时被加载。一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
 */
public class Singleton_Static {
    private static class Singleton_Inner {
        private static Singleton_Inner singleton_inner = new Singleton_Inner();
    }
    private Singleton_Static() {}
    public Singleton_Inner getInstance() {
        return Singleton_Inner.singleton_inner;
    }





}
