package command.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateParser {

    private static DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendOptional(DateTimeFormatter.ofPattern("d M yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d M yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d M yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d M yy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yy"))
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    public static LocalDateTime parseDate(String str) {
        return LocalDateTime.parse(str, inputFormatter);
    }

    public static String dateStringOutput(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }
}