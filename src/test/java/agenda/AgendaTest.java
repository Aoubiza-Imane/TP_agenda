package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AgendaTest {
    Agenda agenda;
    
    // November 1st, 2020
    LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

    // January 5, 2021
    LocalDate jan_5_2021 = LocalDate.of(2021, 1, 5);

    // November 1st, 2020, 22:30
    LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);
    LocalDateTime nov_1__2020_23_30 = LocalDateTime.of(2020, 11, 1, 23, 30);
    LocalDateTime nov_1__2020_20_00 = LocalDateTime.of(2020, 11, 1, 20, 00);

    // 120 minutes
    Duration min_120 = Duration.ofMinutes(120);
    Duration min_30 = Duration.ofMinutes(30);

    // A simple event
    // November 1st, 2020, 22:30, 120 minutes
    Event simple = new Event("Simple event", nov_1__2020_22_30, min_120);
    Event bloque = new Event("Event sympa mais un peu génant",nov_1__2020_23_30,min_30); 
    Event passe = new Event("Event d'echauffement",nov_1__2020_20_00,min_30);
    // A Weekly Repetitive event ending at a given date
    RepetitiveEvent fixedTermination = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, jan_5_2021);

    // A Weekly Repetitive event ending after a give number of occurrrences
    RepetitiveEvent fixedRepetitions = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, 10);
    
    // A daily repetitive event, never ending
    // November 1st, 2020, 22:30, 120 minutes
    RepetitiveEvent neverEnding = new RepetitiveEvent("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);

    @BeforeEach
    public void setUp() {
        agenda = new Agenda();
        agenda.addEvent(simple);
        agenda.addEvent(fixedTermination);
        agenda.addEvent(fixedRepetitions);
        agenda.addEvent(neverEnding);
    }
    
    @Test
    public void testMultipleEventsInDay() {
        assertEquals(4, agenda.eventsInDay(nov_1_2020).size(), "Il y a 4 événements ce jour là");
        assertTrue(agenda.eventsInDay(nov_1_2020).contains(neverEnding));
    }

    @Test
    public void testFindByTitle(){
        assertTrue(agenda.findByTitle("Simple event").contains(simple),"Le programme devrait retourner vrai car simple event appartient à simple.");
        assertFalse(agenda.findByTitle("event banal").contains(simple),"On ne connaît pas event banal");
    }
    
    @Test 
    public void testisFreeFor(){
        assertFalse(agenda.isFreeFor(bloque),"Le planning devrait être déjà bloqué pour simple.");
        assertTrue(agenda.isFreeFor(passe),"Le planning devrait accepter passe.");
}
}
