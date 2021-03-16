class PixelVertex {
	boolean[] pixel;

	public PixelVertex(int n) {
		pixel = new boolean[n];
	}

	public int difference(PixelVertex other) {
		int count = 0;
		for (int i = 0; i < pixel.length; i++) {
			if (pixel[i] != other.pixel[i])
				count++;
		}
		return count;
	}
}