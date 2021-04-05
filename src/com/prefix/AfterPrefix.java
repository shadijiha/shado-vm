/**
 *
 */
package com.prefix;

import com.*;
import com.commands.*;

public class AfterPrefix extends CommandPrefix {
	public AfterPrefix(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getPrefix() {
		return "AFTER";
	}

	@Override
	public String getDescription() {
		return "Executes a command after a another command. Example '> AFTER !create EXECUTE !mem";
	}

	@Override
	public int getArgsCount() {
		return 2;
	}

	@Override
	public void run(String[] args) {

		try {
			String[] tokens = String.join(" ", args).split("EXECUTE");

			Command command1 = Command.parseCommand(vm, tokens[0]);
			Command command2 = Command.parseCommand(vm, tokens[1]);

			command1.run(tokens[0].replaceAll(command1.getCommand(), "").split("\\s+"));
			output.add(command1.flushOutput());

			command2.run(tokens[1].replaceAll(command2.getCommand(), "").split("\\s+"));
			output.add(command2.flushOutput());
		} catch (ArrayIndexOutOfBoundsException e) {
			output.add("[ERROR] expected " + getArgsCount() + " arguments got " + args.length);
		} catch (Exception e) {
			output.add(e.getMessage());
		}
	}
}
