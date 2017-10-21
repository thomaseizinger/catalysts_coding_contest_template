package at.ccc.sample;

import at.ccc.common.api.InputReader;
import at.ccc.common.api.Level;
import at.ccc.common.api.OutputWriter;
import at.ccc.common.impl.LevelTestFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This level is called "Sample".
 *
 * Therefore the {@link LevelTestFactory} will look in a folder named "sample" (lowercase classname)
 * in the resources path of this class for level files, load them one by one and execute them.
 *
 * The results are written in a folder named "results" in the resources path.
 */
public class Sample implements Level {
	@Override
	public void execute(InputReader inputReader, OutputWriter outputWriter) {

		final List<Line> lines = inputReader.readObjects(Line::fromParts).collect(toList());

		outputWriter.write(lines.size());

		lines.forEach(line -> outputWriter.write(
				line.getPart1(),
				line.getPart2(),
				line.getPart3()
		));
	}
}
