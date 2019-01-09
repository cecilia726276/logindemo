package com.chxyz.demo.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GroupDO {

    // 小组id
    private Integer groupId;

    // 组员id
    private List<Integer> groupMembers;



}
