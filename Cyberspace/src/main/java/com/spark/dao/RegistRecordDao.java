package com.spark.dao;

import com.alibaba.druid.sql.PagerUtils;
import com.spark.domain.PageBean;
import com.spark.domain.RegistRecord;
import com.spark.util.Constant;
import com.spark.util.JdbcUtil_;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * 封装查询条件
 * 查询第几页数据
 * 每页显示多少条记录
 * 查询结果
 * @date Date : 2018-12-03  21:48
 * @version： V1.0
 */
public class RegistRecordDao {


    public PageBean<RegistRecord> findStudent(RegistRecord searchModel, int pageNum,
                                      int pageSize){
        //PageBean对象
        PageBean<RegistRecord> result = null;
        //存放查询参数
        List<Object> paramList = new ArrayList<Object>();

        String name = searchModel.getName();
        int status = searchModel.getStatus();

        StringBuilder sql = new StringBuilder(
                "SELECT * FROM t_registrecord WHERE 1 = 1");
        StringBuilder countSql = new StringBuilder(
                "select count(id) as totalRecord from t_registrecord where 1=1 ");
        if(name != null && !name.equals("")){
            sql.append( " and r_name like ?");
            countSql.append( " and r_name like ?");
            paramList.add("%" + name + "%");
        }

        if(status == Constant.GENDER_MALE || status == Constant.GENDER_FEMALE){
            sql.append(" and r_status = ?");
            countSql.append(" and r_status = ?");
            paramList.add(status);
        }
        // 起始索引
        int fromIndex	= pageSize * (pageNum -1);

        // 使用limit关键字，实现分页
        sql.append(" limit " + fromIndex + ", " + pageSize );

        // 存放所有查询出的学生对象
        List<RegistRecord> registRecords = new ArrayList<RegistRecord>();
        JdbcUtil_ jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil_();
            jdbcUtil.getConnection(); // 获取数据库链接

            // 获取总记录数
            List<Map<String, Object>> countResult = jdbcUtil.findResult(countSql.toString(), paramList);
            Map<String, Object> countMap = countResult.get(0);
            int totalRecord = ((Number)countMap.get("totalRecord")).intValue();

            // 获取查询的学生记录
            List<Map<String, Object>> registRecordResult = jdbcUtil.findResult(sql.toString(), paramList);
            if ( registRecordResult != null) {
                for (Map<String, Object> map : registRecordResult) {
                    RegistRecord s = new RegistRecord(map);
                    registRecords.add(s);
                }
            }

            //获取总页数
            int totalPage = totalRecord / pageSize;
            if(totalRecord % pageSize !=0){
                totalPage++;
            }

            // 组装pager对象
            result = new PageBean<RegistRecord>(pageSize, pageNum,
                    totalRecord, totalPage, registRecords);

        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据异常！", e);
        } finally {
            if (jdbcUtil != null) {
                jdbcUtil.releaseConn(); // 一定要释放资源
            }
        }
        return null;
    }















//    //获取总记录数
//    public static Long getCount() throws SQLException {
//        QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
//        String sql = "SELECT count(*) FROM t_registrecord";
//        Long count = (Long)queryRunner.query(sql,new ScalarHandler<>());
//        return count;
//    }
//    //获取对象属性
//    public static List<RegistRecord> getPageData(Integer index, Integer pageCount) throws SQLException {
//        QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
//        String sql = "SELECT * FROM t_registRecord LIMIT ?,?";
//        System.out.println("************index: " + index + "pageCount: " + pageCount );
//        List<RegistRecord> allRegistRecord = null;
//        allRegistRecord =  queryRunner.query(sql,new BeanListHandler<RegistRecord>(RegistRecord.class),index,pageCount);
//
//        return allRegistRecord;
//    }
//    public static void main(String[] args) throws SQLException {
//      List<RegistRecord> lst = getPageData(3,3);
//        System.out.println("-" + lst);
//        System.out.println("-" + lst.size());
//        for (RegistRecord s : lst) {
//            System.out.println(s);
//        }
//    }
}
