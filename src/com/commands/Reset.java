/**
 *
 */
package com.commands;

import com.*;

public class Reset extends Command {

	public Reset(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getCommand() {
		return "reset";
	}

	public String getDescription() {
		return "Clears the memory of the VM";
	}

	@Override
	public void run(String[] args) {
		output.add("[Warning] Resetting the VM. Previous state: " + vm.memoryUsed() + " / " + vm.memoryTotal());
		vm.reset();
	}
}
