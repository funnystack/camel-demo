package com.funny.admin.controller.sys;

import com.funny.admin.model.bo.AdminMenuTree;
import com.funny.admin.model.bo.MenuTree;
import com.funny.admin.model.bo.TreeState;
import com.funny.admin.model.entity.AdminMenuEntity;
import com.funny.admin.service.AdminMenuService;
import com.funny.admin.utils.MenuUtil;
import com.funny.combo.core.result.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private AdminMenuService adminMenuService;


    @ResponseBody
    @RequestMapping("/tree")
    public CommonResult<MenuTree> tree() {
		CommonResult result = CommonResult.fail("");
        MenuTree ret = new MenuTree();
        try {

            List<MenuTree> menuTree = MenuUtil.buildTree(adminMenuService.getList(new HashMap<>()));
            ret.setChildren(menuTree);
            ret.setId(0L);
            TreeState ts = new TreeState();
            ts.setOpened(true);
            ret.setState(ts);
            ret.setText("菜单树");
            result.successBody(ret);
        } catch (Exception e) {
            logger.error("获取所有菜单失败：", e.getMessage());
        }
        return result;

    }

	@ResponseBody
    @RequestMapping("/menuInfo")
	public CommonResult <AdminMenuTree> nodeInfo(Long id) {
		CommonResult result = CommonResult.fail("");
		try {
			AdminMenuTree ret = new AdminMenuTree();
            AdminMenuEntity root = new AdminMenuEntity();
			if(id == 0L){
                root.setId(0L);
                root.setMenuName("菜单树");
            }else{
                root = adminMenuService.selectById(id);
            }
			if(root != null){
				BeanUtils.copyProperties(root, ret);
				List<AdminMenuEntity> leafList = adminMenuService.getLeafList(id);
				List<AdminMenuTree> leafNodeList = new ArrayList<>();
				for(AdminMenuEntity tmp : leafList){
					AdminMenuTree leaf = new AdminMenuTree();
					BeanUtils.copyProperties(tmp, leaf);
					leafNodeList.add(leaf);
				}
				ret.setLeafList(leafNodeList);
			}

			result.successBody(ret);
		} catch (Exception e) {
			logger.error("获取子菜单失败：", e.getMessage());
		}
		return result;
	}


	@ResponseBody
	@RequestMapping("/addMenu")
	public CommonResult addNode(AdminMenuEntity node) {

		if(node.getParentId() == null){
			return CommonResult.fail("请选择父级菜单");
		}
		if(StringUtils.isBlank(node.getMenuName())){
			return CommonResult.fail("请填写菜单名称");
		}
		if(StringUtils.isBlank(node.getMenuCode())){
			return CommonResult.fail("请填写菜单编码");
		}
		CommonResult result = CommonResult.fail("");
		try{
			int insertCount = adminMenuService.insertSelective(node);
			result.setMessage(insertCount>0? "添加成功":"添加失败");
			result.setCode(insertCount>0? CommonResult.CODE_SUCCESS: CommonResult.CODE_FAILURE);
		}catch (Exception e){
			logger.error("添加菜单失败", e);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteMenu")
	public CommonResult deleteNode(Long id) {

		if(id == null){
			return CommonResult.fail("请选择要删除的节点");
		}
		CommonResult result = CommonResult.fail("");
		try{
			int insertCount = adminMenuService.deleteNode(id, true);
			result.setMessage(insertCount>0? "删除成功":"删除失败");
			result.setCode(insertCount>0? CommonResult.CODE_SUCCESS: CommonResult.CODE_FAILURE);
		}catch (Exception e){
			logger.error("删除菜单失败", e);
		}
		return result;
	}

    @ResponseBody
    @RequestMapping("/updateMenu")
    public CommonResult updateMenu(AdminMenuEntity entity) {
        if(entity == null || entity.getId() == null){
            return CommonResult.fail("请选择要更新的菜单");
        }
		CommonResult result = CommonResult.fail("");
        try {
            int updateCount = adminMenuService.updateSelective(entity);
            result.setMessage(updateCount > 0 ? "更新成功" : "更新失败");
            result.setCode(updateCount > 0 ? CommonResult.CODE_SUCCESS : CommonResult.CODE_FAILURE);
        } catch (Exception e) {
            logger.error("更新菜单失败", e);
        }
        return result;
    }


	@RequestMapping("/index")
	public String index() {
		return "menu/index";
	}

}
