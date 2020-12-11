package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {

    private ArrayList<Event> evenements = new ArrayList<>();

    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        evenements.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day to test
     * @return and iterator to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        ArrayList<Event> evenementsDuJour = new ArrayList<>();
        for (Event e : evenements) {
            if (day.isAfter(e.getStart().toLocalDate().minusDays(1)) && day.isBefore(e.getStart().toLocalDate().plusDays(e.getDuration().toDays()).plusDays(1))) {
                evenementsDuJour.add(e);
            }
        }
        return evenementsDuJour;
    }

    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     *
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        ArrayList<Event> myEventsByTitle = new ArrayList<>();
        for (Event e : evenements) {
            if (e.getTitle().equals(title)) {
                myEventsByTitle.add(e);
            }
        }
        return myEventsByTitle;
    }

    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     *
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event e) {
        boolean free = false;
        for (Event ev : evenements) {
            if (evenements.isEmpty()) {
                free = true;
            }
            if (e.getStart().plus(e.getDuration()).isBefore(ev.getStart())) {
                free = true;
            }
            if (e.getStart().plus(e.getDuration()).isAfter(ev.getStart().plus(ev.getDuration()))) {
                free = true;
            }
        }
        return free;
    }
}
