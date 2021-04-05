package com;

import com.commands.*;
import com.prefix.*;

import java.io.*;
import java.util.*;

public class ShadoVM {

	private ShadoObject[] objects;
	private int totalObjects;
	private List<Command> commands;
	private List<CommandPrefix> prefixes;

	public ShadoVM(int capacity) {
		objects = new ShadoObject[capacity];
		totalObjects = 0;
		commands = new ArrayList<>();
		prefixes = new ArrayList<>();
	}

	public ShadoVM() {
		this(10);
	}

	public void reset() {
		Arrays.fill(objects, null);
	}

	public void add(ShadoObject obj) {
		objects[totalObjects++] = obj;
	}

	public void runGarbageCollector() {
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null && objects[i].getRefs() == 0) {
				objects[i] = null;
				totalObjects--;
			}
		}
	}

	public long memoryUsed() {
		return totalObjects;
	}

	public long memoryTotal() {
		return objects.length;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("[");

		for (var obj : objects) {
			if (obj != null)
				builder.append(obj.toString()).append(", ");
		}

		builder.append("]");

		return builder.toString();
	}

	public void addCommand(Command command) {
		commands.add(command);
	}

	public void addPrefix(CommandPrefix prefix) {
		prefixes.add(prefix);
	}

	public void print(PrintStream stream, Object data) {
		stream.print(data.toString());
	}

	public void print(Object data) {
		print(System.out, data);
	}

	public void println(PrintStream stream, Object data) {
		stream.println(data.toString());
	}

	public void println(Object data) {
		println(System.out, data);
	}

	public List<Command> getCommands() {
		return List.of(commands.toArray(new Command[]{}));
	}

	public List<CommandPrefix> getCommandsPrefixes() {
		return List.of(prefixes.toArray(new CommandPrefix[]{}));
	}
}
