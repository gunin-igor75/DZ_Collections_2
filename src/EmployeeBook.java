import java.util.*;

public class EmployeeBook {
    private Map<String, Employee> employeeBook;

    private static int id = 1;

    public EmployeeBook() {
        this.employeeBook = new HashMap<>();
    }

    public Map<String, Employee> getEmployeeBook() {
        return employeeBook;
    }

    public void setEmployeeBook(Map<String, Employee> employeeBook) {
        this.employeeBook = employeeBook;
    }

    // Минимальная зарплата по предприятию

    public Employee minimumSalary() {
        return getEmployeeBook().values().stream().min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // Минимальная зарплата по отделу

    public Employee minimumSalary(int department) {
        return getEmployeeBook().values().
                stream().
                filter(s -> s.getDepartment() == department).
                min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // Максимальная зарплата по предприятию

    public Employee maximumSalary() {
        return getEmployeeBook().values().stream().max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }
    // Максимальная зарплата по отделу

    public Employee maximumSalary(int department) {
        return getEmployeeBook().values().
                stream().
                filter(s -> s.getDepartment() == department).
                max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // Ощие затраты по предприятию
    public double costAmount() {
        return getEmployeeBook().values().stream().mapToDouble(Employee::getSalary).sum();

    }

    // Ощие затраты по отделу
    public double costAmount(int department) {
        return getEmployeeBook().values().
                stream().filter(s -> s.getDepartment() == department).
                mapToDouble(Employee::getSalary).sum();
    }

    // Средняя зарплата по предприятию

    public double avgSalary() {
        return (costAmount() / getEmployeeBook().size());
    }

    // Средняя зарплата по отделу
    public double avgSalary(int department) {
        int size = getEmployeeBook().values().stream().filter(s -> s.getDepartment() == department).toList().size();
        return (costAmount(department) / size);
    }

    // Индексация зарплаты по всему предприятию

    public void indexSalary(int percent) {
        double coefficient = (double) percent / 100 + 1;
        getEmployeeBook().forEach((key, value) -> value.setSalary(value.getSalary() * coefficient));
    }

    // Индексация зарплаты по отделам

    public void indexSalary(int percent, int department) {
        double coefficient = (double) percent / 100 + 1;
        getEmployeeBook().forEach((key, value) -> {
            if (value.getDepartment() == department) value.setSalary(value.getSalary() * coefficient);
        });
    }

    public void printMinimumSalaryTotal(double minimum) {
        List<Employee> employees = new ArrayList<>(getEmployeeBook().values().stream().
                filter(s -> s.getSalary() < minimum).toList());
        if (employees.isEmpty()) {
            System.out.println(("Сотрудников с зарплатой менее " + minimum + " на предприятии нет"));
        } else {
            System.out.printf("Сотрудники с зарплатой менее %.2f руб:\n", minimum);
            employees.forEach(System.out::println);
        }
    }

    // Печать сотрудников с зарплатов более или равной

    public void printMaximumSalaryTotal(double maximum) {
        List<Employee> employees = new ArrayList<>(getEmployeeBook().values().stream().
                filter(s -> s.getSalary() >= maximum).toList());
        if (employees.isEmpty()) {
            System.out.println(("Сотрудников с зарплатой равной или более " + maximum + " на предприятии нет"));
        } else {
            System.out.printf("Сотрудники с зарплатой равной или более %.2f руб:\n", maximum);
            employees.forEach(System.out::println);
        }
    }

    // Добавление сотрудника
    public void addEmployee(Employee employee) {
        if (employee != null && !employeeBook.containsKey(employee.getFullName())) {
            employee.setId(id);
            id++;
            employeeBook.put(employee.getFullName(), employee);
        }
    }
    // Удаление сотрудника по Ф.И.О.

    public void removeEmployee(String fulName) {
        if (fulName != null && employeeBook.containsKey(fulName)) {
            employeeBook.remove(fulName);
        }
    }

    // Удаление сотрудника по id

    public void removeEmployee(int id) {
        Employee employee = getEmployeeBook().values().
                stream().
                filter(s -> s.getId() == id).findFirst().orElse(null);
        if (employee == null) {
            System.out.println("Такого работника нет");
        } else {
            removeEmployee(employee.getFullName());
        }
    }

    // Изменение по Ф.И.О номера отдела
    public void setDepartment(String fullName, int department) {
        if (getEmployeeBook().containsKey(fullName)) {
            getEmployeeBook().get(fullName).setDepartment(department);
        } else {
            System.out.println(fullName + " не является нашим работником");
        }
    }

    // Изменение по Ф.И.О зарплаты
    public void setSalary(String fullName, double salary) {
        if (getEmployeeBook().containsKey(fullName)) {
            getEmployeeBook().get(fullName).setSalary(salary);
        } else {
            System.out.println(fullName + " не является нашим работником");
        }
    }

    // Печать Ф.И.О. всего предприятия

    public void printFullName() {
        getEmployeeBook().values().forEach(s -> System.out.println(s.getFullName()));
    }


    // Печать сотрудников
    public void print() {
        getEmployeeBook().values().forEach(System.out::println);
    }
    // Поиск сотрудника

    public Employee findEmployee(String fullName) {
        if (getEmployeeBook().containsKey(fullName)) {
            return getEmployeeBook().get(fullName);
        }
        return null;
    }
}
