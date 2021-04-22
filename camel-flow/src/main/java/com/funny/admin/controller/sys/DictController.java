package com.funny.admin.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author fangli
 * @version 
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/dict")
public class DictController {
	
//	@Resource
//	private DictService dictService;
//
//	@RequestMapping("/list")
//	public String list() {
//		return ConfigUtils.getAdminPath() + "/dict/list";
//	}
//
//	@RequestMapping("/load")
//	@ResponseBody
//	public ResponseTable<List<Dict>> load(Dict dict, Pagination pagination){
//		List<Dict> rows = dictService.listPage(pagination, dict);
//		ResponseTable<List<Dict>> result = new ResponseTable<List<Dict>>();
//		result.setRows(rows);
//		result.setTotal(pagination.getTotal());
//		return result;
//	}
//	
//	@RequiresPermissions("sys:dict:add")
//	@RequestMapping("/add")
//	public String add(Dict dict,Model model) {
//		try {
//			if(null!=dict&&dict.getId()>0){
//				dict = dictService.selectDictById(dict.getId());
//			}
//			model.addAttribute("dict", dict);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ConfigUtils.getAdminPath() + "/dict/add";
//	}
//
//	@RequestMapping("/save")
//	@ResponseBody
//	public ResponseRest<Dict> save(Dict dict) {
//		ResponseRest<Dict> result = new ResponseRest<Dict>();
//		result.setStatus(0);
//		try {
//			Integer row = 0;
//			if (dict.getId() != null && dict.getId() != 0) {
//				row = dictService.updateDictById(dict);
//			} else {
//				row = dictService.saveDict(dict);
//			}
//			if (row>0) {
//				result.setStatus(1);
//				result.setMsg("保存成功");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setMsg(Const.DEFAULT_ERROR);
//		}
//		return result;
//	}
}
