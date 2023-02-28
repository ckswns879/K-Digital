package com.ruby.paper.dao.data;

import java.util.Map;

public interface DataInterface {

	Map<String, Object> simulInfo();

	Map<String, Object> driveInfo();

	Map<String, Object> vehicleInfo();

	Map<String, Object> getSearch(String car_num);

	Map<String, Object> oilInfo();

}