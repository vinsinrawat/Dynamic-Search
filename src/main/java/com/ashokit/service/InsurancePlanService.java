package com.ashokit.service;

import java.util.List;

import com.ashokit.request.SearchRequest;
import com.ashokit.response.PlanResponse;

public interface InsurancePlanService {

	public List<PlanResponse> searchPlan(SearchRequest searchRequest);
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatuses();
	
}
