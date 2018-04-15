package org.aming.dy.annotation;

import org.aming.dy.enums.Access;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author aming
 * @create 2018-04-03 8:35
 **/
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    /**
     * @return
     */
    String name() default "main";

    Access access() default Access.RO;
}
