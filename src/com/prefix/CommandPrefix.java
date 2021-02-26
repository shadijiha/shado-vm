/**
 * A command prefix is a full uppercase instruction that takes a command as an argument
 */
package com.prefix;

import com.*;

import java.util.*;

public abstract class CommandPrefix {

	protected ShadoVM vm;
	protected List<String> output;

	public CommandPrefix(ShadoVM vm) {
		this.vm = vm;
		output = new ArrayList<>();
		vm.addPrefix(this);
	}

	public abstract String getPrefix();

	public abstract String getDescription();

	public abstract int getArgsCount();

	public abstract void run(String[] args);

	public String getOutput(String seperator) {
		return String.join(seperator, output);
	}

	public String getOutput() {
		return getOutput("\n");
	}

	public String flushOutput(String seperator) {
		String temp = getOutput(seperator);
		this.output.clear();
		return temp;
	}

	public String flushOutput() {
		return flushOutput("\n");
	}
}
