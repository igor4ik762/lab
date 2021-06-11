package Task;

import java.io.Serializable;
import java.sql.Time;
import java.util.Scanner;

public final class Working_day extends Doctor implements Serializable {
    Date date;                      //дата
    int number_of_patients;         //зміна
    Time time;                      //кількість відвідувачів

    //конструктор без параметрів
    public Working_day() {
        date = new Date();
        number_of_patients = 0;
        time = new Time(0, 0, 0);
    }

    //конструктор з параметрами
    public Working_day(int d, int m, int y, int number_of_patients, int hours, int minutes) {
        this.date = new Date(d,m,y);
        this.number_of_patients = number_of_patients;
        this.time = new Time(hours, minutes, 0);
    }

    //конструктор з параметрами
    public Working_day(String surname, String profession, int d, int m, int y, int number_of_patients, int hours, int minutes) {
        super(surname, profession);
        this.date = new Date(d,m,y);
        this.number_of_patients = number_of_patients;
        this.time = new Time(hours, minutes, 0);
    }

    //метод для вводу даних
    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Прізвище : ");
        surname = scanner.nextLine();
        System.out.print("Фах : ");
        profession = scanner.nextLine();
        System.out.println("Дату : ");
        date.inputDate();
        System.out.print("Кількість пацієнтів : ");
        number_of_patients = scanner.nextInt();
        System.out.print("Час початку : ");
        System.out.print("Час : ");
        int hours = scanner.nextInt();
        System.out.print("Мінути : ");
        int minutes = scanner.nextInt();
        time = new Time(hours, minutes, 0);
    }

    //метод для виводу даних
    @Override
    public void output() {
        System.out.println("Прізвище - " + surname);
        System.out.println("Спеціальність - " + profession);
        System.out.println("Дата - " + date.toString());
        System.out.println("Кількість пацієнтів - " + number_of_patients);
        System.out.println("Час початку - " + time);
    }

    //перевизначення методу toString()
    @Override
    public String toString() {
        return surname + '\t' +
                profession + '\t' +
                date + '\t' +
                number_of_patients + '\t' +
                time;
    }

    public String to_string() {
        return surname + '\n' +
                profession + '\n' +
                date.to_string() + '\n' +
                number_of_patients + '\n' +
                time.getDay() + '\n' +
                time.getMinutes() + '\n'
                ;
    }

    //гетери
    public Date getDate() {
        return date;
    }
    public int getNumber_of_patients() {
        return number_of_patients;
    }
    public Time getTime() {
        return time;
    }

    //сетери
    public void setDate(Date date) {
        this.date = date;
    }
    public void setNumber_of_patients(int number_of_patients) {
        this.number_of_patients = number_of_patients;
    }
    public void setTime(Time time) {
        this.time = time;
    }
}