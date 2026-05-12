import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 学生信息管理系统
 * 功能：添加学生、删除学生、查询学生、展示所有学生、退出系统
 */
public class StudentManagement {
    // 学生列表存储所有学生信息
    private final List<Student> studentList;
    // 控制台输入对象
    private final Scanner scanner;

    /**
     * 构造方法：初始化学生列表和输入器
     */
    public StudentManagement() {
        this.studentList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * 启动管理系统
     */
    public void startSystem() {
        System.out.println("===== 欢迎使用学生信息管理系统 =====");
        // 系统主循环
        while (true) {
            showMainMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                executeMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("输入错误！请输入有效数字！");
            } catch (Exception e) {
                System.out.println("系统异常：" + e.getMessage());
            }
        }
    }

    /**
     * 显示主菜单
     */
    private void showMainMenu() {
        System.out.println("\n请选择操作：");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 删除学生信息");
        System.out.println("3. 根据学号查询学生");
        System.out.println("4. 展示所有学生信息");
        System.out.println("5. 退出系统");
        System.out.print("请输入选项：");
    }

    /**
     * 执行菜单选择
     * @param choice 用户输入的选项
     */
    private void executeMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                deleteStudent();
                break;
            case 3:
                queryStudentById();
                break;
            case 4:
                showAllStudents();
                break;
            case 5:
                exitSystem();
                break;
            default:
                System.out.println("无效选项，请重新输入！");
        }
    }

    /**
     * 添加学生功能
     */
    private void addStudent() {
        System.out.println("\n----- 添加学生信息 -----");
        System.out.print("请输入学生学号：");
        String id = scanner.nextLine().trim();

        // 校验学号是否重复
        if (isStudentIdExists(id)) {
            System.out.println("该学号已存在，添加失败！");
            return;
        }

        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine().trim();
        System.out.print("请输入学生年龄：");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
            if (age <= 0 || age > 150) {
                System.out.println("年龄输入不合法！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("年龄必须为数字！");
            return;
        }

        System.out.print("请输入学生性别：");
        String gender = scanner.nextLine().trim();
        System.out.print("请输入学生专业：");
        String major = scanner.nextLine().trim();

        // 创建学生对象并添加到列表
        Student student = new Student(id, name, age, gender, major);
        studentList.add(student);
        System.out.println("学生信息添加成功！");
    }

    /**
     * 校验学号是否存在
     * @param id 学号
     * @return 存在返回true，否则false
     */
    private boolean isStudentIdExists(String id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除学生功能
     */
    private void deleteStudent() {
        System.out.println("\n----- 删除学生信息 -----");
        if (studentList.isEmpty()) {
            System.out.println("暂无学生信息，无法删除！");
            return;
        }

        System.out.print("请输入要删除的学生学号：");
        String id = scanner.nextLine().trim();
        boolean isDeleted = false;

        // 遍历删除对应学号的学生
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                studentList.remove(i);
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            System.out.println("学生信息删除成功！");
        } else {
            System.out.println("未找到该学号的学生！");
        }
    }

    /**
     * 根据学号查询学生
     */
    private void queryStudentById() {
        System.out.println("\n----- 查询学生信息 -----");
        if (studentList.isEmpty()) {
            System.out.println("暂无学生信息！");
            return;
        }

        System.out.print("请输入要查询的学生学号：");
        String id = scanner.nextLine().trim();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out.println("查询到学生信息：");
                System.out.println(student);
                return;
            }
        }
        System.out.println("未查询到该学生信息！");
    }

    /**
     * 展示所有学生信息
     */
    private void showAllStudents() {
        System.out.println("\n----- 所有学生信息 -----");
        if (studentList.isEmpty()) {
            System.out.println("暂无学生信息！");
            return;
        }

        // 遍历输出所有学生
        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println("------------------------");
        System.out.println("总学生数量：" + studentList.size());
    }

    /**
     * 退出系统
     */
    private void exitSystem() {
        System.out.println("感谢使用学生信息管理系统，再见！");
        scanner.close();
        System.exit(0);
    }

    /**
     * 主方法：程序入口
     */
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        management.startSystem();
    }
}

/**
 * 学生实体类
 * 封装学生的学号、姓名、年龄、性别、专业
 */
class Student {
    private final String id;       // 学号
    private final String name;     // 姓名
    private final int age;         // 年龄
    private final String gender;   // 性别
    private final String major;    // 专业

    /**
     * 全参构造方法
     */
    public Student(String id, String name, int age, String gender, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.major = major;
    }

    // getter方法
    public String getId() {
        return id;
    }

    /**
     * 重写toString方法，方便打印学生信息
     */
    @Override
    public String toString() {
        return "学生{" +
                "学号='" + id + '\'' +
                ", 姓名='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ", 专业='" + major + '\'' +
                '}';
    }
}