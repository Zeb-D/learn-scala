package com.yd.scala.groovy;

/**
 * 脚本类型
 * 
 * @author Zeb灬D
 *
 */
public enum ScriptType {

	FILE(0, "文件"), 
	
	TEXT(1, "文本");

	private int id;

	private String name;

	private ScriptType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
