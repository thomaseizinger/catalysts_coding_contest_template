package at.ccc.common.impl;

import at.ccc.common.api.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;

public class InputReaderImpl implements InputReader {

	private final BufferedReader bufferedReader;

	public InputReaderImpl(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public String readLine() {
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public void close() throws Exception {
		bufferedReader.close();
	}
}
