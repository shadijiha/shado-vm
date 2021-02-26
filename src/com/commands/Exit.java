/**
 *
 */
package com.commands;

import com.*;

public class Exit extends Command {

	public Exit(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getCommand() {
		return "exit";
	}

	public String getDescription() {
		return "closes the VM";
	}

	@Override
	public void run(String[] args) {
		System.exit(0);
	}
}
