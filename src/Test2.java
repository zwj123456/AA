import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import cn.joy.framework.kits.JsonKit;


public class Test2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bos = new BufferedReader(new FileReader("json.txt"));
		String resultJson = bos.readLine();
		bos.close();
		Map<String, Object> result = JsonKit.json2Map(resultJson);

		List<Map<String, String>> skuList = (List<Map<String, String>>) result.get("SKU");
		List<Map<String, String>> modifyList = (List<Map<String, String>>) result.get("modify");
		
		for(Map<String, String> modify : modifyList){
			String pid = modify.get("pid");
			String original_id = modify.get("id");
			String str = modify.get("m");
			String original_str = modify.get("o");
			
			//save,返回id
			String id = "1";
			boolean flag = false;	//标示
			
			for(Map<String, String> sku : skuList){
				String skuStr = sku.get("sku");
				System.out.println("原始数据SKU："+skuStr);
				System.out.println();
				skuStr = skuStr.replace(":"+original_id+";", ":"+id+";");
				System.out.println("修改数据SKU："+skuStr);
				System.out.println("------------------------------------");
			}
		}
		System.out.println();
		for(Map<String, String> sku : skuList){
			String skuStr = sku.get("sku");
			System.out.println("SKU："+skuStr);
		}
	}
}
