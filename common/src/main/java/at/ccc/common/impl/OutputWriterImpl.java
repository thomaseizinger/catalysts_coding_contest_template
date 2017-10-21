package at.ccc.common.impl;

import at.ccc.common.api.OutputWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;

public class OutputWriterImpl implements OutputWriter {

	private final BufferedWriter bufferedWriter;

	public OutputWriterImpl(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}

	@Override
	public void writeLine(String line) {
		try {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public void close() throws Exception {
		bufferedWriter.close();
	}
}
