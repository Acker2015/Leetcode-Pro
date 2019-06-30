package java;

/**
 * 单例模式 懒加载
 * 将singletonLazy的定义加上volatile关键字，就可以保证每次都去singletonLazy都从主内存读取，并且可以禁止重排序，就可以使用DCL的写法来完成单例模式。
 */
//单例模式的懒汉实现5--线程安全
//通过设置同步代码块，使用DCL双检查锁机制
//使用双检查锁机制成功的解决了单例模式的懒汉实现的线程不安全问题和效率问题
//DCL 也是大多数多线程结合单例模式使用的解决方案
//第一个if判断的作用：是为了提高程序的 效率，当SingletonLazy5对象被创建以后，再获取SingletonLazy5对象时就不用去验证同步代码块的锁及后面的代码，直接返回SingletonLazy5对象
//第二个if判断的作用：是为了解决多线程下的安全性问题，也就是保证对象的唯一。
public class Singleton {
    private volatile Singleton singletonLazy = null;

    private Singleton() {}

    public Singleton getInstance() {
        if (this.singletonLazy == null) {
            synchronized (Singleton.class) {
                // 双检测
                if (this.singletonLazy == null) {
                    this.singletonLazy = new Singleton();
                }
                return this.singletonLazy;
            }
        } else {
            return this.singletonLazy;
        }
    }
}


