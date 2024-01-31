public class Experience {
    public enum ExperienceLevel {
        FIRST_YEAR,
        INTERMEDIATE,
        EXPERIENCED;
    }

    private ExperienceLevel experience;

    public Experience() {
        this.experience = ExperienceLevel.FIRST_YEAR;
    }

    public void setExperience(ExperienceLevel experience) {
        this.experience = experience;
    }

    /**
     * Tries to convert a String to a valid ExperienceLevel enum
     * If it fails, defaults to FIRST_YEAR
     * 
     * @param experienceStr a String that holds the experience level
     */
    public void setExperience(String experienceStr) {
        // try to convert the String to the matching ExperienceLevel and store it
        try {
            experience = ExperienceLevel.valueOf(experienceStr.trim().toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) { // if it fails
            // set it to the default(i.e. FIRST_YEAR)
            experience = ExperienceLevel.FIRST_YEAR;
        }
    }
}