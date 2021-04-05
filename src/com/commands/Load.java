package com.commands;

import com.*;
import com.prefix.*;

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
			boolean exists = false;
			String[] tokens = line.trim().split("\\s+");
			String commandStr = tokens[0].contains("!") ? tokens[0].substring(1) : tokens[0];

			if (!line.isEmpty() && line.charAt(0) == '!') {
				for (final Command command : vm.getCommands()) {
					if (command.getCommand().equals(commandStr)) {
						command.run(Arrays.copyOfRange(tokens, 1, tokens.length));
						output.add(command.flushOutput());
						exists = true;
					}
				}
			}

			// Otherwise check the prefixes
			for (final CommandPrefix prefix : vm.getCommandsPrefixes()) {
				if (prefix.getPrefix().equals(commandStr)) {
					prefix.run(Arrays.copyOfRange(tokens, 1, tokens.length));
					output.add(prefix.flushOutput());
					exists = true;
				}
			}

			if (!exists) {
				output.add("[ERROR] '" + commandStr + "' is not a valid command or prefix (Did you forget the '!' before a command?)!");
			}
		}
	}
}
