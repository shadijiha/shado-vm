/**
 *
 */
package com.commands;

import com.*;

public class Mem extends Command {

	public Mem(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getCommand() {
		return "mem";
	}

	public String getDescription() {
		return "Shows the content of the VM memory";
	}

	@Override
	public void run(String[] args) {
		output.add(vm.toString());
	}
}
