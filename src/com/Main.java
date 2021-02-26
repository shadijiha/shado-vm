package com;

import com.commands.*;
import com.prefix.*;

import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// write your code here

		Scanner scanner = new Scanner(System.in);
		setupCommands();
		var vm = ShadoVM.singleton;

		boolean exit = false;
		while (true) {

			vm.print("> ");
			String line = scanner.nextLine();

			boolean exists = false;
			String[] tokens = line.trim().split("\\s+");
			String commandStr = tokens[0].contains("!") ? tokens[0].substring(1) : tokens[0];

			if (line.charAt(0) == '!') {
				for (final Command command : vm.getCommands()) {
					if (command.getCommand().equals(commandStr)) {
						command.run(Arrays.copyOfRange(tokens, 1, tokens.length));
						vm.println(command.flushOutput());
						exists = true;
					}
				}
			}

			// Otherwise check the prefixes
			for (final CommandPrefix prefix : vm.getCommandsPrefixes()) {
				if (prefix.getPrefix().equals(commandStr)) {
					prefix.run(Arrays.copyOfRange(tokens, 1, tokens.length));
					vm.println(prefix.flushOutput());
					exists = true;
				}
			}

			if (!exists) {
				vm.println("[ERROR] '" + commandStr + "' is not a valid command or prefix (Did you forget the '!' before a command?)!");
			}

			Thread.sleep(10);
		}
	}

	public static void setupCommands() {
		new Exit(ShadoVM.singleton);
		new Mem(ShadoVM.singleton);
		new Reset(ShadoVM.singleton);
		new Create(ShadoVM.singleton);
		new GC(ShadoVM.singleton);
		new Help(ShadoVM.singleton);
		new Load(ShadoVM.singleton);

		new DelayPrefix(ShadoVM.singleton);
		new RepeatPrefix(ShadoVM.singleton);
	}
}
