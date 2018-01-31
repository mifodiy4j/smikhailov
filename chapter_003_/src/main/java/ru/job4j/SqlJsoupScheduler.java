package ru.job4j;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author  Sergey Mikhailov
 * @since   1.0
 */
public class SqlJsoupScheduler {

    private int startHouse = 0;
    private int startMinute = 0;

    public int getStartHouse() {
        return startHouse;
    }

    public void setStartHouse(int startHouse) {
        this.startHouse = startHouse;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    /**
     * Метод считывает с файла sqlJsoupConfig.properties
     * значение времени (часы и минуты) в которое каждый день
     * раз в сутки будет производиться
     * анализ сайта sql.ru. По умолчанию 00:00
     */
    public void readPropertiesFile() {

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("sqlJsoupConfig.properties");
            prop.load(input);
            setStartHouse(Integer.parseInt(prop.getProperty("startHour")));
            setStartMinute(Integer.parseInt(prop.getProperty("startMinute")));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws SchedulerException {

        SqlJsoupScheduler sqlJsoupScheduler = new SqlJsoupScheduler();
        sqlJsoupScheduler.readPropertiesFile();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        try {
            JobDetail jobDetail = JobBuilder.newJob(SqlJsoup.class)
                    .withIdentity("myJob", "myGroup")
                    .build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                                                .withIdentity("myTrigger", "myGroup")
                                                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(
                                                        sqlJsoupScheduler.getStartHouse(),
                                                        sqlJsoupScheduler.getStartMinute()))
                                                .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
