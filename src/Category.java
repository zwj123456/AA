import java.util.List;


public class Category {
	private Integer cid;
	private Boolean is_parent;
	private String name;
	private Integer parent_cid;
	private String status;
	private Integer sort_order;
	
	private List<Category> childs;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Boolean getIs_parent() {
		return is_parent;
	}

	public void setIs_parent(Boolean is_parent) {
		this.is_parent = is_parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent_cid() {
		return parent_cid;
	}

	public void setParent_cid(Integer parent_cid) {
		this.parent_cid = parent_cid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSort_order() {
		return sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

	public List<Category> getChilds() {
		return childs;
	}

	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}
	
}
