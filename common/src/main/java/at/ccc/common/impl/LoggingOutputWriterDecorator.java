package at.ccc.common.impl;

import at.ccc.common.api.OutputWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingOutputWriterDecorator implements OutputWriter {

	private final OutputWriter delegate;
	private final Logger logger;

	public LoggingOutputWriterDecorator(String levelName, OutputWriter delegate) {
		this.delegate = delegate;
		this.logger = LogManager.getLogger(levelName + " < ");
	}

	@Override
	public void writeLine(String line) {
		logger.info(line);
		delegate.writeLine(line);
	}

	@Override
	public void close() throws Exception {
		delegate.close();
	}
}
