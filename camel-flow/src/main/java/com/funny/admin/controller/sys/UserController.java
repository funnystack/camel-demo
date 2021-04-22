package com.funny.admin.controller.sys;

import com.alibaba.fastjson.JSON;
import com.funny.admin.common.utils.CachedBeanCopier;
import com.funny.admin.controller.BaseController;
import com.funny.admin.model.bo.AdminUser;
import com.funny.admin.model.entity.AdminDepartmentEntity;
import com.funny.admin.model.entity.AdminRoleEntity;
import com.funny.admin.model.entity.AdminUserEntity;
import com.funny.admin.service.AdminDepartmentService;
import com.funny.admin.service.AdminRoleService;
import com.funny.admin.service.AdminUserService;
import com.funny.combo.core.result.CommonResult;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fangli
 * @since JDK 1.8
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private AdminUserService userService;

    @Autowired
    private AdminRoleService roleService;

    @Autowired
    private AdminDepartmentService adminDepartmentService;

    @RequestMapping("list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("user/user_list");

        List<AdminDepartmentEntity> departmentEntityList = adminDepartmentService.findValidDepartment();

        modelAndView.addObject("departmentEntityList", departmentEntityList);


        List<AdminRoleEntity> allRoleList = roleService.getAllValidRoles();
        modelAndView.addObject("allRoles", allRoleList);

        return modelAndView;
    }

    @RequestMapping("pageList")
    public ModelAndView search(AdminUserEntity userEntity) {
        ModelAndView modelAndView = new ModelAndView("user/user_page");
        PageInfo<AdminUserEntity> pageInfo = userService.findPageListByCondition(userEntity);
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public CommonResult<AdminUser> findUserById(@PathVariable("id") Long id) {
        CommonResult result = CommonResult.fail("");
        try {
            AdminUser adminUser = new AdminUser();
            AdminUserEntity userEntity = userService.findEntityById(id);
            CachedBeanCopier.copy(userEntity, adminUser);
            List<AdminRoleEntity> roleEntityList = roleService.getRoleByUserId(id);
            String ids = Joiner.on(",").join(roleEntityList.stream().map(AdminRoleEntity::getId).collect(Collectors.toList()));
            adminUser.setRoleIds(ids);
            result.successBody(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户异常,userId = {}", id, e);
            result.setMessage("新增用户失败");
        }
        return result;
    }

    //    @LogInfo(logType = LogType.ADD, operationContent = "新增用户")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addUser(AdminUser adminUser) {
        CommonResult result = checkUser(adminUser);
        if (result.getCode().intValue()!=0) {
            return result;
        }
        try {
            AdminDepartmentEntity adminDepartmentEntity = adminDepartmentService.findEntityById(adminUser.getDepartmentId());
            if (adminDepartmentEntity == null) {
                result.setMessage("部门不存在!");
                return result;
            }
            adminUser.setDepartmentName(adminDepartmentEntity.getDepartName());
            adminUser.setCreateBy(getCurrentUser().getUserName());
            adminUser.setUserPwd(DigestUtils.md5DigestAsHex(adminUser.getUserName().getBytes()) + DigestUtils.md5DigestAsHex("123456".getBytes()));
            adminUser.setUserStatus(1);
            userService.addUserRoles(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增用户异常,userEntity = {}", JSON.toJSONString(adminUser), e);
            result.setMessage("新增用户失败");
        }
        return result;
    }

    //    @LogInfo(logType = LogType.UPDATE, operationContent = "账户信息修改")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult updateUser(AdminUser adminUser) {
        CommonResult result = checkUser(adminUser);
        if (result.getCode().intValue() != 0  ) {
            return result;
        }
        if (adminUser.getId() == null) {
            result.setMessage("未找到id");
            return result;
        }
        try {
            AdminDepartmentEntity adminDepartmentEntity = adminDepartmentService.findEntityById(adminUser.getDepartmentId());
            if (adminDepartmentEntity == null) {
                result.setMessage("部门不存在!");
                return result;
            }

            List<AdminRoleEntity> roleEntityList = roleService.getRoleByUserId(adminUser.getId());
            String ids = Joiner.on(",").join(roleEntityList.stream().map(AdminRoleEntity::getId).collect(Collectors.toList()));
            boolean updateRole = !ids.equals(adminUser.getRoleIds());
            adminUser.setDepartmentName(adminDepartmentEntity.getDepartName());
            adminUser.setUpdateBy(getCurrentUser().getUserName());
            userService.updateUserRoles(adminUser, updateRole);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改用户异常,userEntity = {}", JSON.toJSONString(adminUser), e);
            result.setMessage("修改用户失败");
        }
        return result;
    }


    private CommonResult checkUser(AdminUser adminUser) {
        CommonResult jsonResult = CommonResult.fail("");
        if (StringUtils.isBlank(adminUser.getUserName())) {
            jsonResult.setMessage("请输入用户名");
            return jsonResult;
        }
        if (StringUtils.isBlank(adminUser.getRealName())) {
            jsonResult.setMessage("请输入姓名");
            return jsonResult;
        }
        if (StringUtils.isBlank(adminUser.getEmail())) {
            jsonResult.setMessage("请输入邮件");
            return jsonResult;
        }
        if (StringUtils.isBlank(adminUser.getMobile())) {
            jsonResult.setMessage("请输入手机号");
            return jsonResult;
        }

        if (StringUtils.isBlank(adminUser.getWorkNo())) {
            jsonResult.setMessage("请输入工号");
            return jsonResult;
        }
        if (adminUser.getDepartmentId() == null) {
            jsonResult.setMessage("请输入部门");
            return jsonResult;
        }
        if (StringUtils.isBlank(adminUser.getRoleIds())) {
            jsonResult.setMessage("请选择角色");
            return jsonResult;
        }
        jsonResult.successBody(null);
        return jsonResult;

    }


    //    @LogInfo(logType = LogType.UPDATE, operationContent = "启用用户")
    @RequestMapping(value = "/unlock/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult unlockUser(@PathVariable("id") Long id) {
        CommonResult result = CommonResult.fail("");
        try {
            AdminUserEntity userEntity = userService.findEntityById(id);
            if (null == userEntity) {
//                result.error("用户不存在!");
                return result;
            }
            AdminUserEntity updateUser = new AdminUserEntity();
            updateUser.setId(id);
//            updateUser.setUpdateBy(getCurrentUser().getId());
            updateUser.setUserStatus(1);
            userService.updateSelectiveById(updateUser);
        } catch (Exception e) {
            logger.error("启用用户异常,userId = {}", id, e);
//            result.error("启用用户失败");
        }
        return result;
    }


    //    @LogInfo(logType = LogType.UPDATE, operationContent = "禁用用户")
    @RequestMapping(value = "/lock/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult lockUser(@PathVariable("id") Long id) {
        CommonResult result = CommonResult.fail("");
        try {
            AdminUserEntity userEntity = userService.findEntityById(id);
            if (null == userEntity) {
//                result.error("用户不存在!");
                return result;
            }
            AdminUserEntity updateUser = new AdminUserEntity();
            updateUser.setId(id);
//            updateUser.(getCurrentUser().getId());
            updateUser.setUserStatus(0);
            userService.updateSelectiveById(updateUser);
        } catch (Exception e) {
            logger.error("禁用用户异常,userId = {}", id, e);
//            result.error("禁用用户失败");
        }
        return result;
    }


    @RequestMapping(value = "/roleList/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult getRoleList(@PathVariable("userId") Long userId) {
        CommonResult result = CommonResult.fail("");
        try {
            List<AdminRoleEntity> allRoleList = roleService.getAllValidRoles();

            List<AdminRoleEntity> userRoleList = roleService.getRoleByUserId(userId);


        } catch (Exception e) {
            logger.error("获取角色列表异常,userId = {}", userId, e);
        }
        return result;
    }
}