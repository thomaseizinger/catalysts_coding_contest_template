package at.ccc.common.api;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface InputReader extends AutoCloseable {

	String readLine();

	default String[] readParts() {
		final String line = readLine();
		return line.split(" ");
	}

	default Integer readInt() {
		return Integer.parseInt(readLine());
	}

	default <Result> Result readObject(Function<String[], Result> mapper) {
		return mapper.apply(readParts());
	}

	default <Result> Stream<Result> readObjects(Function<String[], Result> mapper) {

		final Integer numberOfObjects = readInt();

		return IntStream.range(0, numberOfObjects).mapToObj(index -> readObject(mapper));
	}
}
