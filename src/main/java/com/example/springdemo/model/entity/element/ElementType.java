package com.example.springdemo.model.entity.element;

// FIXME 需要检查一下这些 type 在 switch 模式下有没有遗漏
public enum ElementType {
    /**
     * 图表
     */
    CHART, TEXT, IMAGE, TABS, CARDS, TIPS, WEBPAGE,
    /**
     * 过滤器
     */
    FILTER
}
