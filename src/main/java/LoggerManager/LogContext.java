package loggerManager;

import java.text.DateFormat;
import java.util.Date;

public class LogContext {

    String entryDate;
    public String getEntryDate() { return entryDate; }

    int currentFileIndex;
    public String getCurrentFileIndex() { return Integer.toString(currentFileIndex); }

    int currentEntryIndex;
    public String getCurrentEntryIndex() { return Integer.toString(currentEntryIndex); }

    LogContext(DateFormat entryDateFormat, int currentFileIndex) {
        Date date = new Date(); 
        entryDate = entryDateFormat.format(date);
        this.currentFileIndex = currentFileIndex+1;
        currentEntryIndex = 1;
    }

    void incrementCurrentIndex() {
        currentFileIndex++;
        currentEntryIndex++;
    }
}
