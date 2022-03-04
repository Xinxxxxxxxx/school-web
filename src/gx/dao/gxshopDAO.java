package gx.dao;

import gx.entity.gxshop;
import gx.service.MysqlConn;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class gxshopDAO {
	MysqlConn db = new MysqlConn();
	ResultSet rs = null;
	int res = 0;
	//查询商品信息
	public List<gxshop> QueryShop(String sql,Object[] param){
		List<gxshop> someshop = new ArrayList();
		try{
			rs=db.doQuery(sql, param);
			while(rs.next()){
				gxshop shop =  new gxshop();
				shop.setGxsid(rs.getInt("gxsid"));
				shop.setGxsname(rs.getString("gxsname"));
				shop.setGxdw(rs.getString("gxdw"));
				shop.setGxtxm(rs.getInt("gxtxm"));
				shop.setGxjj(rs.getDouble("gxjj"));
				shop.setGxmj(rs.getDouble("gxmj"));
				shop.setGxhjcount(rs.getDouble("gxhjcount"));
				shop.setGxbjcount(rs.getDouble("gxbjcount"));
				shop.setGxzscount(rs.getDouble("gxzscount"));
				shop.setGxkc(rs.getInt("gxkc"));
				shop.setGxaddtime(rs.getDate("gxaddtime")+" "+rs.getTime("gxaddtime"));
				shop.setGxmore(rs.getString("gxmore"));
				someshop.add(shop);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return someshop;
	}
	
	//查询条数
	public int getRowCount(String sql,Object[] param){
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
	
	//添加商品信息
	public int Addshop(gxshop sp) {
		res=db.doExecute("insert into gxshopinfo(gxsname,gxdw,gxjj,gxmj,gxhjcount,gxbjcount,gxzscount,gxmore,gxtxm,gxaddtime) values(?,?,?,?,?,?,?,?,?,now())",
				new Object[]{sp.getGxsname(),sp.getGxdw(),sp.getGxjj(),sp.getGxmj(),sp.getGxhjcount(),sp.getGxbjcount(),sp.getGxzscount(),sp.getGxmore(),sp.getGxtxm()});
		db.close();
		return res;
		
	}
	//删除信息
	public int DeleteSp(int id){
		res=db.doExecute("delete from gxshopinfo where gxsid=?",new Object[]{id});
		db.close();
		return res;
	}
	//通过ID查找
	public gxshop QueryById(int id) {
		gxshop sp=new gxshop();
		try{
			rs =db.doQuery("select * from gxshopinfo where gxsid=?", new Object[]{id});
			while(rs.next()){
				sp.setGxsid(rs.getInt("gxsid"));
				sp.setGxsname(rs.getString("gxsname"));
				sp.setGxdw(rs.getString("gxdw"));
				sp.setGxtxm(rs.getInt("gxtxm"));
				sp.setGxjj(rs.getDouble("gxjj"));
				sp.setGxmj(rs.getDouble("gxmj"));
				sp.setGxhjcount(rs.getDouble("gxhjcount"));
				sp.setGxbjcount(rs.getDouble("gxbjcount"));
				sp.setGxzscount(rs.getDouble("gxzscount"));
				sp.setGxmore(rs.getString("gxmore"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return sp;
	}
	//通过ID进行修改
	public int UpdatespById(gxshop sp) {
		res=db.doExecute("update gxshopinfo set gxsname=?,gxdw=?,gxjj=?,gxmj=?,gxtxm=?,gxhjcount=?,gxbjcount=?,gxzscount=?,gxmore=? where gxsid=?", 
				new Object[]{sp.getGxsname(),sp.getGxdw(),sp.getGxjj(),sp.getGxmj(),sp.getGxtxm(),sp.getGxhjcount(),sp.getGxbjcount(),sp.getGxzscount(),sp.getGxmore(),sp.getGxsid()});
		db.close();
		return res;
	}
	

}
