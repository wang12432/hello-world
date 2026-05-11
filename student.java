import java.util.ArrayList;
import java.util.List;
import java.util.Random;


  学生成绩管理工具
  包含学生对象定义、成绩生成、统计、筛选功能
 
public class StudentScoreManager {
     常量定义
    private static final int STUDENT_COUNT = 30;
    private static final int MIN_SCORE = 60;
    private static final int MAX_SCORE = 100;

    public static void main(String[] args) {
         1. 创建学生列表
        ListStudent studentList = new ArrayList();

         2. 批量生成学生数据
        generateStudentData(studentList);
        System.out.println(===== 所有学生信息 =====);
        printStudentList(studentList);

         3. 计算平均分
        double averageScore = calculateAverageScore(studentList);
        System.out.println(n班级平均分： + String.format(%.2f, averageScore));

         4. 筛选高分学生（90分及以上）
        ListStudent highScoreStudents = filterHighScoreStudents(studentList);
        System.out.println(n===== 90分以上优秀学生 =====);
        printStudentList(highScoreStudents);

         5. 统计各分数段人数
        countScoreRange(studentList);
    }

    
      生成指定数量的随机学生数据
      @param studentList 学生集合
     
    private static void generateStudentData(ListStudent studentList) {
        Random random = new Random();
        for (int i = 1; i = STUDENT_COUNT; i++) {
            String name = 学生 + i;
            int score = MIN_SCORE + random.nextInt(MAX_SCORE - MIN_SCORE + 1);
            Student student = new Student(i, name, score);
            studentList.add(student);
        }
    }

    
      打印学生列表
     
    private static void printStudentList(ListStudent studentList) {
        if (studentList.isEmpty()) {
            System.out.println(暂无学生数据);
            return;
        }
        for (Student student  studentList) {
            System.out.println(学号： + student.getId()
                    + ，姓名： + student.getName()
                    + ，成绩： + student.getScore());
        }
    }

    
      计算班级平均分
     
    private static double calculateAverageScore(ListStudent studentList) {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        int totalScore = 0;
        for (Student student  studentList) {
            totalScore += student.getScore();
        }
        return (double) totalScore  studentList.size();
    }

    
      筛选90分以上学生
     
    private static ListStudent filterHighScoreStudents(ListStudent studentList) {
        ListStudent highScoreList = new ArrayList();
        for (Student student  studentList) {
            if (student.getScore() = 90) {
                highScoreList.add(student);
            }
        }
        return highScoreList;
    }

    
      统计分数段人数
     
    private static void countScoreRange(ListStudent studentList) {
        int excellent = 0, good = 0, pass = 0;
        for (Student student  studentList) {
            int score = student.getScore();
            if (score = 90) {
                excellent++;
            } else if (score = 80) {
                good++;
            } else {
                pass++;
            }
        }
        System.out.println(n===== 分数段统计 =====);
        System.out.println(优秀(90+)： + excellent +  人);
        System.out.println(良好(80-89)： + good +  人);
        System.out.println(及格(60-79)： + pass +  人);
    }

    
      学生内部类
     
    static class Student {
        private int id;
        private String name;
        private int score;

        public Student(int id, String name, int score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

         getter方法

      1232123123123123dfgjdshgsd
        public int getId() { return id; }
        public String getName() { return name; }
        public int getScore() { return score; }
    }
}
