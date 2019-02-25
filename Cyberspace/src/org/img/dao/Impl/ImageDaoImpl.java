package org.img.dao.Impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.img.dao.ImageDao;
import org.img.domain.Image;
import org.img.jdbc.util.JDBCUtil;

public class ImageDaoImpl implements ImageDao{
	Connection conn = null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	int effect=-1;
	@Override
	public int save(Image img) {	
		try {
			conn=JDBCUtil.getConnection();	
			String sql="INSERT INTO img(img_name,img_address,use_id)VALUES(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,img.getImg_name());
			pstmt.setString(2,img.getImg_address());
			pstmt.setInt(3,img.getUse_id());
			effect=pstmt.executeUpdate();
			if(effect>0) {
				System.out.println("插入成功！");
				effect=1;
			}else {
				System.out.println("插入失败！");
				effect=-1;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, null);
		}
		return effect;
	}
	@Override
	public int getImgID() {
		try{
			conn=JDBCUtil.getConnection();
			String sql="SELECT img_id from img order by img_id DESC LIMIT 1";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
			int id=	rs.getInt(1);
			System.out.println("MAXid="+id);
			return id;
			}
		}catch (Exception e) {
			
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return -1;
	}

	@Override
	public List <Image> getAll(int id) {
		try {
			conn=JDBCUtil.getConnection();
			String sql = "SELECT img_address FROM img where use_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);	
			rs=pstmt.executeQuery();
			List<Image> list = new ArrayList<Image>();
			while(rs.next()) {
				Image image = new Image();	
				image.setImg_address(rs.getString(1));
				list.add(image);
			}				
			System.out.println("list="+list);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return null;
	}
}
