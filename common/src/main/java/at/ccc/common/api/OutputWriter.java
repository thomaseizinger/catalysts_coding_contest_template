package at.ccc.common.api;

public interface OutputWriter extends AutoCloseable {

	void writeLine(String line);

	default void write(Integer integer) {
		writeLine(Integer.toString(integer));
	}

	default void write(String... parts) {
		writeLine(String.join(" ", parts));
	}

}
