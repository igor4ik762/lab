package Task;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class Task {
    ArrayList<Working_day> workingday;

    public Task() throws IOException {
        workingday = new ArrayList<Working_day>();
        download();
    }

    public void add() throws IOException {
        Working_day new_workingday = new Working_day();
        System.out.println("\nВведіть інформацію :");
        new_workingday.input();
        workingday.add(new_workingday);

        System.out.println("\nЗапись додана!");

        this.save();
    }

    public void edit() throws IOException {
        count_note();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть порядковий номер, який ви хочете редагувати : ");
        String line = scanner.nextLine();
        if (!line.matches("\\d+") || Integer.parseInt(line) <= 0 || Integer.parseInt(line) > workingday.size()) {
            System.err.println("Не вірно введений номер!");
            this.edit();
            return;
        }
        int nomer = Integer.parseInt(line);
        nomer--;

        System.out.println("\nВідредагуйте :");
        workingday.get(nomer).input();
        System.out.println("\nРедагування пройшло успішно!");

        this.save();
    }

    public void print() throws IOException {
        count_note();

        int i = 0;
        System.out.println("\n--------Інформація :--------");
        for (var p :
                workingday) {
            System.out.println("\nРобочий день №" + (i + 1) + " :");
            p.output();
            i++;
        }
        System.out.println("\n---------------------------------------");
    }

    public void search() throws IOException {
        count_note();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть прізвище доктора, яке ви шукаєте : ");
        String surname = scanner.nextLine();

        boolean flag = false;
        int i = 0;
        for (var p :
                workingday) {
            if (p.getSurname().equals(surname)) {
                if (flag == false) {
                    System.out.println("\nРезультат : ");
                    flag = true;
                }
                System.out.println("Робочий день №" + (i + 1) + " :");
                p.output();
                System.out.println();
                i++;
            }
        }

        if (flag == false) {
            System.out.println("Пошук не вдалий!");
        }
    }

    public void sort() throws IOException {
        count_note();
        ArrayList<Working_day> sortmovies = new ArrayList<Working_day>(workingday);
        Scanner scanner = new Scanner(System.in);
        for(;;) {
            System.out.print("\nВведіть параметр по якому відбудеться сортування : ");
            String commnd = scanner.nextLine();
            switch (commnd) {
                case "surname":
                    sortmovies.sort(Comparator.comparing(Working_day::getSurname));
                    break;
                case "profession":
                    sortmovies.sort(Comparator.comparing(Working_day::getProfession));
                    break;
                case "date":
                    Working_day[] arr = sort_date();

                    int i = 0;
                    System.out.println("\n--------Відсортований список :--------");
                    for (var p :
                            arr) {
                        System.out.println("\nПрийом №" + (i + 1) + " :");
                        p.output();
                        i++;
                    }
                    System.out.println("\n--------------------------------------");
                    return;
                case "patients":
                    sortmovies.sort(Comparator.comparing(Working_day::getNumber_of_patients));
                    break;
                case "time":
                    sortmovies.sort(Comparator.comparing(Working_day::getTime));
                    break;
                case "help":
                    System.out.println("\nsurname - по прізвищу" + "\n" +
                            "profession - по спеціальності" + "\n" +
                            "date - по даті" + "\n" +
                            "patients - по кількості пацієнтів" + "\n" +
                            "time - по часу");
                    continue;
                default:
                    System.out.println("Не вірно введений параметр сортування(help-допомога). Введіть ще раз :");
                        continue;
            }
            break;
        }

        int i = 0;
        System.out.println("\n--------Відсортований список :--------");
        for (var p :
                sortmovies) {
            System.out.println("\nРобочий день №" + (i + 1) + " :");
            p.output();
            i++;
        }
        System.out.println("\n--------------------------------------");
    }

    public void delete() throws IOException {
        count_note();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть порядковий номер, який ви хочете видалити : ");
        String line = scanner.nextLine();
        if (!line.matches("\\d+") || Integer.parseInt(line) <= 0 || Integer.parseInt(line) > workingday.size()) {
            System.err.println("Не вірно введений номер!");
            this.delete();
            return;
        }
        int nomer = Integer.parseInt(line);
        nomer--;

        workingday.remove(nomer);
        System.out.println("\nВидалення пройшло успішно!");

        this.save();
    }

    /*
    1)Загальна кількість відвідувачів
    2)Прийом з мінімальною кількістю відвідувачів
    3)Довжина прізвища
    */
    public void task() throws IOException {
        count_note();

        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n1.Завдання 1" + "\n" +
                    "2.Завдання 2" + "\n" +
                    "3.Завдання 3" + "\n" +
                    "4.Вернутись назад" + "\n" +
                    "Введіть число : ");
            int nomer = scanner.nextInt();

            if (nomer < 1 || nomer > 4) {
                System.out.println("Не вірно введене число!! Введіть ще раз :");
                continue;
            }

            switch (nomer) {
                case 1:
                    task_1();
                    break;
                case 2:
                    task_2();
                    break;
                case 3:
                    task_3();
                    break;
                case 4:
                    return;
            }
        }
    }

    private void task_1() throws IOException {
        try {
            Date beg_date = new Date();
            Date end_date = new Date();
            System.out.println("\nВведіть першу дату :");
            beg_date.inputDate();
            System.out.println("\nВведіть другу дату :");
            end_date.inputDate();

            if (beg_date.better(end_date))
                throw new IOException("Перша дата не може бути бульшою за другу!");

            int count_patients = 0;
            int count_day = 0;
            for (Working_day day : workingday) {
                if (!beg_date.better(day.date) || beg_date.equally(day.date))
                    if (end_date.better(day.date) || end_date.equally(day.date)) {
                        count_patients += day.number_of_patients;
                        count_day++;
                    }
            }

            if (count_day == 0)
                throw new IOException("В заданий період не було найдено записів!");

            double result = (double) count_patients / count_day;

            System.out.println("Середня кількість пацієнтів в день за період(" + beg_date.toString() + " - " + end_date.toString() + ") = " + result);
            System.out.println();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void task_2() throws IOException {
        count_note();

        int max = workingday.get(0).number_of_patients;
        int count_day = 0;
        for (var p : workingday) {
            if(p.number_of_patients == max)
                count_day++;
            else if(p.number_of_patients > max){
                max = p.number_of_patients;
                count_day = 1;
            }
        }

        System.out.println("Кількість днів з максимальною кількістю пацієнтів(" + max + ") = " + count_day);
        System.out.println();
    }

    private void task_3() throws IOException {

    }

    private Working_day[] sort_date() {
        Working_day[] arr = new Working_day[workingday.size()];
        arr = workingday.toArray(arr);
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j].getDate().better(arr[j + 1].getDate())) {
                    Working_day temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

    private void count_note() throws IOException {
        if (workingday.size() == 0)
            throw new IOException("Записів нема!");
    }


    /* Цей метод перевіряє чи файл існує
     * вказаному за шляхом {@param filepath}
     *
     * @param filepath шлях до файлу
     * @throws IOException якщо файлу по шляху {@code filepath} не існує
     */
    private static void isFile(String filepath) throws IOException {
        //перевірка чи файл існує
        File file = new File(filepath);
        if (!file.isFile()) //throw new IOException("Файл не знайдено!");
            file.createNewFile();
    }

    /*Цей метод записує у файл за шляхом {@param filepath}
     * текст {@param text}
     *
     * @param filepath шлях до файлу
     * @throws IOException якщо файлу по шляху {@code filepath} не існує
     */
    public void write_to_file() throws IOException {
        isFile("Working_days.txt");

        BufferedWriter output = new BufferedWriter(new FileWriter("Working_days.txt"));
        for (var p :
                workingday) {
            output.write(p.to_string());
        }
        output.flush();
        output.close();
        output.close();
    }

    private void save() {
        try {
            isFile("Working_days.dat");
            FileOutputStream fos = new FileOutputStream("Working_days.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(workingday);
            oos.close();
            fos.close();
        }
        catch(Exception ex) {
            System.err.println("Виникла якась помилка!");
        }
    }

    private void download() {
        try {
            isFile("Working_days.dat");
            FileInputStream fis = new FileInputStream("Working_days.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            workingday = (ArrayList<Working_day>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
