package com.commands;

import com.*;

public class GC extends Command {
	public GC(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getCommand() {
		return "gc";
	}


	public String getDescription() {
		return "runs the garbage collector and removes all objects that have 0 references";
	}

	@Override
	public void run(String[] args) {
		vm.runGarbageCollector();
		output.add("Garbage collected!");
	}
}
