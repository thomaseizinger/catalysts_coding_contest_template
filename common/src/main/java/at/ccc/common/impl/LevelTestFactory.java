package at.ccc.common.impl;

import at.ccc.common.api.InputReader;
import at.ccc.common.api.Level;
import at.ccc.common.api.OutputWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DynamicTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LevelTestFactory {

	private static final Logger LOGGER = LogManager.getLogger();

	private final Level level;

	public LevelTestFactory(Level level) {
		this.level = level;
	}

	public Stream<DynamicTest> create() throws Exception {

		final String levelName = level.getClass().getSimpleName().toLowerCase();

		LOGGER.info("Executing level '{}'.", levelName);

		final Path levelResourceFolder = Paths.get(level.getClass().getResource("/" + levelName).toURI());
		final Path resultFolder = Paths.get(levelResourceFolder.toString(), "results");

		if (!resultFolder.toFile().exists()) {
			Files.createDirectory(resultFolder);
		}

		final List<Path> levelFiles = Files
				.list(levelResourceFolder)
				.filter(path -> path.toFile().isFile())
				.collect(toList());

		LOGGER.info("Number of level files: {}.", levelFiles.size());

		return levelFiles
				.stream()
				.map(file -> {
					final String levelInstanceName = file.getFileName().toString();
					return DynamicTest.dynamicTest(
							levelInstanceName,
							() -> {
								final Path resultFile = Paths.get(resultFolder.toString(), levelInstanceName);
								Files.deleteIfExists(resultFile);

								final BufferedReader inputFilerReader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
								final BufferedWriter outputFileWriter = Files.newBufferedWriter(resultFile, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

								try (final InputReader inputReader = new LoggingInputReaderDecorator(levelInstanceName, new InputReaderImpl(inputFilerReader));
								     final OutputWriter outputWriter = new LoggingOutputWriterDecorator(levelInstanceName, new OutputWriterImpl(outputFileWriter))) {
									level.execute(inputReader, outputWriter);
								}
							}
					);
				});
	}
}
