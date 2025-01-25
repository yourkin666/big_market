package cn.yourkin666.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityStateVO {

    create("create", "创建"),
    open("open", "开启");

    private final String code;
    private final String desc;
}
