/**
 *
 */
package com.commands;

import com.*;

import java.util.*;

public abstract class Command {

	protected List<String> output;
	protected ShadoVM vm;

	protected Command(ShadoVM vm) {
		this.output = new ArrayList<>();
		this.vm = vm;
		vm.addCommand(this);
	}

	public abstract String getCommand();

	public abstract String getDescription();

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

	protected static Map<String, String> parseArgs(String[] args) {

		Map<String, String> map = new HashMap<>();
		int count = 0;
		for (var arg : args) {
			var tokens = arg.split("=");
			if (tokens.length > 1) {
				map.put(tokens[0].replaceAll("-", ""), tokens[1]);
			} else {
				map.put(count + "", tokens[0]);
			}
			count++;
		}

		return map;
	}

	public static void setTimeout(final Runnable fun, final int delayMS) {
		new Thread(() -> {
			try {
				Thread.sleep(delayMS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			fun.run();
		}).start();
	}

	public static Command parseCommand(ShadoVM vm, String command) {
		String[] tokens = command.trim().split("\\s+");
		String commandStr = tokens[0].contains("!") ? tokens[0].substring(1) : tokens[0];

		for (final var cmd : vm.getCommands()) {
			if (cmd.getCommand().equals(commandStr)) {
				return cmd;
			}
		}

		return null;
	}
}
