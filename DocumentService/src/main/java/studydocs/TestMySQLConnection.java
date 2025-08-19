//package studydocs;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//
//@SpringBootApplication
//public class TestMySQLConnection implements CommandLineRunner {
//
//    private final DataSource dataSource;
//
//    public TestMySQLConnection(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(TestMySQLConnection.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        try (Connection conn = dataSource.getConnection()) {
//            System.out.println("✅ Kết nối MySQL thành công!");
//            System.out.println("Database: " + conn.getCatalog());
//        } catch (Exception e) {
//            System.err.println("❌ Lỗi kết nối MySQL: " + e.getMessage());
//        }
//    }
//}
