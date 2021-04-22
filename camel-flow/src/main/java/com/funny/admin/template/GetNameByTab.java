package com.funny.admin.template;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 根据版块标识查询版块名称
 * @author fangli
 */
public class GetNameByTab implements TemplateMethodModelEx {

    //private SectionService sectionService = SpringContextHolder.getBean(SectionService.class);

	@Override
	@SuppressWarnings("rawtypes")
    public Object exec(List list) throws TemplateModelException {
//        if (list == null || list.size() != 1) {
//            throw new TemplateModelException("Wrong arguments");
//        }
//        String tab = list.get(0).toString();
//        Section section = sectionService.findByTab(tab);
//        if (section != null) {
//            return section.getName();
//        }
        return null;
    }
}
