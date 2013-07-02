/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

/**
 *
 * @author Ryan
 */
public abstract class ObservableObserver<T> extends AbstractObservable
        implements Observer {

    public ObservableObserver(T value) {
        super(value);
    }
}
