package at.ccc.common.api;

import at.ccc.common.impl.LevelTestFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

public interface Level {

	@TestFactory
	default Stream<DynamicTest> files() {

		final Level instance = createLevelInstance();
		final LevelTestFactory factory = new LevelTestFactory(instance);

		try {
			return factory.create();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	default Level createLevelInstance() {
		try {
			return getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	void execute(InputReader inputReader, OutputWriter outputWriter);

}
