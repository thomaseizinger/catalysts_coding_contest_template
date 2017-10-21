package at.ccc.sample;

public class Line {

	private final String part1;
	private final String part2;
	private final String part3;

	public Line(String part1, String part2, String part3) {

		this.part1 = part1;
		this.part2 = part2;
		this.part3 = part3;
	}

	public static Line fromParts(String[] parts) {
		return new Line(parts[0], parts[1], parts[2]);
	}

	public String getPart1() {
		return part1;
	}

	public String getPart2() {
		return part2;
	}

	public String getPart3() {
		return part3;
	}
}
