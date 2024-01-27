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

    public Experience(ExperienceLevel experience) {
        this.experience = experience;
    }

    public Experience(String experience) {
        setExperience(experience);
    }

    public void setExperience(ExperienceLevel experience) {
        this.experience = experience;
    }

    /**
     * Tries to convert a String to a valid ExperienceStr enum
     * If it fails, defaults to UNDEFINED
     * 
     * @param experienceStr a String that holds the demeanor
     */
    public void setExperience(String experienceStr) {
        // try to convert the String to the matching ExperienceType and store it
        try {
            experience = ExperienceType.valueOf(experienceStr.trim().toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) { // if it fails
            // set it to the default(i.e. UNDEFINED)
            experience = ExperienceType.UNDEFINED;
        }
    }

    public ExperienceLevel getExperience() {
        return experience;
    }
}