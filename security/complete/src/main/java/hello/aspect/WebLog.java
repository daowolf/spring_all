package hello.aspect;

import java.lang.annotation.*;

/**
 * @Auther: BigDaddy
 * @Date: 2019/9/16 23:12
 * @Description: 自定义log注解
 */
@Retention(RetentionPolicy.RUNTIME)//什么时候使用该注解，我们定义为运行时
@Target({ElementType.METHOD})//注解用于什么地方，我们定义为作用于方法上
@Documented//注解是否包含在JavaDoc中;
public @interface WebLog {//注解名为WebLog
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";//定义一个属性,默认为一个空串
}
