package com.ltsh.common.util;

public class Item<Key,Value> {
	private Key key;
	private Value value;
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public Item(Key key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}
	
}
