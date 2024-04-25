package com.mlv.learn.common;

import com.mlv.learn.vo.OrgTreeVO;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class TreeNode {
    private OrgTreeVO vo;
    public TreeNode left;  //左节点
    public TreeNode right;  //右节点
    public TreeNode(OrgTreeVO vo) {  //提供一个结点的构造方法  new一个新结点的时候是不知道左右子树的,所以不用构造
        this.vo = vo;
    }
}
