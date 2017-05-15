package com.cw.cramer.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysDepartmentDAO;
import com.cw.cramer.auth.entity.DepartmentTreeNode;
import com.cw.cramer.auth.entity.SysDepartment;
import com.cw.cramer.auth.entity.SysDepartmentExample;
import com.cw.cramer.auth.entity.SysDepartmentExample.Criteria;
import com.cw.cramer.common.base.BaseService;
import com.cw.cramer.common.constant.CommonConstant;
import com.cw.cramer.common.constant.SequenceConstant;
import com.cw.cramer.common.constant.StatusConstant;
import com.cw.cramer.common.util.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * 部门服务类
 * @author wicks
 */
@Service(value="sysDepartmentService")
public class SysDepartmentService extends BaseService{
	
	@Autowired
	private SysDepartmentDAO sysDepartmentDAO;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 获取部门
	 * @param id
	 * @return
	 */
	public SysDepartment getSysDepartment(int id){
		SysDepartment department = sysDepartmentDAO.selectByPrimaryKey(id);
		department.setOwenrRoles(sysRoleService.getRolesByOwnerDepartment(id));
		return department;
	}
	
	/**
	 * 获取部门列表
	 * @param pageNum
	 * @param pageSize
	 * @param DepartmentName
	 * @return
	 */
	public PageInfo<SysDepartment> getSysDepartments(int pageNum, int pageSize, String DepartmentName, Integer parentId) {
		PageHelper.startPage(pageNum, pageSize);
		SysDepartmentExample example = new SysDepartmentExample();
		String sortStr = "department.sort, department.id";
		Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		if(!Strings.isNullOrEmpty(DepartmentName)){
			criteria.andNameLike(DepartmentName);
		} else if(parentId != null){
			criteria.andIdEqualTo(parentId);
			Criteria criteria2 = example.createCriteria();
			criteria2.andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
			criteria2.andParentIdEqualTo(parentId);
			example.or(criteria2);
			sortStr = "(case when department.id='"+parentId.toString()+"' then 1 else 2 end), "+sortStr;
		} 
		example.setOrderByClause(sortStr);
		List<Integer> ids = sysDepartmentDAO.selectIdByExample(example);
		PageHelper.clearPage();
		SysDepartmentExample exampleId = new SysDepartmentExample();
		if(ids.size() > 0){
			exampleId.or().andIdIn(ids);
		} else {
			return new PageInfo<SysDepartment>(new ArrayList<SysDepartment>());
		}
		exampleId.setOrderByClause(sortStr);
		List<SysDepartment> Departments = sysDepartmentDAO.selectByExample(exampleId);
		return new PageInfo<SysDepartment>(Departments);
	}
	
	/**
	 * 添加部门
	 * @param Department
	 * @return
	 */
	public boolean insert(SysDepartment Department){
		Department.setId(getNextSeq(SequenceConstant.SEQ_SYSDEPARTMENTID));
		Department.setStatus(StatusConstant.STATUS_ENABLED);
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

	/**
	 * 获取上级部门
	 * @param departmentId
	 * @return
	 */
	public SysDepartment getUpperDepartment(int departmentId){
		SysDepartment department = getSysDepartment(departmentId);
		SysDepartment upperDepartment = getSysDepartment(department.getParentId());
		return upperDepartment;
	}
	
	/**
	 * 获取部门层级
	 * @param departmentId
	 * @param sort
	 * @return
	 */
	public List<SysDepartment> getDepartmentLevels(int departmentId, String sort){
		List<SysDepartment> departments = new ArrayList<SysDepartment>();
		SysDepartment department = getSysDepartment(departmentId);
		departments.add(department);

		int parentId = department.getParentId();
		while(parentId > 0){
			SysDepartment upperDepartment = getSysDepartment(parentId);
			departments.add(upperDepartment);
			parentId = upperDepartment.getParentId();
		}
		
		//排序
		if(CommonConstant.SORT_DESC.equals(sort)){
			departments = Lists.reverse(departments);
		}
		
		return departments;
	}
	
	/**
	 * 获取部门树结构
	 * @param departmentId
	 * @param sort
	 * @return
	 */
	public List<DepartmentTreeNode> getDepartmentTrees(){
		List<DepartmentTreeNode> roots = new ArrayList<DepartmentTreeNode>();
		Map<Integer, DepartmentTreeNode> map = new HashMap<>();
		SysDepartmentExample example = new SysDepartmentExample();
		example.or().andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		example.setOrderByClause("department_parent_id, department_sort, department_id");
		List<SysDepartment> departments = sysDepartmentDAO.selectByExample(example);
		for(SysDepartment department : departments){
			DepartmentTreeNode node = new DepartmentTreeNode();
			node.setId(department.getId().toString());
			node.setName(department.getName());
			map.put(department.getId(), node);
			
			//关联父节点
			if(department.getParentId() == 0){
				roots.add(node);
				node.setOpen(true);
			} else {
				if(map.containsKey(department.getParentId())){
					if(map.get(department.getParentId()).getChildren() == null){
						map.get(department.getParentId()).setChildren(new ArrayList<>());
					}
					map.get(department.getParentId()).getChildren().add(node);
				}
			}
		}
		
		//返回根节点
		return roots;
	}
	
}
