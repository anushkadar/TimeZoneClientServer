package org.me.dtd.repository;

import org.me.dtd.entity.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Repository
public class DateTimeJdbcDao {

    @Value("${spring.datasource.url}")
    private String dbName;

    private static final String INSERT_SQL =
            "insert into date_tb (date_str, date, local_Time, local_date, local_datetime_dt,local_datetime_ts, offset_datetime, zoned_dateTime) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SQL = "select * from date_tb where id = ?";
    Connection conn = null;
    PreparedStatement preparedStatement = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if(dbName.contains("mysql")){
            return getSqlConnection();
        }else{
            return getPostgresConnection();
        }
    }

    public Connection getSqlConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/samle_db";
        String username = "root";
        String password = "password";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }


    public static Connection getPostgresConnection() throws ClassNotFoundException, SQLException {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/samle_db";
        String username = "root";
        String password = "password";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }


    public Data save(Data data) {
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, data.getDateStr());
            preparedStatement.setObject(2, data.getDate());
            preparedStatement.setObject(3, data.getLocalTime());
            preparedStatement.setObject(4, data.getLocalDate());
            preparedStatement.setObject(5, data.getLocalDateTimeDt());
            preparedStatement.setObject(6, data.getLocalDateTimeTs());
            preparedStatement.setObject(7, data.getOffsetDateTime());
            preparedStatement.setObject(8, data.getZonedDateTime());
            // Execute statement and return the number of rows affected
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    data.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return data;
    }

    public Data get(Integer id) {
        Data data = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SELECT_SQL);
            preparedStatement.setObject(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                data = new Data();
                data.setId(rs.getInt("id"));
                data.setDateStr(rs.getString("date_str"));
                data.setDate(rs.getObject("date", Date.class));
                data.setLocalTime(rs.getObject("local_Time", LocalTime.class));
                data.setLocalDate(rs.getObject("local_date", LocalDate.class));
                data.setLocalDateTimeDt(rs.getObject("local_datetime_dt", LocalDateTime.class));
                data.setLocalDateTimeTs(rs.getObject("local_datetime_ts", LocalDateTime.class));
                data.setOffsetDateTime(rs.getObject("offset_datetime", OffsetDateTime.class));
                data.setZonedDateTime(rs.getObject("zoned_datetime", ZonedDateTime.class));
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return data;
    }
}

