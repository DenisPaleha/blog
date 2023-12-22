//package org.paleha.blog.logger;
//
//import org.paleha.blog.exceptions.LoggerException;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static java.lang.Integer.parseInt;
//
//
//public class LoadLogger extends AbstractLogger {
//
//    private AbstractLogger loggerAm;
//    private AbstractLogger loggerPm;
//
//    public LoadLogger(AbstractLogger loggerAm, AbstractLogger loggerPm) {
//        this.loggerAm = loggerAm;
//        this.loggerPm = loggerPm;
//    }
//
//    public void logOutput(String prefix, String result) throws LoggerException {
//        if (isAm()) {
//            this.loggerAm.logOutput(prefix, result);
//        } else {
//            this.loggerPm.logOutput(prefix, result);
//        }
//    }
//
//    private static boolean isAm() {
//        String time;
////        boolean amTime;
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH"); // "yy-MM-dd_HH-mm-ss"
//        time = now.format(formatter);
//        return parseInt(time) < 12;
//    }
//}
