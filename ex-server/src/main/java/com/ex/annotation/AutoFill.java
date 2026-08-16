package com.ex.annotation;

import com.ex.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识某个方法需要进行公共字段字段填充
 * 公共字段所处的参数对象，位置必须是方法的第一个
 * 对象必须包含以下方法
 * setCreateTime(...)
 * setUpdateTime(...)
 * setCreateUser(...)
 * setUpdateUser(...)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value();
}
