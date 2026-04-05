public class Calculate {
    private static final int SENSOR_X = 36;
    private static final int SENSOR_Y = 24;

    public static float toFOV(float width, float height, int focal) throws IllegalArgumentException
    {
        if (width <= 0 || height <= 0 || focal <= 0) {
            throw new IllegalArgumentException("Inputs must be positive non-zero values");
        }
        if (width / height >= (float) SENSOR_X / SENSOR_Y) {
            return (float) ((180 / Math.PI) * 2 * Math.atan((double) (SENSOR_X / width * height) / (2 * focal)));
        } else {
            return (float) ((180 / Math.PI) * 2 * Math.atan((double) SENSOR_Y / (2 * focal)));
        }
    }
}
