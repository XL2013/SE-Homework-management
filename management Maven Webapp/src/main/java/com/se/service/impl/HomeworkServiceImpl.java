package com.se.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mockito.internal.matchers.And;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.se.dao.TeamHomeworkDao;
import com.se.pojo.Homework;
import com.se.pojo.HomeworkFile;
import com.se.pojo.TeamHomework;
import com.se.dao.HomeworkDao;
import com.se.service.HomeworkService;
import com.se.util.ExcelHelper;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{
	
	@Resource
	private HomeworkDao homeworkDao;
	
	@Resource
	private TeamHomeworkDao teamHomeworkDao;
	@Override
	public void addHomeworkInfos(List<Homework> homeworks) {
		// TODO Auto-generated method stub
		for(Homework homework :homeworks)
			homeworkDao.addHomework(homework);
	}


	@Override
	public List<TeamHomework> getTeamHomeworks(String team_id) {
		
		return  teamHomeworkDao.getTeamHomeWorks(team_id);
	}
	
	@Override
	public TeamHomework getTeamHomework(String homework_id,String team_id) {
		return teamHomeworkDao.getTeamHomeworkByID(homework_id,team_id);
	}
	
	public void addTeamHomework(String team_id,String homework_id){
		TeamHomework homework=new TeamHomework();
		homework.setHomework_id(homework_id);
		homework.setTeam_id(team_id);
		homework.setStatus(0);
		homework.setSubmit_time("");
		homework.setSubmitter("");
		homework.setCorrectInfo("");
		homework.setStudent_comment("");
		teamHomeworkDao.addTeamHomework(homework);
	}
	public void setComment(String comment,String homework_id,String team_id){
		teamHomeworkDao.setTeamHomeworkComment(homework_id, team_id, comment);
	}
	@Override
	public void updateTeamHomework(TeamHomework teamHomework) {
		teamHomeworkDao.updateTeamHomework(teamHomework);		
	}
	@Override
	public List<HomeworkFile> getTeamHomewokFiles(String homework_id, String team_id) {
		// TODO Auto-generated method stub
		return teamHomeworkDao.getTeamHomeworkFiles(homework_id, team_id);
	}

	@Override
	public void modifyHomeworkRatio(String homework_id, double ratio) {
		// TODO Auto-generated method stub
		homeworkDao.updateHomeworkRatio(homework_id, ratio);
	}
	
	public List<Homework> getHomeworksInfoByCourseID(String courseID){
		return homeworkDao.getHomeworksInfoByCourseID(courseID);
	}


	@Override
	public Homework getHomework(String homework_id) {
		return homeworkDao.getHomeworkById(homework_id);
	}


	@Override
	public Map<String, Object> getTeamHomeworkViewData() {
		// TODO Auto-generated method stub
		List<TeamHomework> teamHomeworks = teamHomeworkDao.getTeamHomeworkViewData();
		List<String> homework_names = new ArrayList<>();
		List<Double> homework_ratios = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("teamHomeworks", teamHomeworks);
		for(TeamHomework teamHomework:teamHomeworks){
			String homework_id = teamHomework.getHomework_id();
			String homework_name = teamHomeworkDao.getHomeworkName(homework_id);
			double homework_ratio = homeworkDao.getRatioByHomeworkID(homework_id);
			homework_names.add(homework_name);
			homework_ratios.add(homework_ratio);
		}
		map.put("homework_ratios", homework_ratios);
		map.put("homework_names", homework_names);
		return map;
	}


	@Override
	public Map<String, Object> getTeamHomeworkViewData(String homework_name, String submit_time, String team_id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		if(submit_time!="" && team_id!=""){
			map.put("teamHomeworks",teamHomeworkDao.getTeamHomeworkViewDataByNameTeamIDTime(homework_name, team_id, submit_time));
		}
		else if(team_id!="" && team_id=="")
			map.put("teamHomeworks",teamHomeworkDao.getTeamHomeworkViewDataByNameTeamID(homework_name, team_id));
		else if(team_id=="" && team_id!=""){
			map.put("teamHomeworks",teamHomeworkDao.getTeamHomeworkViewDataByNameTime(homework_name, submit_time));
		}
		else{
			map.put("teamHomeworks", teamHomeworkDao.getTeamHomeworkViewDataByName(homework_name));
		}
		List<String> homework_names = new ArrayList<>();
		List<Double> homework_ratios = new ArrayList<>();
		for(TeamHomework teamHomework: (List<TeamHomework>)map.get("teamHomeworks")){
			String homework_id = teamHomework.getHomework_id();
			String homeworkname = teamHomeworkDao.getHomeworkName(homework_id);
			double homework_ratio = homeworkDao.getRatioByHomeworkID(homework_id);
			homework_names.add(homeworkname);
			homework_ratios.add(homework_ratio);
		}
		map.put("homework_names", homework_names);
		map.put("homework_ratios", homework_ratios);
		return map;
	}
		
	public void saveHomeworkFile(String team_id, String homework_id, MultipartFile[] files) {
	
		HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path= request.getSession().getServletContext().getRealPath("/WEB-INF/upload/studentHomework/");
		//课程/作业/小组
		path=path+"\\"+homeworkDao.getHomeworkById(homework_id).getCourse_id()+"\\"+homework_id+"\\"+team_id+"\\";
		//保存作业
		File dirPath=new File(path);
		if(!dirPath.exists())
			dirPath.mkdirs();
		if(files.length!=0){
			for(MultipartFile file : files){
				try {					
					//如果文件不存在记录文件所属的小组及作业，以及文件的路径与名字
					if(!checkHomeworkFile(file.getOriginalFilename(), homework_id, team_id)){
						HomeworkFile homework_file=new HomeworkFile();
						homework_file.setFile_path(path);
						homework_file.setFile_name(file.getOriginalFilename());
						homework_file.setHomework_id(homework_id);
						homework_file.setTeam_id(team_id);
						teamHomeworkDao.addHomeworkFile(homework_file);
					}					
					//写入文件
					File tempFile=new File(dirPath,file.getOriginalFilename());//生成空文件					
					file.transferTo(tempFile);	//写入文件
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}				
			}
		}
		
	}


	@Override
	public boolean checkHomeworkFile(String file_name,String homework_id,String team_id) {		
		if(teamHomeworkDao.isFileExist(file_name, team_id, homework_id)==1)
			return true;
		else
			return false;
	}


	//todo :这里直接使用updateTeamHomework
	public void submitTeamHomework(String team_id, String homework_id,String student_id) {
		String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		teamHomeworkDao.setTeamHomeworkStatus(homework_id, team_id, 1);
		teamHomeworkDao.setTeamHomeworkGrade(homework_id, team_id, 0);
		teamHomeworkDao.setTeamHomeworkTime(homework_id, team_id, date);
		teamHomeworkDao.setTeamHomeworkSubmitter(homework_id, team_id, student_id);
	}


	@Override
	public void evaluateTeamHomework(String team_id, String homework_id, String correctInfo, int grade) {
		teamHomeworkDao.setTeamHomeworkCorrectInfo(homework_id, team_id, correctInfo);
		teamHomeworkDao.setTeamHomeworkGrade(homework_id, team_id, grade);
		teamHomeworkDao.setTeamHomeworkStatus(homework_id, team_id, 2);
			
	}

	
	@Override
	public List<HomeworkFile> getHomeworkFiles(String team_id, String homework_id) {
		return teamHomeworkDao.getTeamHomeworkFiles(homework_id, team_id);
	}

}
