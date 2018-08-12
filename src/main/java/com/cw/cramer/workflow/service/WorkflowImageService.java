package com.cw.cramer.workflow.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.WorkFlowAPI;
import com.cw.cramer.workflow.engine.WorkFlowSystemManagerAPI;
import com.cw.cramer.workflow.engine.entity.WfTemplate;

/**
 * 类 名: WorkflowImageService
 * 描 述: 工作流图像服务类
 * 作 者: wicks
 * 创 建：2018年3月22日
 * 版 本：1.0
 * 
 * 历 史: 1.0 wicks 2018年3月22日 创建
 */
@Service(value = "workflowImageService")
public class WorkflowImageService {
	
	private final Logger logger = LoggerFactory.getLogger(WorkflowImageService.class);

	@Autowired
	WorkFlowSystemManagerAPI workFlowSystemManagerAPI;
	
	@Autowired
	WorkFlowAPI workFlowAPI;
	
	/**
	 * 描 述：生成工作流图像
	 * 作 者：wicks
	 * 历 史: 1.0 wicks 2018年3月22日 创建 
	 * @param templateKey
	 * @return
	 */
	public void generateImage(String path) {
		path = path + "/files/workflow/images/";
		List<WfTemplate> wfTemplates = workFlowAPI.getTemplates();
		
		for(WfTemplate wfTemplate : wfTemplates) {
			generateImage(wfTemplate.getTemplateKey(), path);
		}
		
	}
	
	/**
	 * 描 述：生成工作流图像
	 * 作 者：wicks
	 * 历 史: 1.0 wicks 2018年3月22日 创建 
	 * @param templateKey
	 * @return
	 */
	public String generateImage(String templateKey, String path) {
		InputStream inputStream = workFlowSystemManagerAPI.getTemplateImage(templateKey);
		String fileName = path+templateKey+".png";
		File file = new File(fileName);
		
		try{
			File fileParent = file.getParentFile();  
			if(!fileParent.exists()){  
			    fileParent.mkdirs();  
			}
			
			int index;
		    byte[] bytes = new byte[1024];  
		    FileOutputStream downloadFile = new FileOutputStream(file);  
		    while ((index = inputStream.read(bytes)) != -1) {  
		        downloadFile.write(bytes, 0, index);  
		        downloadFile.flush();  
		    }  
		    downloadFile.close();  
		    inputStream.close();  
		}
		catch(Exception ex){
			logger.error("生成工作流图像失败:"+fileName);
		}
		
		return fileName;
	}
	
}
