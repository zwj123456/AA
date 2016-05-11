import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import cn.joy.framework.kits.JsonKit;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;

public class Test implements Serializable {
	
	public static final String url = "	http://gw.api.taobao.com/router/rest";
	public static final String appkey = "23345274";
	public static final String secret = "26776b6ca99ae143cc0d49e1e10bc50c";
	
	TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
	
	public static void main(String[] args) throws Exception {
		
		List<Category> result = new ArrayList<Category>();
		try {
			result = new Test().findCategoryData(0L);	//第一级类目
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			BufferedOutputStream oos = new BufferedOutputStream(new FileOutputStream("category.txt"));
			oos.write(JsonKit.object2Json(result).getBytes());
			
			oos.flush();
			oos.close();
		}
	}
	
	private List<Category> findCategoryData(long p_cid) throws ApiException{

		List<Category> dataList = new ArrayList<Category>();
		
		ItemcatsGetRequest req = new ItemcatsGetRequest();
		req.setFields("cid,parent_cid,name,is_parent,status,sort_order");
		req.setParentCid(p_cid);
		ItemcatsGetResponse rsp = client.execute(req);
		String responseBody = rsp.getBody();
		Map<String, Map<String, Map<String, List<Map<String, Object>>>>> result = JsonKit.json2Map(responseBody);
		
		List<Map<String, Object>> objs = new ArrayList<>();
		try{
			objs = result.get("itemcats_get_response").get("item_cats").get("item_cat");
		}catch(Exception e){
			System.out.println(responseBody);
			e.printStackTrace();
			return null;
		}
		
		for(Map<String, Object> obj : objs){
			Integer cid = Integer.valueOf(obj.get("cid").toString());
			Boolean is_parent = Boolean.valueOf(obj.get("is_parent").toString());
			String name = String.valueOf(obj.get("name"));
			Integer parent_cid = Integer.valueOf(obj.get("parent_cid").toString());
			String status = obj.get("status").toString();
			Integer sort_order = Integer.valueOf(obj.get("sort_order").toString());

			System.out.println("cid:"+cid+"\t is_parent:"+is_parent+"\t name:"+name+"\t parent_cid:"+parent_cid);
			
			Category category = new Category();
			category.setCid(cid);
			category.setIs_parent(is_parent);
			category.setName(name);
			category.setParent_cid(parent_cid);
			category.setStatus(status);
			category.setSort_order(sort_order);
			
//			if(is_parent){
//				category.setChilds(findCategoryData(cid));
//			}
			
			dataList.add(category);
		}
		
		return  dataList;
	}
}
