/**
 *
 */

package com;

public class ShadoObject {

	private int refs;

	public ShadoObject(int refs) {
		this.refs = refs;
	}

	public ShadoObject() {
		refs = 0;
	}

	public int getRefs() {
		return this.refs;
	}

	public String toString() {
		return getClass().getName() + "@" + hashCode() + " (" + refs + ")";
	}
}
