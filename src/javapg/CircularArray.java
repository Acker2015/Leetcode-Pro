package javapg;

import java.util.Iterator;

/**
 * 实现一个CircularArray类，支持类似数组的数据结构，这些数据结构可以高效的进行旋转。
 * 该类支持泛型，并能够通过标准的for(Object o: circularArray)语法支持迭代操作
 */
public class CircularArray<T> implements Iterable<T> {
    private T[] items;
    private int head;   // 旋转点的指针
    public CircularArray(int size) {
        this.items = (T[]) new Object[size];
        this.head = 0;
    }
    // 坐标索引转换
    private int convert(int index) {
        if (index < 0) {
            index += items.length;
        }
        return (head + index) % items.length;
    }

    public void rotate(int shiftRight) {
        this.head = this.convert(shiftRight);
    }

    public T get(int i) {
        if (i < 0 || i >= items.length) {
            throw new IndexOutOfBoundsException("...");
        }
        return this.items[convert(i)];
    }
    public void set(int i, T item) {
        if (i < 0 || i >= items.length) {
            throw new IndexOutOfBoundsException("...");
        }
        this.items[convert(i)] = item;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularArrayIterator<>(this);
    }

    private class CircularArrayIterator<E> implements Iterator<E> {
        // _current反映从旋转后的开头算起的偏移值，而不是从头算起
        private int _currrent = -1;
        private E[] _items;
        CircularArrayIterator(CircularArray<E> circularArray) {
            this._items = circularArray.items;
        }
        @Override
        public boolean hasNext() {
            return _currrent < this._items.length-1;
        }
        @Override
        public E next() {
            this._currrent++;
            return _items[convert(_currrent)];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("...");
        }
    }

    public static void main(String ...args) {
        CircularArray<String> circularArray = new CircularArray<>(5);
        circularArray.set(0, "a");
        circularArray.set(1, "super");
        circularArray.set(2, "Acker");
        circularArray.set(3, "I");
        circularArray.set(4, "am");
        circularArray.rotate(3);
        for (String str: circularArray) {
            System.out.println(str);
        }
    }
}
