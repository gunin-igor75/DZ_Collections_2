import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        EmployeeBook employeeBook = new EmployeeBook();


        Employee employee;
        String path = "src/test.txt";
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNext()) {
                String[] info = scanner.nextLine().split("\\s+");
                employee = new Employee(info[0], info[1], info[2], Integer.parseInt(info[3]), Double.parseDouble(info[4]));
                employeeBook.addEmployee(employee);
            }
        }
        // ���������� ������� ��� � ��� �������� ������� ��������
        double totalMonth = employeeBook.costAmount();
        System.out.printf("����� ������ �� �����������  %.2f ���.\n", totalMonth);
        System.out.println();
        double departmentMonth = employeeBook.costAmount(5);
        System.out.printf("����� ������ �� ������ �%d  %.2f ���.\n", 5, departmentMonth);
        System.out.println();
        System.out.println(employeeBook.minimumSalary());
        System.out.println();
        System.out.println(employeeBook.minimumSalary(3));
        System.out.println();
        System.out.println(employeeBook.maximumSalary());
        System.out.println();
        System.out.println(employeeBook.maximumSalary(4));
        System.out.println();
        double avgMonth = employeeBook.avgSalary();
        System.out.printf("������� ������ �� �����������  %.2f ���.\n", avgMonth);
        System.out.println();
        double departmentAvg = employeeBook.avgSalary(5);
        System.out.printf("������� ������ �� ������ �%d  %.2f ���.\n", 5, departmentAvg);
        System.out.println();
        employeeBook.indexSalary(10); // ���������� �/� �� �����������
        System.out.println();
        employeeBook.indexSalary(15, 2); // ���������� �/� �� ������
        System.out.println();
        employeeBook.printFullName(); //  �. �. �. ���� �����������
        System.out.println();
        employeeBook.print(); // ������ ���� ����������� �� ����� ���������� �� ��� �������
        System.out.println();
        employeeBook.printMinimumSalaryTotal(50000);
        System.out.println();
        employeeBook.printMaximumSalaryTotal(60000);
        System.out.println();
        System.out.println(employeeBook.maximumSalary());
        System.out.println(employeeBook.minimumSalary());

        System.out.println();
        employeeBook.addEmployee(new Employee("�������",
                "����", "����������",3, 55000));
        employeeBook.print();
        System.out.println();
        employeeBook.removeEmployee(10);
        employeeBook.print();
        System.out.println();
        employeeBook.removeEmployee("���������� ����� ��������");
        employeeBook.print();
        System.out.println();
        employeeBook.setDepartment("������ ���� ��������", 1);
        System.out.println(employeeBook.findEmployee("������ ���� ��������"));
        System.out.println();
        employeeBook.setSalary("������� ����� ��������", 70000);
        System.out.println(employeeBook.findEmployee("������� ����� ��������"));
    }
}