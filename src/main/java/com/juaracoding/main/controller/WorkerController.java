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
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;


@RestController
@RequestMapping("/worker")
public class WorkerController  {

	@Autowired
	JdbcTemplate jdbc;

	public List<Worker> getWorker() {

		String sql = "Select * from worker";

		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

		return worker;

	}
	
	//menampilkan data departement
	public List<Worker>getdepartement() {

		String sql = "CALL `datadepartment`()";

		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

		return worker;

	}
	//menampilkan data gaji
	public List<Worker> Selectgaji( double salary) {

		String sql = "SELECT * FROM worker WHERE `salary`=5000000";
		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

		return worker;

	}
	
	public List<Worker>getdepartement1() {

		String sql = "select department, count(*) as jumlah from worker group by department;";

		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

		return worker;

	}
	
	//menampilkan 5 salary tertinggi
	public List<Worker> getSalary() {

		String sql = "SELECT * FROM worker ORDER BY salary DESC LIMIT 5";

		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

		return worker;

	}
	

	public int insertWorker(Worker worker) {
		return jdbc.update("insert into worker(worker_id, first_name, last_name, salary, joining_date, department) values ('" + worker.getWorker_id() + "','"
				+ worker.getFirst_name() + "','" + worker.getLast_name() + "'," + worker.getSalary() + ",'"+ worker.getJoining_date() + "','"+ worker.getDepartment()+"')");

	}

	
	public int updateWorker(int worker_id, Worker worker) {

		return jdbc.update("UPDATE worker SET `first_name`='" + worker.getFirst_name() + "',`last_name`='" + worker.getLast_name()
				+ "`salary`=" + worker.getSalary() + "',`joining_date`='" + worker.getJoining_date() + "',`department`=" + worker.getDepartment() +" WHERE worker_id = " + worker.getWorker_id() );

	}

	public int deleteWorker(int worker_id) {
		return jdbc.update("DELETE FROM `worker`  WHERE `worker_id` = " + worker_id );
		
	
	}

	

	 @PostMapping("/")
	    public String add(@RequestBody Worker worker) {
		 

			if (this.insertWorker(worker) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
	    }
	 
	 
	 @DeleteMapping("/{worker_id}")
	    public void delete(@PathVariable int worker_id) {
		 	deleteWorker(worker_id);
	 }
	 
	 
	 @GetMapping("/")
	    public List<Worker> list() {
	        return getWorker();
	    }
	 
	 
	 @GetMapping("/salary")
	    public List<Worker> list2(@PathVariable double salary) {
	        return Selectgaji(salary);
	    }
	 
	 @GetMapping("/getsalary")
	    public List<Worker> getsalary1() {
	        return getSalary();
	    }
	 
	 @GetMapping("/getdepartment")
	    public List<Worker> getdepartemen() {
	        return getdepartement1();
	    }
	 
	 @PutMapping("/{worker_id}")
	    public ResponseEntity<?> update(@RequestBody Worker worker, @PathVariable int worker_id) {
		 try {
	            updateWorker(worker_id, worker);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	

}