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
			String sql="INSERT INTO img(img_id,img_name,img_address,use_id)VALUES(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,img.getImg_id());
			pstmt.setString(2,img.getImg_name());
			pstmt.setString(3,img.getImg_address());
			pstmt.setInt(4,img.getUse_id());
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
	public Image getAddress(int id) {
		try{
			conn=JDBCUtil.getConnection();
			String sql="select * from img where img_id=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Image image = new Image();
				image.setImg_id(rs.getInt(1));
				image.setImg_name(rs.getString(2));
				image.setImg_address(rs.getString(3));
				image.setUse_id(rs.getInt(4));
				System.out.println("image");
				return image;
			}
		}catch (Exception e) {
			
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return null;
	}

	@Override
	public List <Image> getAll(int id) {
		try {
		/*	Use_img use = new Use_img();*/
			conn=JDBCUtil.getConnection();
			String sql = "SELECT *FROM img where use_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);	

			rs=pstmt.executeQuery();
			List<Image> list = new ArrayList<Image>();
			while(rs.next()) {
				Image image = new Image();
				image.setImg_id(rs.getInt(1));
				image.setImg_name(rs.getString(2));
				image.setImg_address(rs.getString(3));
				image.setUse_id(rs.getInt(4));
				list.add(image);
			}				
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
