package ru.gb.mikenord.homework05;

public class Employee {
    private String firstLastName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee(String firstLastName, String position, String email, String phoneNumber, int salary, int age) {
        this.firstLastName = firstLastName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void printEmployeeInfo() {
        System.out.print("Сотрудник - " + firstLastName + ", " + age + " ");
        switch (age % 10) {
            case 1:
                System.out.println("год");
                break;
            case 2:
            case 3:
            case 4:
                System.out.println("года");
                break;
            default:
                System.out.println("лет");
        }
        System.out.printf(" - должность: %s  (%d руб.)\n", position, salary);
        System.out.printf(" - E-mail: %s,  тел.: %s\n\n", email, phoneNumber);
    }

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Сергей Иванов", "Начальник отдела",
                        "sivanov@firm.ru", "84957006050", 120000, 44),
                new Employee("Никита Михайлов", "Заместитель начальника отдела",
                        "nmikhailov@firm.ru", "84957006051", 90000, 33),
                new Employee("Александр Сидоров", "Курьер",
                        "asidorov@firm.ru", "84957006059", 35000, 27),
                new Employee("Виктор Петров", "Старший менеджер",
                        "vpetrov@firm.ru", "84957006055", 70000, 40),
                new Employee("Елена Максимова", "Главный бухгалтер",
                        "emaksimova@firm.ru", "84957006080", 85000, 41)
        };

        for (Employee employee : employees) {
            if (employee.age > 40) {
                employee.printEmployeeInfo();
            }
        }
    }
}
