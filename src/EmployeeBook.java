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

    // ����������� �������� �� �����������

    public Employee minimumSalary() {
        return getEmployeeBook().values().stream().min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // ����������� �������� �� ������

    public Employee minimumSalary(int department) {
        return getEmployeeBook().values().
                stream().
                filter(s -> s.getDepartment() == department).
                min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // ������������ �������� �� �����������

    public Employee maximumSalary() {
        return getEmployeeBook().values().stream().max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }
    // ������������ �������� �� ������

    public Employee maximumSalary(int department) {
        return getEmployeeBook().values().
                stream().
                filter(s -> s.getDepartment() == department).
                max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    // ���� ������� �� �����������
    public double costAmount() {
        return getEmployeeBook().values().stream().mapToDouble(Employee::getSalary).sum();

    }

    // ���� ������� �� ������
    public double costAmount(int department) {
        return getEmployeeBook().values().
                stream().filter(s -> s.getDepartment() == department).
                mapToDouble(Employee::getSalary).sum();
    }

    // ������� �������� �� �����������

    public double avgSalary() {
        return (costAmount() / getEmployeeBook().size());
    }

    // ������� �������� �� ������
    public double avgSalary(int department) {
        int size = getEmployeeBook().values().stream().filter(s -> s.getDepartment() == department).toList().size();
        return (costAmount(department) / size);
    }

    // ���������� �������� �� ����� �����������

    public void indexSalary(int percent) {
        double coefficient = (double) percent / 100 + 1;
        getEmployeeBook().forEach((key, value) -> value.setSalary(value.getSalary() * coefficient));
    }

    // ���������� �������� �� �������

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
            System.out.println(("����������� � ��������� ����� " + minimum + " �� ����������� ���"));
        } else {
            System.out.printf("���������� � ��������� ����� %.2f ���:\n", minimum);
            employees.forEach(System.out::println);
        }
    }

    // ������ ����������� � ��������� ����� ��� ������

    public void printMaximumSalaryTotal(double maximum) {
        List<Employee> employees = new ArrayList<>(getEmployeeBook().values().stream().
                filter(s -> s.getSalary() >= maximum).toList());
        if (employees.isEmpty()) {
            System.out.println(("����������� � ��������� ������ ��� ����� " + maximum + " �� ����������� ���"));
        } else {
            System.out.printf("���������� � ��������� ������ ��� ����� %.2f ���:\n", maximum);
            employees.forEach(System.out::println);
        }
    }

    // ���������� ����������
    public void addEmployee(Employee employee) {
        if (employee != null && !employeeBook.containsKey(employee.getFullName())) {
            employee.setId(id);
            id++;
            employeeBook.put(employee.getFullName(), employee);
        }
    }
    // �������� ���������� �� �.�.�.

    public void removeEmployee(String fulName) {
        if (fulName != null && employeeBook.containsKey(fulName)) {
            employeeBook.remove(fulName);
        }
    }

    // �������� ���������� �� id

    public void removeEmployee(int id) {
        Employee employee = getEmployeeBook().values().
                stream().
                filter(s -> s.getId() == id).findFirst().orElse(null);
        if (employee == null) {
            System.out.println("������ ��������� ���");
        } else {
            removeEmployee(employee.getFullName());
        }
    }

    // ��������� �� �.�.� ������ ������
    public void setDepartment(String fullName, int department) {
        if (getEmployeeBook().containsKey(fullName)) {
            getEmployeeBook().get(fullName).setDepartment(department);
        } else {
            System.out.println(fullName + " �� �������� ����� ����������");
        }
    }

    // ��������� �� �.�.� ��������
    public void setSalary(String fullName, double salary) {
        if (getEmployeeBook().containsKey(fullName)) {
            getEmployeeBook().get(fullName).setSalary(salary);
        } else {
            System.out.println(fullName + " �� �������� ����� ����������");
        }
    }

    // ������ �.�.�. ����� �����������

    public void printFullName() {
        getEmployeeBook().values().forEach(s -> System.out.println(s.getFullName()));
    }


    // ������ �����������
    public void print() {
        getEmployeeBook().values().forEach(System.out::println);
    }
    // ����� ����������

    public Employee findEmployee(String fullName) {
        if (getEmployeeBook().containsKey(fullName)) {
            return getEmployeeBook().get(fullName);
        }
        return null;
    }
}
