public class Experience {
    public enum ExperienceLevel {
        FIRST_YEAR,
        INTERMEDIATE,
        EXPERIENCED;

        /**
         * Attempts to convert a String to an ExperienceLevel
         * If it it does not match any ExperienceLevel, default to FIRST_YEAR
         * 
         * @param experienceStr a String that contains an ExperienceLevel
         */
        private static ExperienceLevel toExperienceLevel(String experienceStr) {
            // try to convert the String to the matching ExperienceLevel and store it
            try {
                return ExperienceLevel.valueOf(experienceStr.trim().toUpperCase());

            } catch (IllegalArgumentException | NullPointerException e) { // if it fails
                // set it to the default(i.e. UNDEFINED)
                return ExperienceLevel.FIRST_YEAR;
            }
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
}