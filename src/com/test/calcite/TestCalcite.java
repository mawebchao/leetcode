package com.test.calcite;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestCalcite {
    public static final Map<String, Database> MAP = new HashMap<String, Database>();

    public static class Database {
        public List<Table> tables = new LinkedList<Table>();
    }


    public static class Table {
        public String tableName;
        public List<Column> columns = new LinkedList<Column>();
        public List<List<String>> data = new LinkedList<List<String>>();
    }

    public static class Column {
        public String name;
        public String type;
    }

    static Table newClassTable() {
        //定义一个抽象Class表
        Table cl = new Table();
        cl.tableName = "Class";
        Column name = new Column();
        name.name = "name";
        name.type = "varchar";
        cl.columns.add(name);

        Column id = new Column();
        id.name = "id";
        id.type = "integer";
        cl.columns.add(id);

        Column teacher = new Column();
        teacher.name = "teacher";
        teacher.type = "varchar";
        cl.columns.add(teacher);
        return cl;
    }

    static Table newStudentTable() {
        //定义一个抽象Student表
        Table student = new Table();
        student.tableName = "Student";
        Column name = new Column();
        name.name = "name";
        name.type = "varchar";
        student.columns.add(name);

        Column id = new Column();
        id.name = "id";
        id.type = "varchar";
        student.columns.add(id);

        Column classId = new Column();
        classId.name = "classId";
        classId.type = "integer";
        student.columns.add(classId);

        Column birth = new Column();
        birth.name = "birthday";
        birth.type = "date";
        student.columns.add(birth);

        Column home = new Column();
        home.name = "home";
        home.type = "varchar";
        student.columns.add(home);
        return student;
    }


    public static void main(String[] args) {
//        Database db = new Database();
//        db.tables.add(newClassTable());
//        db.tables.add(newStudentTable());




    }

}
