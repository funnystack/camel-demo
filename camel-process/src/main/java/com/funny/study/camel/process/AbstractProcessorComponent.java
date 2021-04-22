package com.funny.study.camel.process;


import org.apache.camel.Exchange;

public class AbstractProcessorComponent<T> {
    T getRouteData(Exchange exchange){
        T t = (T) exchange.getIn().getBody();
        return t;
    }
}
