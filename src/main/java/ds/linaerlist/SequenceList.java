package ds.linaerlist;

import java.util.*;

/**
 * 顺序表的实现(ArrayList)
 *
 * <p>RandomAccess 接口，这个接口没有任何方法，实现这个接口即意味着支持快速随机访问（下标访问）。快速随机访问速度 > 迭代器遍历速度。
 *
 * <p>link: https://www.lagou.com/lgeduarticle/95386.html
 *
 * @author Suz1
 * @date 2020/5/21 8:47 下午
 */
public class SequenceList<E> extends AbstractList<E> implements List<E>, Cloneable, RandomAccess {
    // default length of the array
    private static final int DEFAULT_CAPACITY = 10;

    // empty array
    private static final Object[] EMPTY_ELEMENTDATA = {};
    // TODO https://stackoverflow.com/questions/35756277/why-the-maximum-array-size-of-arraylist-is-integer-max-value-8
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * 我们将此与EMPTY_ELEMENTDATA区别开来，以了解添加第一个元素时需要扩容多少。
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private Object[] elements;
    // the elements count
    private int size;

    // constructor with initial capacity
    public SequenceList(int initCap) {
        assert initCap >= 0;
        elements = initCap == 0 ? EMPTY_ELEMENTDATA : new Object[initCap];
    }

    // constructor with no input
    public SequenceList() {this.elements = EMPTY_ELEMENTDATA;}

    // constructor with collection
    public SequenceList(Collection<? extends E> c) {
        elements = c.toArray();
        if ((size = elements.length) != 0) {
            // TODO
            if (elements.getClass() != Object[].class) {
                elements = Arrays.copyOf(elements, size, Object[].class);
            } else {
                this.elements = EMPTY_ELEMENTDATA;
            }
        }
    }

    private static int hugeCap(int minCap) {
        // 是否超过 int 的最大值 overflow
        if (minCap < 0) {
            throw new OutOfMemoryError();
        }
        return (minCap > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    private static int calculateCap(Object[] elements, int minCap) {
        if (elements == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCap);
        }
        return minCap;
    }

    // trim the list
    public void trimToSize() {
        // TODO
        modCount++;
        if (size < elements.length) {
            elements = (size == 0) ? EMPTY_ELEMENTDATA : Arrays.copyOf(elements, size);
        }
    }

    public void ensureCapacity(int minCap) {
        int minExpand = (elements != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) ? 0 : DEFAULT_CAPACITY;
        if (minCap > minExpand) {
            ensureExplicitCap(minCap);
        }
    }

    private void ensureCapacityInternal(int minCap) {
        ensureExplicitCap(calculateCap(elements, minCap));
    }

    private void ensureExplicitCap(int minCap) {
        // modify count ++
        modCount++;
        if (minCap > elements.length) {
            grow(minCap);
        }
    }

    /**
     * 增加容量以确保它至少可以容纳
     * 最小容量参数指定的元素数。
     *
     * @param minCap mini cap
     */
    private void grow(int minCap) {
        int originCap = elements.length;
        // move 1 bit to right = 1.5 * origin arr len
        int newCap = originCap + originCap >> 1;
        if (newCap - minCap < 0) {
            newCap = minCap;
        }
        if (newCap - MAX_ARRAY_SIZE > 0) {
            newCap = hugeCap(minCap);
        }
        elements = Arrays.copyOf(elements, newCap);
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        assert index >= 0;
        return (E) elements[index];

    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    // shallow clone
    @Override
    public Object clone() {
        try {
            SequenceList<?> v = (SequenceList<?>) super.clone();
            v.elements = Arrays.copyOf(elements, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public boolean add(E e) {
        // modify count ++
        ensureCapacityInternal(size + 1);
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E e) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size++);
        // TODO
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = e;
        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        modCount++;
        E oldVal = (E) elements[index];
        // TODO
        int numMove = size - index - 1;
        if (numMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMove);
        }
        // TODO --size = null and let GC do it's work
        elements[--size] = null;
        return oldVal;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                fastRemove(i);
                return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        modCount++;
        int numRemove = size - index - 1;
        if (numRemove > 0) {
            System.arraycopy(elements, index + 1, elements, index, numRemove);
        }
        // let gc do it's work
        elements[--size] = null;
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void clear() {
        modCount++;
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}
