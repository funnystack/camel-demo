package com.funny.admin.utils;

import com.funny.admin.model.bo.MenuTree;
import com.funny.admin.model.bo.TreeState;
import com.funny.admin.model.entity.AdminMenuEntity;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author fangli
 * @date 2017年8月9日
 */
public class MenuUtil {




    public static List<MenuTree> buildTree(List<AdminMenuEntity> menus) {
        //使用google guava 包 对以获取的分类按照父类Id进行组装
        Multimap<Long, AdminMenuEntity> treeMultimap = ArrayListMultimap.create();
        for (int i = 0; i < menus.size(); i++) {
            treeMultimap.put(Long.valueOf(menus.get(i).getParentId()), menus.get(i));
        }
        //递归调用 生成当前节点的子节点
        List<MenuTree> list = subMenu(0L, treeMultimap, 0);
        return list;
    }

    /**
     * 递归处理多级分类问题
     *
     * @param parentId 父类Id
     * @param maps     所有分类的按照父类ID组装后容器
     * @param level    分类的级别 0：根
     * @return 返回 parentId 节点的子分类节点【可能是多个】
     */
    public static List<MenuTree> subMenu(Long parentId, Multimap<Long, AdminMenuEntity> maps, int level) {
        List<MenuTree> list = new ArrayList<MenuTree>();
        Collection<AdminMenuEntity> trList = maps.get(parentId);
        for (Iterator<AdminMenuEntity> iterator = trList.iterator(); iterator.hasNext(); ) {
            AdminMenuEntity permissionTemp = iterator.next();
            MenuTree permissionTree = new MenuTree();
            permissionTree.setCurrent(permissionTemp);
            permissionTree.setLevel(level);
            permissionTree.setId(permissionTemp.getId());
            permissionTree.setText(permissionTemp.getMenuName());
            TreeState treeState = new TreeState();
            treeState.setOpened(true); // 设置树是打开状态
            permissionTree.setState(treeState);
            list.add(permissionTree);
            permissionTree.setChildren(subMenu(permissionTemp.getId(), maps, level + 1));
        }
        if (list.isEmpty()) {
            return new ArrayList<>();
        } else {
            return list;
        }
    }

    public static List<MenuTree> buildJsTree(List<MenuTree> permissions) {
        //使用google guava 包 对以获取的分类按照父类Id进行组装
        Multimap<String, MenuTree> treeMultimap = ArrayListMultimap.create();
        for (int i = 0; i < permissions.size(); i++) {
            treeMultimap.put(permissions.get(i).getPid(), permissions.get(i));
        }
        //递归调用 生成当前节点的子节点
        List<MenuTree> list = subJsMenu("0", treeMultimap, 0);
        return list;
    }

    /**
     * 递归处理多级分类问题
     *
     * @param parentId 父类Id
     * @param maps     所有分类的按照父类ID组装后容器
     * @param level    分类的级别 0：根
     * @return 返回 parentId 节点的子分类节点【可能是多个】
     */
    public static List<MenuTree> subJsMenu(String parentId, Multimap<String, MenuTree> maps, int level) {
        List<MenuTree> list = new ArrayList<MenuTree>();
        Collection<MenuTree> trList = maps.get(parentId);
        for (Iterator<MenuTree> iterator = trList.iterator(); iterator.hasNext(); ) {
            MenuTree permissionTemp = iterator.next();
            MenuTree permissionTree = new MenuTree();
            permissionTree.setIcon("fa " + permissionTemp.getIcon());
            permissionTree.setId(permissionTemp.getId());
            permissionTree.setPid(permissionTemp.getPid());
            permissionTree.setText(permissionTemp.getText());
            permissionTree.setLevel(level);
            list.add(permissionTree);
            permissionTree.setChildren(subJsMenu(permissionTemp.getId() + "", maps, level + 1));
        }
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
