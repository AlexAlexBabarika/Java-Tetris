public enum Difficulty {
    Easy(1f),
    Normal(0.75f), 
    Hard(0.25f);

    private float speedMultiplier;

    private Difficulty(float multiplier) {
        speedMultiplier = multiplier;
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }
}
