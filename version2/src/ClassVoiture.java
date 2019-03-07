

import java.util.Objects;
import java.util.Vector;

public class ClassVoiture {
	private int numClass;
	private int painColor;
	private Vector<Boolean> optionMap;
	
	public ClassVoiture(int painColor, Vector<Boolean> optionMap ) {
		this.numClass = 0;
		this.painColor = painColor;
		this.optionMap = optionMap;
	}
	
	public int getNumClass() {
		return numClass;
	}
	
	public int getPainColor() {
		return painColor;
	}
	
	public Vector<Boolean> getOptionMap() {
		return optionMap;
	}
	
	public void setNumClass(int numClass) {
		this.numClass = numClass;
	}
	
	@Override
	public String toString() {
		
		return "Color " + painColor + " Options :" + optionMap;
	}
	
	public String getValueOption() {
		StringBuffer res = new StringBuffer();
		for (Boolean option: optionMap)
			res.append(option + " ");
		return res.toString();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		ClassVoiture classVoiture = (ClassVoiture) obj;
		//return (super.equals(obj) && Objects.equals(painColor, classVoiture.painColor) && Objects.equals(getValueMap(), classVoiture.getValueMap()) && Objects.equals(getKeyMap(), classVoiture.getKeyMap()));
		return (this.toString().equals(classVoiture.toString()));
	//return (x == disque.x && y == disque.y && rayon == disque.rayon); cette return est vrai aussi;
}
	
	@Override
	public int hashCode() {
	    return Objects.hashCode(this.painColor);
	}
}
