package com.commands;

import com.*;

import java.io.*;
import java.util.*;

public class Load extends Command {
	public Load(ShadoVM vm) {
		super(vm);
	}

	@Override
	public String getCommand() {
		return "load";
	}

	@Override
	public String getDescription() {
		return "Loads a file and executes the command inside. Args: '--filename' as string";
	}

	@Override
	public void run(String[] rawargs) {

		//Read file
		List<String> lines = new ArrayList<>();
		var args = Command.parseArgs(rawargs);
		try {
			Scanner scanner = new Scanner(new FileInputStream(args.get("filename")));
			while (scanner.hasNextLine())
				lines.add(scanner.nextLine());
			scanner.close();
		} catch (Exception e) {
			output.add("[ERROR] " + e.getMessage());
		}

		for (final var line : lines) {
			String[] tokens = line.trim().split("\\s+");
			String commandStr = tokens[0].substring(1);

			boolean exists = false;
			for (final var command : vm.getCommands()) {
				if (command.getCommand().equals(commandStr)) {
					command.run(Arrays.copyOfRange(tokens, 1, tokens.length));
					output.add(command.flushOutput());
					exists = true;
				}
			}

			if (!exists) {
				output.add("[ERROR] '" + commandStr + "' is not a valid command!");
			}
		}
	}
}
