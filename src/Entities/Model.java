package Entities;

public class Model {
	//model_id, mname, cost, count
	
	int mid;
	String mname;
	float cost;
	int count;
	
	public Model(int mid, String mname, float cost, int count) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.cost = cost;
		this.count = count;
	}

	public int getMid() {
		return mid;
	}

	@Override
	public String toString() {
		return "Model [mid=" + mid + ", mname=" + mname + ", cost=" + cost + ", count=" + count + "]";
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}
}
