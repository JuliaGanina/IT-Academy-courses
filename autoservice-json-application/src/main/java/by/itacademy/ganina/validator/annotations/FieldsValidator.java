package by.itacademy.ganina.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValidator {

    String pattern() default "";

    String message() default "";

    boolean isValidField() default false;
}
