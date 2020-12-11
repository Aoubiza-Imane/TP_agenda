package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.WEEKS;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {
    
    private  LocalDate tic =null ;
    private long noc = 0;
    private LocalDate fin ;
    private long nb;
    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
        super(title, start, duration, frequency);
        this.tic = terminationInclusive ;

    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.noc = numberOfOccurrences; 
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        if (tic == null){
            if (this.getFrequency()== ChronoUnit.DAYS){
                fin = this.getStart().toLocalDate().plusDays(noc-1);
            }
            if (this.getFrequency()== ChronoUnit.WEEKS){
                fin = this.getStart().toLocalDate().plusWeeks(noc-1);
            }
            if (this.getFrequency()== ChronoUnit.MONTHS){
                fin = this.getStart().toLocalDate().plusMonths(noc-1);
            }
        }
        else{
            fin =  tic ;}
        return fin;
    }

    public long getNumberOfOccurrences() {
       if (noc == 0){
            if (this.getFrequency()== ChronoUnit.DAYS){
                nb = DAYS.between(this.getStart().toLocalDate(),tic)+1;
            }
            if (this.getFrequency()== ChronoUnit.WEEKS){
                nb = WEEKS.between(this.getStart().toLocalDate(),tic)+1;
            }
            if (this.getFrequency()== ChronoUnit.MONTHS){
                nb = MONTHS.between(this.getStart().toLocalDate(),tic)+1;
            }
        }
        else{
            nb =  noc ;}
        return nb;
    }
        
}
