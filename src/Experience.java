public class Experience {
    public enum ExperienceLevel {
        FIRST_YEAR,
        INTERMEDIATE,
        EXPERIENCED,
        UNDEFINED;

        /**
         * Attempts to convert a String to an ExperienceLevel
         * If it it does not match any ExperienceLevel, default to FIRST_YEAR
         * 
         * @param experienceStr a String that contains an ExperienceLevel
         */
        private static ExperienceLevel toExperienceLevel(String experienceStr) {
            // convert the String to the matching ExperienceLevel and store it
            ExperienceLevel experience =  ExperienceLevel.valueOf(experienceStr.trim().toUpperCase());

            // Can not be set to undefined
            if (experience == ExperienceLevel.UNDEFINED) {
                throw new IllegalArgumentException("\""  + experienceStr + "\" is not a valid experience.");
            }

            return experience;
        }
    }

    private ExperienceLevel experience;

    public Experience() {
        this.experience = ExperienceLevel.FIRST_YEAR;
    }

    public Experience(String experienceStr) {
        experience = ExperienceLevel.toExperienceLevel(experienceStr);
    }

    public Experience(ExperienceLevel experience) {
        this.experience = experience;
    }

    public ExperienceLevel getExperience() {
        return experience;
    }

    public void setExperience(ExperienceLevel experience) {
        this.experience = experience;
    }

    public void setExperience(String experienceStr) {
        this.experience = ExperienceLevel.toExperienceLevel(experienceStr);
    }

    @Override
    public String toString() {
        return experience.toString();
    }
}