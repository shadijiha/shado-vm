/**
 *
 */
package com.prefix;

import com.*;
import com.commands.*;

import java.util.*;

public class RepeatPrefix extends CommandPrefix {

	public RepeatPrefix(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getPrefix() {
		return "REPEAT";
	}

	@Override
	public String getDescription() {
		return "Executes a command a certain number of time. Example: '> REPEAT 10 !mem'";
	}

	@Override
	public int getArgsCount() {
		return 2;
	}

	@Override
	public void run(String[] args) {

		try {
			int repeats = Integer.parseInt(args[0]);
			Command cmd = Command.parseCommand(vm, args[1]);
			String[] cmdArgs = args.length >= getArgsCount() ? Arrays.copyOfRange(args, 2, args.length) : new String[]{""};

			for (int i = 0; i < repeats; i++) {
				cmd.run(cmdArgs);
				vm.println(cmd.flushOutput());
			}

			vm.print("> ");


		} catch (ArrayIndexOutOfBoundsException e) {
			output.add("[ERROR] expected " + getArgsCount() + " arguments got " + args.length);
		} catch (Exception e) {
			output.add(e.getMessage());
		}

	}
}
