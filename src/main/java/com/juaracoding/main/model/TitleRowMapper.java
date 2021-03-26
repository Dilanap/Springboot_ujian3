package com.juaracoding.main.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class TitleRowMapper implements RowMapper<Title>{
	@Override
	public Title mapRow(ResultSet ab, int rowNum) throws SQLException {

		Title title= new Title();
		title.setWorker_ref_id(ab.getInt("worker_ref_id"));
		title.setWorker_title(ab.getString("worker_title"));
		title.setAffected_from(ab.getString("affected_from"));
		
		
		// TODO Auto-generated method stub
		return title;
	}
}
