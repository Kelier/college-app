package com.wan.college.entity;

import java.util.ArrayList;

/**
 * 
 * @author Apathy、恒
 * 
 *         父类分组的实体
 * 
 * */

public class ChildEntity {

	private int groupColor;

	private String groupName;

	private ArrayList<String> childNames;
	private ArrayList<String> childIds;

	public ArrayList<String> getChildIds() {
		return childIds;
	}

	public void setChildIds(ArrayList<String> childIds) {
		this.childIds = childIds;
	}

/* ==========================================================
	 * ======================= get method =======================
	 * ========================================================== */
	
	public int getGroupColor() {
		return groupColor;
	}

	public String getGroupName() {
		return groupName;
	}

	public ArrayList<String> getChildNames() {
		return childNames;
	}

	/* ==========================================================
	 * ======================= set method =======================
	 * ========================================================== */
	
	public void setGroupColor(int groupColor) {
		this.groupColor = groupColor;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setChildNames(ArrayList<String> childNames) {
		this.childNames = childNames;
	}

}
