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
        System.out.println(n==生 =====);
        printStudentList(highScoreStudents);

         5. 统计各分数段人数
        countScoreRange(studentList);
    }

    
    
  
}
