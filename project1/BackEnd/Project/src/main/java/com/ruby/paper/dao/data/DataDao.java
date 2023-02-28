package com.ruby.paper.dao.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ruby.paper.domain.DrivingVO;
import com.ruby.paper.domain.OilVO;
import com.ruby.paper.domain.SimulVO;
import com.ruby.paper.domain.VehicleVO;

@Repository
public class DataDao implements DataInterface {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DataDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		System.out.println("JDBCTemplate이용");
	}
	
	@Override
	public Map<String, Object> simulInfo() {
		String sqlString = "select * from Simul";
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		try {
			List<SimulVO> list = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<SimulVO>(SimulVO.class));
			ret.put("data", list);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}

	@Override
	public Map<String, Object> driveInfo() {
		String sqlString = "select car_num, date, dsr,rac, sds, durs from Driving_Unit_Data";
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		try {
			List<DrivingVO> list = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<DrivingVO>(DrivingVO.class));
			ret.put("data", list);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}
	
	@Override
	public Map<String, Object> getSearch(String car_num) {
		Map<String, Object> ret = new HashMap<>();
		String sqlString = String.format("select car_num, date, rac, sds, dsr from Driving_Unit_Data where car_num='%s'", car_num);
		ret.put("sql", sqlString);
		try {
			List<DrivingVO> d = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<DrivingVO>(DrivingVO.class));
			ret.put("data", d);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}
	
	@Override
	public Map<String, Object> vehicleInfo() {
		String sqlString = "select car_num, judgment from Vehicle_Information_Data";
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		try {
			List<VehicleVO> list = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<VehicleVO>(VehicleVO.class));
			ret.put("data", list);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}
		
	public Map<String, Object> oilInfo() {
		String sqlString = "select * from oil";
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		try {
			List<OilVO> list = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<OilVO>(OilVO.class));
			ret.put("data", list);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}
	
}