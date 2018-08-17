package com.example.tvd.analogics_device_printing.values;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FunctionCall {

    public String space(String s, int len) {
        int temp;
        StringBuilder spaces = new StringBuilder();
        temp = len - s.length();
        for (int i = 0; i < temp; i++) {
            spaces.append(" ");
        }
        return (s + spaces);
    }

    public String alignright(String msg, int len) {
        StringBuilder msgBuilder = new StringBuilder(msg);
        for (int i = 0; i < len - msgBuilder.length(); i++) {
            msgBuilder.insert(0, " ");
        }
        msg = msgBuilder.toString();
        msg = String.format("%" + len + "s", msg);
        return msg;
    }

    public String aligncenter(String msg, int len) {
        int count = msg.length();
        int value = len - count;
        int append = (value / 2);
        return space(" ", append) + msg + space(" ", append);
    }
    public String currentDateandTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        return sdf.format(new Date());
    }
}
