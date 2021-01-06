package com.example.springdemo.model.entity.element;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * date: 2021/1/6 10:57
 *
 * @author dongyu.ye
 * @since 3.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type", visible = true, defaultImpl = JsonSetting.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ImageSetting.class, name = "IMAGE"),
    @JsonSubTypes.Type(value = TabSetting.class, name = "TABS")
})
public interface ElementSetting {

}
