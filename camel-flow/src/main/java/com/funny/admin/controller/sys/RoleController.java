package com.funny.admin.controller.sys;

import com.funny.admin.controller.BaseController;
import com.funny.admin.model.bo.MenuTree;
import com.funny.admin.model.entity.AdminMenuEntity;
import com.funny.admin.model.entity.AdminRoleEntity;
import com.funny.admin.service.AdminMenuService;
import com.funny.admin.service.AdminRoleService;
import com.funny.admin.utils.MenuUtil;
import com.funny.combo.core.result.CommonResult;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangli
 * @version 2015-05-21 23:16:42
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Resource
    private AdminRoleService roleService;

    @Resource
    private AdminMenuService menuService;

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @RequestMapping("/list")
    public String index() {

        return "role/role_list";
    }

    @RequestMapping(value = "/pageList")
    public ModelAndView search(AdminRoleEntity role, Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView("role/role_page");
        PageInfo<AdminRoleEntity> pageInfo = roleService.findPageListByCondition(role);
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<?> load(Long roleId) {
        CommonResult jsonResult = CommonResult.fail("");
        try {
            AdminRoleEntity adminRoleEntity = roleService.findEntityById(roleId);
            jsonResult.successBody(adminRoleEntity);
        } catch (Exception e) {

            logger.error("查询角色异常roleId={}", roleId, e.getMessage());
            return CommonResult.fail("查询角色异常" + e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public CommonResult add(AdminRoleEntity role) {
        CommonResult jsonResult = CommonResult.fail("");
        try {
            roleService.insertSelective(role);
            jsonResult.successBody(null);
        } catch (Exception e) {
            jsonResult.fail("新增角色异常" + e.getMessage());
            logger.error("新增角色异常：", e.getMessage());
        }
        return jsonResult;
    }


    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteRole(@PathVariable Long id) {
        CommonResult jsonResult = CommonResult.fail("");
        try {
            roleService.deleteRole(id);
            jsonResult.successBody(null);
        } catch (Exception e) {
            jsonResult.setMessage("新增角色异常" + e.getMessage());
            logger.error("新增角色异常：", e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public CommonResult menu(Long roleId) {
        CommonResult result = CommonResult.fail("");

        List<AdminMenuEntity> allMenuList = menuService.findValidMenuList();
        List<MenuTree> allTree = MenuUtil.buildTree(allMenuList);

        List<AdminMenuEntity> roleMenuList = menuService.findMenuByRoleId(roleId);
        List<MenuTree> selectTreeList = roleMenuList.stream().map(t -> getMenuTree(t)).collect(Collectors.toList());

        Map<String,Object> maps= Maps.newHashMap();
        maps.put("all",allTree);
        maps.put("selected",selectTreeList);

        result.successBody(maps);
        return result;
    }


    private MenuTree getMenuTree(AdminMenuEntity adminMenuEntity) {
        MenuTree menuTree = new MenuTree();
        menuTree.setId(adminMenuEntity.getId());
        menuTree.setText(adminMenuEntity.getMenuName());
        return menuTree;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/alloc", method = RequestMethod.POST)
    public CommonResult menuAlloc(Long roleId, Long[] menus) {
        CommonResult jsonResult = CommonResult.fail("");
        String updateBy = getCurrentUser().getUserName();
        try {
            roleService.allocRoleMenu(roleId, menus, updateBy);
            jsonResult.successBody(null);
        } catch (Exception e) {
            jsonResult.setMessage("角色分配权限异常" + e.getMessage());
            logger.error("角色分配权限异常：roleId = {},menus{}", roleId, menus.toString(), e.getMessage());
            e.printStackTrace();
        }

        return jsonResult;
    }
}