package br.com.valkyrie.campus.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DefaultDateFormatter {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public final String formatDate(Date date) {
        return formatter.format(date);
    }
}
