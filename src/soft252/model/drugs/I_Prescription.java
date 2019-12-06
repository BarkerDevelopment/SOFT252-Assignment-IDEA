package soft252.model.drugs;

import java.time.LocalDate;

/**
 * Defines the functions for a Treatment object.
 */
public interface I_Prescription {
    /**
     * @return the _startDate variable. This describes the day which the treatment starts..
     */
    public LocalDate getStartDate();

    /**
     * @return the _treatment variable. This represents the actual treatment prescribed.
     */
    public I_Treatment getTreatment();

    /**
     * @return the _qty variable. This represents the number of individual treatments given.
     */
    public int getQty();

    /**
     * @return the _course variable. This describes the number of days over which the treatment should be completed.
     */
    public int getCourse();
}
