package com.pluralsight.httpclient;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileProcessorUsingJDK11 {

	public static void main(String[] args) {
		writeFile();
		readFile();
	}

	private static void writeFile() {
		try {
			File helloWorldFile = new File("helloworld.txt");
			if (!helloWorldFile.isFile()) {
				helloWorldFile.createNewFile();
			}
			Files.writeString(helloWorldFile.toPath(), "Hello World!", Charset.defaultCharset(),
					StandardOpenOption.WRITE);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write file", e);
		}
	}

	private static void readFile() {
		try {
			File helloWorldFile = new File("helloworld.txt");
			String content = Files.readString(helloWorldFile.toPath(), Charset.defaultCharset());
			System.out.print(content);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read file", e);
		}
	}

}
