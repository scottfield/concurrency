package com.jackie.concurrency.concurrency.practice;

public @interface GuardedBy {
    String value() default "";
}
