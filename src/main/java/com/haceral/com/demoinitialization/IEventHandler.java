package com.haceral.com.demoinitialization;

public interface IEventHandler<E> {

    E getEvent();

    void handle(E event) throws Exception;
}
