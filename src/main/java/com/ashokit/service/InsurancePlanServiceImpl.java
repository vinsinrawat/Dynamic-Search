package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ashokit.entity.InsurancePlan;
import com.ashokit.repository.InsurancePlanRepository;
import com.ashokit.request.SearchRequest;
import com.ashokit.response.PlanResponse;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService{

	@Autowired
	private InsurancePlanRepository repo;
	

	@Override
	public List<String> getUniquePlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		return repo.getPlanStatuses();
	}

	@Override
	public List<PlanResponse> searchPlan(SearchRequest req) {
		
		InsurancePlan entity = new InsurancePlan();
		
		if(null!=req && req.getPlanName()!=null && !req.getPlanName().equals("")) {
			entity.setPlanName(req.getPlanName());
		}
		
		if(null!=req && req.getPlanStatus()!=null && !req.getPlanStatus().equals("")) {
			entity.setPlanStatus(req.getPlanStatus());
		}
		
		Example<InsurancePlan> example = Example.of(entity);
		List<InsurancePlan> findAll= repo.findAll(example);
		
		List<PlanResponse> plansData = new ArrayList<>();
		for(InsurancePlan ip : findAll) {
			PlanResponse plan = new PlanResponse();
			BeanUtils.copyProperties(ip, plan);
			plansData.add(plan);
		}
		
		return plansData;
	}
	
}
