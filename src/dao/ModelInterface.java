package dao;

import Entities.Model;

public interface ModelInterface {	
	public void addmodel(Model m);
	public void deletemodel(int mid);
	public void updatemodelcost(int mid);
	public void displayAllmodels();
	public Model searchModelById(int mid);
}
