package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long idProvider = 0;
    private Map<Long, TimeEntry> timeEntries = new HashMap<>();

    public InMemoryTimeEntryRepository() {

    }

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(++idProvider, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(newTimeEntry.getId(), newTimeEntry);
        return newTimeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    public List<TimeEntry> list() {
        return timeEntries.values().stream().collect(Collectors.toList());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (find(id) == null){
            return null;
        }

        TimeEntry newTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(newTimeEntry.getId(), newTimeEntry);
        return newTimeEntry;
    }

    public void delete(long id) {
        timeEntries.remove(id);
    }
}
