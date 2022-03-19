package com.tedu.mycalcitetest;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;

import javax.xml.validation.Schema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class TestApp {
    // 通过java创建一个具有类似表结构的数据
    public static class HrSchema {
        public final Employee[] emps = new Employee[2];
        public final Department[] depts = new Department[2];
    }
    public static class Department {
        public Integer deptno;
        public Department(Integer deptno) {
            this.deptno = deptno;
        }
    }
    public static class Employee {
        public Integer empid;
        public Integer deptno;
        public Employee(Integer empid, Integer deptno) {
            this.empid = empid;
            this.deptno = deptno;
        }
    }
    // 通过该方法来添加一些数据，并返回这个集合
    public static HrSchema createInmemSchema () {
        HrSchema hs = new HrSchema();
        hs.depts[0] = new Department(1);
        hs.depts[1] = new Department(2);
        hs.emps[0] = new Employee(11,2);
        hs.emps[1] = new Employee(22,2);
        return hs;
    }
    // Class StudyDoc
//    public static void TestInMemDatasource () throws ClassNotFoundException, SQLException {
//        HrSchema hs = TestApp.createInmemSchema();
//        Class.forName("org.apache.calcite.jdbc.Driver");
//        Properties info = new Properties();
//        info.setProperty("lex", "JAVA");
//        Connection connection =
//                DriverManager.getConnection("jdbc:calcite:", info);
//        CalciteConnection calciteConnection =
//                connection.unwrap(CalciteConnection.class);
//        SchemaPlus rootSchema = calciteConnection.getRootSchema();
//        Schema schema = new ReflectiveSchema(hs);
//        rootSchema.add("hr", schema);
//        Statement statement = calciteConnection.createStatement();
//        ResultSet resultSet = statement.executeQuery(
//                "select d.deptno, min(e.empid)\n"
//                        + "from hr.emps as e\n"
//                        + "join hr.depts as d\n"
//                        + "  on e.deptno = d.deptno\n"
//                        + "group by d.deptno\n"
//                        + "having count(*) > 1");
//        ShowQueryResult(resultSet);
//        resultSet.close();
//        statement.close();
//        connection.close();
//    }

    public static void main(String[] args) throws SqlParseException {
//        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
//        final FrameworkConfig config = Frameworks.newConfigBuilder()
//                .parserConfig(SqlParser.configBuilder()
//                        .setParserFactory(SqlParserImpl.FACTORY)
//                        .setCaseSensitive(false)
//                        .setQuoting(Quoting.BACK_TICK)
//                        .setQuotedCasing(Casing.TO_UPPER)
//                        .setUnquotedCasing(Casing.TO_UPPER)
//                        .setConformance(SqlConformanceEnum.ORACLE_12)
//                        .build())
//                .build();
//
//        String sql = "select ids, name from test where id < 5 and name = 'zhang'";
//        SqlParser parser = SqlParser.create(sql, config.getParserConfig());
//        try {
//            SqlNode sqlNode = parser.parseStmt();
//            System.out.println(sqlNode.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        SqlParser sqlParser = SqlParser.create("select * from \"table\" where \"column\" > 1 limit 1");
        SqlNode sqlNode =  sqlParser.parseQuery();

        if(sqlNode instanceof SqlCall){
            if(sqlNode instanceof SqlBasicCall){
                SqlBasicCall basicCall = (SqlBasicCall) sqlNode;
                System.out.println(((SqlIdentifier)basicCall.operand(0)).getSimple());
                System.out.println(((SqlNumericLiteral)basicCall.operand(1)).getValue());
                System.out.println(basicCall.getKind());
            }
            System.out.println(sqlNode.getKind()+" -> "+sqlNode.getClass());
            SqlCall call = (SqlCall) sqlNode;
            for(SqlNode node: call.getOperandList()){
                System.out.println(node+" "+node.getKind());
            }
        }

    }
}
