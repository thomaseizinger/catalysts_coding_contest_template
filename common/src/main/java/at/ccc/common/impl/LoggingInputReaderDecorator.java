package at.ccc.common.impl;

import at.ccc.common.api.InputReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingInputReaderDecorator implements InputReader {

	private final InputReader delegate;
	private final Logger logger;

	public LoggingInputReaderDecorator(String levelName, InputReader delegate) {
		this.delegate = delegate;
		this.logger = LogManager.getLogger(levelName + " > ");
	}

	@Override
	public String readLine() {

		final String line = delegate.readLine();

		logger.info(line);

		return line;
	}

	@Override
	public void close() throws Exception {
		delegate.close();
	}
}
