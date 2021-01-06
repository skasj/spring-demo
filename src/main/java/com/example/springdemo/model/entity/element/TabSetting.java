package com.example.springdemo.model.entity.element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author caojingwei
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TabSetting implements ElementSetting {

    private List<TabEntity> tabList;

    @JsonIgnore
    public List<String> getTabEntityIdList() {
        if (CollectionUtils.isEmpty(tabList)) {
            return new ArrayList<>();
        }
        return tabList.stream()
            .map(TabEntity::getTabId)
            .collect(Collectors.toList());
    }
}
