package com.cw.cramer.workflow.engine.service.define;

import java.io.File;
import java.io.InputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.constant.DeploymentConstants;

/**
 * 工作流部署服务类
 * @author wicks
 */
@Service(value = "wfDeploymentService")
public class WfDeploymentService {
	
	public static Logger logger = LoggerFactory.getLogger(WfDeploymentService.class);
	
	@Autowired
	RepositoryService repositoryService;
	
	/**
	 * 部署工作流模板
	 */
	public void deploy() {
		
		// 获取目录下所有部署资源文件
		File fileDirectory = new File(this.getClass().getResource("/").getPath() + DeploymentConstants.BPMN_PATH);
		logger.info("【工作流部署】进行工作流部署，模板目录："+fileDirectory.getPath());
		
		// 遍历目录进行资源加载并部署
		if(fileDirectory.isDirectory()) {
			File[] files = fileDirectory.listFiles();
			DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
			
			// 去除重复部署，只部署最新版
			deploymentBuilder.enableDuplicateFiltering();
			
			if(files != null){
				for(File file : files){
					if(file.getName().endsWith(".bpmn")) {
						deploymentBuilder.addClasspathResource(DeploymentConstants.BPMN_PATH + file.getName());
						logger.info("【工作流部署】读取工作流模板定义："+file.getName());
					}
				}
				deploymentBuilder.deploy();
				logger.info("【工作流部署】完成工作流的部署");
			} else {
				logger.error("【工作流部署】无任何工作流模板文件，部署失败");
			}
		} else {
			logger.error("【工作流部署】工作流模板目录无效，部署失败");
		}
	}
	
	/**
	 * 部署工作流模板
	 * @param templateKey
	 * @param inputStream
	 */
	public void deploy(String fileName) {
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
			
		// 去除重复部署，只部署最新版
		deploymentBuilder.enableDuplicateFiltering();
		
		deploymentBuilder.addClasspathResource(DeploymentConstants.BPMN_PATH + fileName);
		logger.info("【工作流部署】读取工作流模板定义："+fileName);
		deploymentBuilder.deploy();
		logger.info("【工作流部署】完成工作流的部署");
	}
	
	/**
	 * 部署工作流模板
	 * @param templateKey
	 * @param inputStream
	 */
	public void deploy(String templateKey, InputStream inputStream) {
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
			
		// 去除重复部署，只部署最新版
		deploymentBuilder.enableDuplicateFiltering();
		
		deploymentBuilder.addInputStream(templateKey, inputStream);
		deploymentBuilder.deploy();
		logger.info("【工作流部署】完成工作流-"+templateKey+"的部署");
	}

}
