package fanjoker.vips.utils;

import java.util.concurrent.TimeUnit;

public class TFormat {

    public static String format(long tempo) {
        if (tempo == 0)
            return "0 segundos";

        long dias = TimeUnit.MILLISECONDS.toDays(tempo);
        long horas = TimeUnit.MILLISECONDS.toHours(tempo) - (dias * 24);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(tempo) - (TimeUnit.MILLISECONDS.toHours(tempo) * 60);
        long segundos = TimeUnit.MILLISECONDS.toSeconds(tempo) - (TimeUnit.MILLISECONDS.toMinutes(tempo) * 60);

        StringBuilder sb = new StringBuilder();

        if (dias > 0)
            sb.append(dias + (dias == 1 ? " dia" : " dias"));

        if (horas > 0)
            sb.append(dias > 0 ? (minutos > 0 ? ", " : " e ") : "").append(horas + (horas == 1 ? " hora" : " horas"));

        if (minutos > 0)
            sb.append(dias > 0 || horas > 0 ? (segundos > 0 ? ", " : " e ") : "").append(minutos + (minutos == 1 ? " minuto" : " minutos"));

        if (segundos > 0)
            sb.append(dias > 0 || horas > 0 || minutos > 0 ? " e " : (sb.length() > 0 ? ", " : "")).append(segundos + (segundos == 1 ? " segundo" : " segundos"));

        String s = sb.toString();
        return s.isEmpty() ? "0 segundos" : s;
    }
    public static String format2(long time) {
        String format = "";
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        long hoursInMillis = TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time - hoursInMillis);
        long minutesInMillis = TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time - (hoursInMillis + minutesInMillis));
        int days = (int) (time / (1000*60*60*24));
        if (hours > 0)
            if (days > 0) {
                time = time - TimeUnit.DAYS.toMillis(days);
                hours = TimeUnit.MILLISECONDS.toHours(time - minutesInMillis);
                format = days + "d, " + hours + (hours > 1 ? "h" : "h");
                return format;
            } else {
                format = hours + (hours > 1 ? "h" : "h");
            }
        if (minutes > 0) {
            if ((seconds > 0) && (hours > 0))
                format += " ";
            else if (hours > 0)
                format += " ";
            format += minutes + (minutes > 1 ? "m" : "m");
        }
        if (seconds > 0) {
            if ((hours > 0) || (minutes > 0))
                format += " ";
            format += seconds + (seconds > 1 ? "s" : "s");
        }
        if (format.equals("")) {
            long rest = time / 100;
            if (rest == 0)
                rest = 1;
            format = "0." + rest + "s";
        }
        if (days > 0){
            format = days + "d";
        }
        return format;
    }
}
