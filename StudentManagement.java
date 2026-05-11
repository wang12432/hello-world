import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 学生信息管理系统（优化版）
 * 功能：增删查展示学生，控制台交互，数据校验，安全退出
 */
public class StudentManagementSystem {
    // 学生集合
    private final List<Student> studentList;
    private final Scanner scanner;

    // 构造器初始化
    public StudentManagementSystem() {
        this.studentList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * 系统启动入口
     */
    public void start() {
        System.out.println("===== 欢迎使用优化版学生信息管理系统 =====");
        while (true) {
            showMenu();
            handleMenuInput();
        }
    }

    /**
     * 展示主菜单
     */
    private void showMenu() {
        System.out.println("\n-------- 主菜单 --------");
        System.out.println("1. 添加学生");
        System.out.println("2. 删除学生");
        System.out.println("3. 根据学号查询学生");
        System.out.println("4. 查看所有学生");
        System.out.println("5. 退出系统");
        System.out.print("请输入操作序号：");
    }

    /**
     * 处理菜单输入
     */
    private void handleMenuInput() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            executeOperation(choice);
        } catch (NumberFormatException e) {
            System.out.println("❌ 输入错误，请输入数字！");
        } catch (Exception e) {
            System.out.println("❌ 系统异常：" + e.getMessage());
        }
    }

    /**
     * 执行对应操作
     */
    private void executeOperation(int choice) {
        switch (choice) {
            case 1 -> addStudent();
            case 2 -> deleteStudent();
            case 3 -> queryStudentById();
            case 4 -> showAllStudents();
            case 5 -> exitSystem();
            default -> System.out.println("❌ 无效选项，请重新输入！");
        }
    }

    /**
     * 添加学生（优化版）
     */
    private void addStudent() {
        System.out.println("\n----- 添加学生 -----");

        // 学号输入与校验
        String id = inputStudentId();
        if (isIdExist(id)) {
            System.out.println("❌ 该学号已存在！");
            return;
        }

        // 基础信息输入
        String name = inputNotEmpty("请输入姓名：");
        Integer age = inputValidAge();
        String gender = inputNotEmpty("请输入性别：");
        String major = inputNotEmpty("请输入专业：");

        // 封装对象
        Student student = new Student(id, name, age, gender, major);
        studentList.add(student);
        System.out.println("✅ 学生添加成功！");
    }

    /**
     * 删除学生
     */
    private void deleteStudent() {
        System.out.println("\n----- 删除学生 -----");

        if (studentList.isEmpty()) {
            System.out.println("❌ 暂无学生数据！");
            return;
        }

        String id = inputStudentId();
        boolean removed = studentList.removeIf(s -> s.getId().equals(id));

        if (removed) {
            System.out.println("✅ 删除成功！");
        } else {
            System.out.println("❌ 未找到该学号学生！");
        }
    }

    /**
     * 根据学号查询
     */
    private void queryStudentById() {
        System.out.println("\n----- 查询学生 -----");

        if (studentList.isEmpty()) {
            System.out.println("❌ 暂无学生数据！");
            return;
        }

        String id = inputStudentId();
        Student student = findStudentById(id);

        if (student != null) {
            System.out.println("查询成功：");
            System.out.println(student);
        } else {
            System.out.println("❌ 未找到该学生！");
        }
    }

    /**
     * 展示所有学生
     */
    private void showAllStudents() {
        System.out.println("\n----- 学生列表 -----");

        if (studentList.isEmpty()) {
            System.out.println("📭 暂无学生信息");
            return;
        }

        studentList.forEach(System.out::println);
        System.out.println("------------------------");
        System.out.println("📊 总人数：" + studentList.size());
    }

    // ==================== 工具方法（提取复用逻辑） ====================

    /**
     * 输入并返回一个非空字符串
     */
    private String inputNotEmpty(String tip) {
        String content;
        do {
            System.out.print(tip);
            content = scanner.nextLine().trim();
            if (content.isEmpty()) {
                System.out.println("❌ 内容不能为空！");
            }
        } while (content.isEmpty());
        return content;
    }

    /**
     * 输入合法年龄
     */
    private Integer inputValidAge() {
        Integer age;
        while (true) {
            System.out.print("请输入年龄：");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age > 0 && age <= 150) {
                    return age;
                }
                System.out.println("❌ 年龄必须在 1~150 之间");
            } catch (NumberFormatException e) {
                System.out.println("❌ 请输入有效数字");
            }
        }
    }

    /**
     * 输入学号
     */
    private String inputStudentId() {
        return inputNotEmpty("请输入学号：");
    }

    /**
     * 判断学号是否存在
     */
    private boolean isIdExist(String id) {
        return studentList.stream().anyMatch(s -> s.getId().equals(id));
    }

    /**
     * 根据ID查找学生
     */
    private Student findStudentById(String id) {
        return studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * 安全退出
     */
    private void exitSystem() {
        System.out.println("👋 感谢使用，再见！");
        scanner.close();
        System.exit(0);
    }

    // ==================== 主方法 ====================
    public static void main(String[] args) {
        new StudentManagementSystem().start();
    }
}

/**
 * 学生实体类（优化：不可变对象，规范结构）
 */
class Student {
    private final String id;
    private final String name;
    private final int age;
    private final String gender;
    private final String major;

    public Student(String id, String name, int age, String gender, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "学号='" + id + '\'' +
                ", 姓名='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ", 专业='" + major + '\'' +
                '}';
    }
}