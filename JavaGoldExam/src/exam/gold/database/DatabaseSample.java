package exam.gold.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSample {

    public static void main(String[] args) {
        String url = "jdbc:h2:mem:test";
        try (Connection con = DriverManager.getConnection(url)) {
            if (con == null) {
                System.out.println("null");
            }
            DatabaseMetaData metaData = con.getMetaData();
            boolean isINSENTINVE = metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            boolean isSENTINVE = metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
            boolean isUpdatebale = metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.printf("INSETIVE %b , SENTIVE %b; UPDATABLE %b\n", isINSENTINVE, isSENTINVE, isUpdatebale);

            try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

                String create = "CREATE TABLE sample (sample_id int, sample_name varchar(10),PRIMARY KEY (sample_id));";
                stmt.execute(create);
                //行を追加する
                String insert1 = "INSERT INTO sample(sample_id,sample_name) VALUES (10,'Atom');";
                String insert2 = "INSERT INTO sample(sample_id,sample_name) VALUES (15,'Uran');";
                String insert3 = "INSERT INTO sample(sample_id,sample_name) VALUES (21,'Cobalt');";
                stmt.execute(insert1);
                stmt.execute(insert2);
                stmt.execute(insert3);

                String select = "SELECT * FROM sample";

                try (ResultSet rs = stmt.executeQuery(select)) {
                    while (rs.next()) {
                        showRow(rs);
                    }

                    showSplit();
                    rs.first();
                    showRow(rs);
                    rs.absolute(3);
                    showRow(rs);
                    showSplit();
                    //行の更新
                    rs.updateInt("SAMPLE_ID", 24);
                    rs.updateRow();
                    showRow(rs);
                    showSplit();

                    //行の追加
                    rs.moveToInsertRow();
                    rs.updateInt("sample_id", 80);
                    rs.updateString("sample_name", "Leo");
                    rs.insertRow();
                    rs.beforeFirst();
                    showSplit();
                    //行の削除

                    rs.absolute(2);
                    rs.deleteRow();
                    showRow(rs);
                    showSplit();

                    //INSENTIVEなので、追加、削除した行はResultSetに反映されない
                    rs.beforeFirst();
                    while (rs.next()) {
                        showRow(rs);
                    }
                }
                showSplit();

                //取り直すと反映される
                try (ResultSet rs = stmt.executeQuery(select)) {
                    while (rs.next()) {
                        showRow(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void showRow(ResultSet rs) throws SQLException {
        System.out.printf("RowId:%d,id:%d,name:%s \r\n", rs.getRow(), rs.getInt(1),
                rs.getString("SAMPLE_NAME"));
    }

    private static void showSplit() {
        System.out.println("------------------");
    }

}
