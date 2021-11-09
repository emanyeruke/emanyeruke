package zw.co.mynhaka.policyholderservice.service.util;

import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Mapping(target = "version", ignore = true)
@Mapping(target = "createdBy", ignore = true)
@Mapping(target = "createdDate", ignore = true)
@Mapping(target = "lastModifiedBy", ignore = true)
@Mapping(target = "lastModifiedDate", ignore = true)
public @interface IgnoreAuditing { }
