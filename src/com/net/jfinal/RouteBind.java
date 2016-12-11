package com.net.jfinal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Route bindingControllerannotation<br>
 * staycontrollerUse on
 * 
 * huilet 2013-3-20
 * @author yuanc
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RouteBind {
	/**Corresponding path name already/Start*/
	String path() default "/";
	/**View location directory*/
	String viewPath() default "";
}