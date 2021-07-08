package com.example.springdemo.model.entity.element;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author caojingwei
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportElement {


    @JsonTypeInfo(use = Id.NAME, include = As.EXTERNAL_PROPERTY, property = "type", visible = true, defaultImpl = JsonSetting.class)
    private ElementSetting setting;

    private ElementType type;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ImageSetting imageSetting = new ImageSetting("123");
        ReportElement reportElement = new ReportElement(imageSetting,ElementType.IMAGE);
        System.out.println(objectMapper.writeValueAsString(reportElement));

        String image = "{\"type\":\"IMAGE\",\"setting\":{\"fileId\":\"123\"}}";
        String tab = "{\"type\":\"TABS\",\"setting\":{\"tabList\":[{\"tabId\":\"123\",\"tabName\":\"name\"}]}}";
        String other = "{\"type\":\"CHART\",\"setting\":{\"fileId\":\"123\",\"tabList\":[{\"tabId\":\"123\",\"tabName\":\"name\"}]}}";
        ReportElement imageObject = objectMapper.readValue(image, ReportElement.class);
        System.out.println(objectMapper.writeValueAsString(imageObject));
        ReportElement tabObject = objectMapper.readValue(tab, ReportElement.class);
        System.out.println(objectMapper.writeValueAsString(tabObject));
        ReportElement otherObject = objectMapper.readValue(other, ReportElement.class);
        System.out.println(objectMapper.writeValueAsString(otherObject));
    }
}
