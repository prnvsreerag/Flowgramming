/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.List;

/**
 *
 * @author Ryan
 */
public class AbstractObservable<T> implements Observable<T> {

    private String name;
    private List<Observer> observers;
    private T value;

    public AbstractObservable(String name, T value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public void addObserver(Observer observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    @Override
    public void update() {
        synchronized (observers) {
            for (Observer observer : observers) {
                observer.update(name, value);
            }
        }
    }

    public void setValue(T value) {
        this.value = value;
        update();
    }
}
