/**
 *
 */
package com.commands;

import com.*;

public class Help extends Command {
	public Help(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getCommand() {
		return "help";
	}

	public String getDescription() {
		return "Show all the commands and their description";
	}

	@Override
	public void run(String[] args) {

		output.add("Commands: ");
		for (var command : vm.getCommands()) {
			output.add("\t" + command.getCommand() + ": " + command.getDescription());
		}

		output.add("\nPrefixes: ");
		for (var prefix : vm.getCommandsPrefixes()) {
			output.add(String.format("\t%s: %s (Expected args: %d)", prefix.getPrefix(), prefix.getDescription(), prefix.getArgsCount()));
		}

	}
}
