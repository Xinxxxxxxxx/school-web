package gx.dao;

import gx.entity.gxshopls;
import gx.service.MysqlConn;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class gxshoplsDAO {
	MysqlConn db = new MysqlConn();
	ResultSet rs = null;
	int res = 0;
	
	//查询临时商品
	public List<gxshopls> QueryShopls(String sql,Object[] param){
		List<gxshopls> someshopls = new ArrayList();
		try{
			rs=db.doQuery(sql, param);
			while(rs.next()){
				gxshopls shopls =  new gxshopls();
				shopls.setGxsid(rs.getInt("gxsid"));
				shopls.setGxsname(rs.getString("gxsname"));
				shopls.setGxdw(rs.getString("gxdw"));
				shopls.setGxtxm(rs.getInt("gxtxm"));
				shopls.setGxjj(rs.getDouble("gxjj"));
				shopls.setGxmj(rs.getDouble("gxmj"));
				shopls.setGxhjcount(rs.getDouble("gxhjcount"));
				shopls.setGxbjcount(rs.getDouble("gxbjcount"));
				shopls.setGxzscount(rs.getDouble("gxzscount"));
				shopls.setGxkc(rs.getInt("gxkc"));
				shopls.setGxmore(rs.getString("gxmore"));
				shopls.setGxrole(rs.getInt("gxrole"));
				someshopls.add(shopls);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return someshopls;
	}
	//删除信息
	public int DeleteSpls(int id){
		res=db.doExecute("delete from gxshopls where gxsid=?",new Object[]{id});
		db.close();
		return res;
	}
	//清空临时表中所有数据
	public int Qingkongdata(){
		res=db.doExecute("delete from gxshopls", new Object[]{});
		db.close();
		return res;
	}

}
