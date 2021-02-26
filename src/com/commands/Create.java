/**
 *
 */
package com.commands;

import com.*;

public class Create extends Command {

	public Create(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getCommand() {
		return "create";
	}

	public String getDescription() {
		return "creates a Shado Object and adds it to the VM memory. Args: '--refs' as integer";
	}

	@Override
	public void run(String[] rawArgs) {
		var args = Command.parseArgs(rawArgs);

		try {
			var obj = new ShadoObject(Integer.parseInt(args.getOrDefault("refs", 0 + "")));
			vm.add(obj);
			output.add("added " + obj.getClass() + " at " + obj.hashCode());
		} catch (Exception e) {
			output.add("[ERROR] Syntax error. 'refs' arg must be a valid integer");
		}
	}
}
