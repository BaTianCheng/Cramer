package com.cw.cramer.workflow.engine.service.authorize;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfTemplate;
import com.cw.cramer.workflow.engine.util.WfConvertUtils;

/**
 * WfAuthorizeService
 * @author wicks
 */
@Service
public class WfAuthorizeService {

	@Autowired
	IdentityService identityService;
	
	@Autowired
	RepositoryService repositoryService;
	
	/**
	 * 获取用户可发起的流程模板
	 * @param creator
	 * @return
	 */
	public List<WfTemplate> getTemplates(String creator) {
		List<ProcessDefinition> processDefinitions = new ArrayList<>();
		
		processDefinitions = repositoryService
				.createProcessDefinitionQuery()
				.startableByUser(creator)
				.latestVersion()
				.list();
		return WfConvertUtils.convertWfTemplates(processDefinitions);
	}

	
}
