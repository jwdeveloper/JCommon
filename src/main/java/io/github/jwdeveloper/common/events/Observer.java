package io.github.jwdeveloper.common.events;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;


public class Observer<T> {
    private Field field;
    private Object object;
    private Class<?> fieldType;
    private boolean binded;
    protected Set<Consumer<T>> onChange;

    public Observer() {
        this.onChange = new LinkedHashSet();
    }

    public Observer(Object classObject, String field) {
        this();
        this.binded = this.bind(classObject.getClass(), field);
        this.object = classObject;
    }

    public Observer(Object classObject, Field field) {
        this();
        this.field = field;
        this.object = classObject;
        this.fieldType = this.field.getType();
        this.binded = true;
    }


    public static <T> Observer<T> create(T value) {
        var observer = new Observer<T>(value, "object");
        observer.bind(observer.getClass(), "value");
        observer.setObject(observer);
        return observer;
    }


    public Observer<T> getObserver() {
        return this;
    }

    public T get() {
        if (!this.binded) {
            return null;
        } else {
            try {
                return (T) this.field.get(this.object);
            } catch (Exception var2) {
                throw new RuntimeException(var2);
            }
        }
    }

    public void set(T value) {
        if (this.binded) {
            try {
                this.field.set(this.object, value);
                Iterator var2 = this.onChange.iterator();

                while (var2.hasNext()) {
                    Consumer<T> onChangeEvent = (Consumer) var2.next();
                    onChangeEvent.accept(value);
                }
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }

        }
    }

    public boolean bind(Class<?> _class, String filedName) {
        try {
            this.field = _class.getDeclaredField(filedName);
            this.field.setAccessible(true);
            this.fieldType = this.field.getType();
            this.binded = true;
            return true;
        } catch (NoSuchFieldException var4) {
            throw new RuntimeException(var4);
        }
    }

    public Class<?> getType() {
        return this.fieldType;
    }

    public void subscribe(Consumer<T> onChangeEvent) {
        this.onChange.add(onChangeEvent);
    }

    public void unsubscribe(Consumer<T> onChangeEvent) {
        this.onChange.remove(onChangeEvent);
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getFieldName() {
        return this.field != null ? this.field.getName() : "";
    }

    public void invoke() {
        this.set(this.get());
    }

    public Field getField() {
        return this.field;
    }
}

