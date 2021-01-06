package com.example.springdemo.model.entity.element;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author caojingwei
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageSetting implements ElementSetting {

    private String fileId;
}
