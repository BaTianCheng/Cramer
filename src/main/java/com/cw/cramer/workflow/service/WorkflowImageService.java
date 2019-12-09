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
 * WorkflowImageService
 * @author wicks
 */
@Service(value = "workflowImageService")
public class WorkflowImageService {
	
	private final Logger logger = LoggerFactory.getLogger(WorkflowImageService.class);

	@Autowired
	WorkFlowSystemManagerAPI workFlowSystemManagerAPI;
	
	@Autowired
	WorkFlowAPI workFlowAPI;
	
	/**
	 * 文件路径
	 */
	private static final String fileURL = "/files/workflow/images/";
	
	/**
	 * 生成工作流图像
	 * @throws Exception 
	 */
	public void generateImage(String path) throws Exception {
		path = path + fileURL;
		List<WfTemplate> wfTemplates = workFlowAPI.getTemplates();
		
		for(WfTemplate wfTemplate : wfTemplates) {
			generateImage(wfTemplate.getTemplateKey(), path);
		}
		
	}
	
	/**
	 * 生成工作流图像
	 * @throws Exception 
	 */
	public String generateImage(String templateKey, String path) throws Exception {
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
			logger.error("生成工作流图像失败:"+fileName, ex);
			throw ex;
		}
		
		return fileName;
	}
	
}
