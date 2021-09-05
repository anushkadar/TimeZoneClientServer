package org.me.dtd.repository;

import org.me.dtd.entity.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Repository
public class DateTimeJdbcTemplateDao extends JdbcTemplate {

    private static final String INSERT_SQL =
            "insert into date_tb (date_str, date, local_Time, local_date, local_datetime_dt, local_datetime_ts, offset_datetime, zoned_dateTime) "
                    + "values (?, ?, ?, ?, ?, ?, ?,?)";
    private static final String SELECT_SQL = "select * from date_tb where id = ?";

    public DateTimeJdbcTemplateDao(DataSource dataSource) {
        super(dataSource);
    }

    public Data save2(Data data) {
        Integer dataId = update(INSERT_SQL,
                new Object[] { data.getDateStr(), data.getDate(), data.getLocalTime(), data.getLocalDate(),
                        data.getLocalDateTimeDt(), data.getLocalDateTimeTs(), data.getOffsetDateTime(),
                        data.getZonedDateTime() });
        data.setId(dataId);
        return data;
    }

    public Data save(Data data) {
        KeyHolder holder = new GeneratedKeyHolder();
        update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, data.getDateStr());
            ps.setObject(2, data.getDate());
            ps.setObject(3, data.getLocalTime());
            ps.setObject(4, data.getLocalDate());
            ps.setObject(5, data.getLocalDateTimeDt());
            ps.setObject(6, data.getLocalDateTimeTs());
            ps.setObject(7, data.getOffsetDateTime());
            ps.setObject(8, data.getZonedDateTime().toOffsetDateTime());
            return ps;
        }, holder);

        int newUserId;
        if (holder.getKeys().size() > 1) {
            newUserId = (int) holder.getKeys().get("id");
        } else {
            newUserId = holder.getKey().intValue();
        }
        data.setId(newUserId);
        return data;
    }

    public Data get(Integer id) {
        return queryForObject(SELECT_SQL, this::mapRow, id);
    }

    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
        Data data = new Data();
        data.setId(rs.getInt("id"));
        data.setDateStr(rs.getString("date_str"));
        data.setDate(rs.getTimestamp("date"));
        data.setLocalTime(rs.getTime("local_Time").toLocalTime());
        data.setLocalDate(rs.getDate("local_date").toLocalDate());
        data.setLocalDateTimeDt(rs.getTimestamp("offset_datetime").toLocalDateTime());
        data.setLocalDateTimeTs(rs.getTimestamp("local_datetime_ts").toLocalDateTime());
        data.setOffsetDateTime(rs.getObject("offset_datetime", OffsetDateTime.class));
        data.setZonedDateTime(rs.getObject("zoned_datetime", ZonedDateTime.class));

        return data;
    }
}

