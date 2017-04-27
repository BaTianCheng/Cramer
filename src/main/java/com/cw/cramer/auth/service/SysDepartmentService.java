package com.cw.cramer.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysDepartmentDAO;
import com.cw.cramer.auth.entity.SysDepartment;
import com.cw.cramer.auth.entity.SysDepartmentExample;
import com.cw.cramer.common.base.BaseService;
import com.cw.cramer.common.constant.SequenceConstant;
import com.cw.cramer.common.constant.StatusConstant;
import com.cw.cramer.common.util.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

/**
 * 角色服务类
 * @author wicks
 */
@Service(value="sysDepartmentService")
public class SysDepartmentService extends BaseService{
	
	@Autowired
	private SysDepartmentDAO sysDepartmentDAO;
	
	/**
	 * 获取部门
	 * @param id
	 * @return
	 */
	public SysDepartment getSysDepartment(int id){
		return sysDepartmentDAO.selectByPrimaryKey(id);
	}
	
	/**
	 * 获取部门列表
	 * @param pageNum
	 * @param pageSize
	 * @param DepartmentName
	 * @return
	 */
	public PageInfo<SysDepartment> getSysDepartments(int pageNum, int pageSize, String DepartmentName) {
		PageHelper.startPage(pageNum, pageSize);
		SysDepartmentExample example = new SysDepartmentExample();
		if(!Strings.isNullOrEmpty(DepartmentName)){
			example.or().andNameEqualTo(DepartmentName).andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		} else {
			example.or().andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		}
		example.setOrderByClause("department_sort");
		List<SysDepartment> Departments = sysDepartmentDAO.selectByExample(example);
		return new PageInfo<SysDepartment>(Departments);
	}
	
	/**
	 * 添加部门
	 * @param Department
	 * @return
	 */
	public boolean insert(SysDepartment Department){
		Department.setId(getNextSeq(SequenceConstant.SEQ_SYSDEPARTMENTID));
		return sysDepartmentDAO.insert(Department)>0 ? true : false;
	}
	
	/**
	 * 修改部门
	 * @param Department
	 * @return
	 */
	public boolean update(SysDepartment Department){
		Department.setUpdateBy(this.getCurrentUser().getId());
		Department.setUpdateTime(DateTimeUtils.getCurrentTime());
		return sysDepartmentDAO.updateByPrimaryKey(Department)>0 ? true : false;
	}
	
	/**
	 * 更新部门基本信息
	 * @param editedUser
	 * @return
	 */
	public boolean updateInfo(SysDepartment editedDepartment){
		SysDepartment Department = getSysDepartment(editedDepartment.getId());
		Department.setName(editedDepartment.getName());
		Department.setCode(editedDepartment.getCode());
		Department.setStatus(editedDepartment.getStatus());
		Department.setRemarks(editedDepartment.getRemarks());
		return update(Department);
	}
	
	/**
	 * 删除部门(更新标志位)
	 * @param userId
	 * @return
	 */
	public boolean delete(int id){
		SysDepartment Department = getSysDepartment(id);
		if(Department != null){
			Department.setStatus(StatusConstant.STATUS_DELETED);
			return sysDepartmentDAO.updateByPrimaryKey(Department)>0 ? true : false;
		} else {
			return false;
		}
	}

}
