package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.TitleRowMapper;


@RestController
@RequestMapping("/title")
public class TitleController  {

	@Autowired
	JdbcTemplate jdbc;

	public List<Title> getTitle() {

		String sql = "Select * from title";

		List<Title> title = jdbc.query(sql, new TitleRowMapper());

		return title;

	}
	
	

	public int insertTitle(Title title) {
		return jdbc.update("insert into title(worker_ref_id, worker_title, affected_from) values ('" + title.getWorker_ref_id() + "','"
				+ title.getWorker_title() + "','" + title.getAffected_from()+"')");

	}

	
	public int updateTitle(int worker_ref_id, Title title) {

		return jdbc.update("UPDATE title SET `worker_title`='" + title.getWorker_title() + "',`affected_from`='" + title.getAffected_from()
				+ " WHERE worker_ref_id = " + title.getWorker_ref_id() );

	}

	public int deleteTitle(int worker_ref_id) {
		return jdbc.update("DELETE FROM `title`  WHERE `worker_ref_id` = " + worker_ref_id );
		
	
	}


	 @PostMapping("/")
	    public String add(@RequestBody Title title) {
		 

			if (this.insertTitle(title) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
	    }
	 
	 
	 @DeleteMapping("/{worker_ref_id}")
	    public void delete(@PathVariable int worker_ref_id) {
		 	deleteTitle(worker_ref_id);
	 }
	 
	 
	 @GetMapping("/")
	    public List<Title> list() {
	        return getTitle();
	    }
	 
	 
	 @PutMapping("/{worker_ref_id}")
	    public ResponseEntity<?> update(@RequestBody Title title, @PathVariable int worker_ref_id) {
		 try {
	            updateTitle(worker_ref_id, title);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	

}