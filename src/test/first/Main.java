package test.first;
//import org.firebirdsql.jdbc.FirebirdDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //Путь к базе данных. База данных должна уже существовать.
        String strDatabasePath = "/Users/Svetlanailina/Desktop/test_fire/EXAM.FDB";

        // По этому URL будет происходить подключение к базе данных.
        // Обратите внимание: URL содержит путь к базе данных.
        // URL действителен для firebird. Для других СУБД он будет другим.

        String strURL="jdbc:firebirdsql:localhost/3050:"+strDatabasePath;



        // Логин, с помощью которого подключаемся к базе данных.
        String strUser="SYSDBA";

        // Пароль.
        String strPassword="sysdba";

        // SQL запрос, который будет выполняться.
        String strSQL="SELECT * FROM SUBJECT ";





        try
        {
            // Инициализируемя Firebird JDBC driver.
            // Эта строка действительна только для Firebird.
            // Для других СУБД она будет немного видоизменена.
            // Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
            Class.forName("org.firebirdsql.jdbc.FirebirdDriver").newInstance();

        }
        catch(IllegalAccessException ex)
        {
            ex.printStackTrace();
        }
        catch(InstantiationException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        Connection conn=null;
        try
        {
            //Создаём подключение к базе данных
            conn = DriverManager.getConnection(
                    strURL,
                    strUser, strPassword);


            if (conn==null)
            {
                System.err.println("Нет подключения к БД ");
            }
            else {
                System.out.println("Есть подключение");
            }
            // Создаём класс, с помощью которого будут выполняться
            // SQL запросы.
            Statement stmt = conn.createStatement();

            //Выполняем SQL запрос.
            ResultSet rs = stmt.executeQuery(strSQL);

            // Смотрим количество колонок в результате SQL запроса.
            int nColumnsCount = rs.getMetaData().getColumnCount();

            // Выводим результат.
            while(rs.next())
            {
                System.out.println();
                for (int n=1;n < nColumnsCount+1;n++)
                {
                    Object obj = rs.getObject(n);
                    if (obj!=null)
                    {
                        System.out.print(obj+" | ");
                    }
                }
            }

            // Освобождаем ресурсы.
            stmt.close();

            conn.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }

}
