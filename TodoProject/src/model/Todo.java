package model;

import model.BaseEntity;

public class Todo extends BaseEntity<Integer> {
	private String title;
	private boolean status;
	
	public Todo(String title, boolean status) {
		this.title = title;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Todo [title=" + title + ", status=" + status + "]";
	}
	
	
}
