package gx.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gx.entity.gxHy;
import gx.service.MysqlConn;

public class gxHyDAO {
	MysqlConn db = new MysqlConn();
	ResultSet rs = null;
	int res = 0;
	
	public List<gxHy> QueryHy(String sql, Object[] param){
		List<gxHy> somehy = new ArrayList<gxHy>();
		try{
			rs = db.doQuery(sql,param);
			while(rs.next()){
				gxHy hy = new gxHy();
				hy.setGxid(rs.getInt("gxid"));
				hy.setGxvname(rs.getString("gxvname"));
				hy.setGxphone(rs.getString("gxvphone"));
				hy.setGxqb(rs.getDouble("gxqb"));
				hy.setGxjf(rs.getInt("gxjf"));
				hy.setGxaddr(rs.getString("gxaddr"));
				hy.setGxjb(rs.getString("gxjb"));
				hy.setGxdtime(rs.getDate("gxdtime")+""+rs.getTime("gxdtime"));
				somehy.add(hy);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return somehy;
	
	}
	public int getRowCount(String sql, Object[] param){
		try{
			rs = db.doQuery(sql, param);
			if(rs.next()){
				res = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return res;
	}
   public int AddHy(gxHy hy){
	   res = db.doExecute("insert into gxvip(gxvname,gxvphone,gxaddr,gxjb,gxdtime) values(?,?,?,?,now())",
			   new Object[]{hy.getGxvname(),hy.getGxphone(),hy.getGxaddr(),hy.getGxjb()});
	   return res;
   }
   public gxHy QueryHyById(int gxid){
	   gxHy hy = new gxHy();
	   try{
		   rs = db.doQuery("select *from gxvip where gxid = ?", new Object[]{gxid});
		   if(rs.next()){
			   hy.setGxid(rs.getInt("gxid"));
			   hy.setGxvname(rs.getString("gxvname"));
				hy.setGxphone(rs.getString("gxvphone"));
				hy.setGxqb(rs.getDouble("gxqb"));
				hy.setGxjf(rs.getInt("gxjf"));
				hy.setGxaddr(rs.getString("gxaddr"));
				hy.setGxjb(rs.getString("gxjb"));
				hy.setGxdtime(rs.getDate("gxdtime")+" "+rs.getTime("gxdtime"));
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return hy;
	
	}
		
   public int UpdateHy(gxHy hy){
	   res = db.doExecute("update gxvip set gxvname=?,gxvphone=?,gxaddr=?,gxjb=?,gxqb=? where gxid=?", 
			   new Object[]{hy.getGxvname(),hy.getGxphone(),hy.getGxaddr(),hy.getGxjb(),hy.getGxqb(),hy.getGxid()});
	   db.close();
	   return res;
   }
   public int DeleteHyById(int gxid){
	   res = db.doExecute("delete from gxvip where gxid=?", new Object[]{gxid});
	   db.close();
	   return res;
   }
 }

