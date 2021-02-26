/**
 *
 */
package com.prefix;

import com.*;
import com.commands.*;

import java.util.*;

public class DelayPrefix extends CommandPrefix {
	public DelayPrefix(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getPrefix() {
		return "DELAY";
	}

	@Override
	public String getDescription() {
		return "Executes a command after a delay. Example: '> DELAY 3000 !help'";
	}

	@Override
	public int getArgsCount() {
		return 2;
	}

	@Override
	public void run(String[] args) {

		try {
			int delay = Integer.parseInt(args[0]);
			Command cmd = Command.parseCommand(vm, args[1]);
			String[] cmdArgs = args.length >= getArgsCount() ? Arrays.copyOfRange(args, 2, args.length) : new String[]{""};

			Command.setTimeout(() -> {
				cmd.run(cmdArgs);
				vm.println("");
				vm.println(cmd.flushOutput());
				vm.print("> ");
			}, delay);


		} catch (ArrayIndexOutOfBoundsException e) {
			output.add("[ERROR] expected " + getArgsCount() + " arguments got " + args.length);
		} catch (Exception e) {
			output.add(e.getMessage());
		}
	}
}
