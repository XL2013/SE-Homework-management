package com.se.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.RollCallDao;
import com.se.pojo.RollCallSetting;
import com.se.service.RollCallService;

@Service("rollCallService")
public class RollCallServiceImpl implements RollCallService{

	@Resource
	RollCallDao rollCallDao;
	@Override
	public int getCourseRollCallTotals(String course_id) {
		// TODO Auto-generated method stub
		if(rollCallDao.getRollCallSetting(course_id)==null)
			return 0;
		else {
			return rollCallDao.getRollCallSetting(course_id).getTotal();
		}
	}
	public int getStudentRollCallTotals(String course_id,String student_id){
		return  rollCallDao.getStudentRollCallTotals(course_id, student_id);
	}

	@Override
	public int getAbsenceTimes(String student_id, String course_id) {
		// TODO Auto-generated method stub
		if(rollCallDao.getRollCallSetting(course_id)==null)
			return 0;
		//总的点到次数-已被点到的次数
		else {
			return rollCallDao.getStudentRollCallTotals(course_id, student_id)-rollCallDao.getStudentRollCallTimes(course_id, student_id);
		}
	}
	@Override
	public void addRollCallSetting(String course_id, int total) {
		RollCallSetting rollCallSetting=new RollCallSetting();
		rollCallSetting.setCourse_id(course_id);
		rollCallSetting.setTotal(total);
		rollCallDao.addRollCallSetting(rollCallSetting);
		
	}

}
