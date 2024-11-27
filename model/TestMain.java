package model;

import model.dao.TaskDAO;

public class TestMain {

	public static void main(String[] args) {
		
		TaskDAO t = new TaskDAO();
		t.getConnection();

	}

}
