import java.util.HashMap;
import java.util.Objects;

public class ClassVoiture {
	private int numClass;
	private int paintColor;
	private HashMap<String, Boolean> optionMap;
	
	public ClassVoiture(int painColor, HashMap<String, Boolean> optionMap ) {
		this.numClass = 0;
		this.paintColor = painColor;
		this.optionMap = optionMap;
	}
	
	public int getNumClass() {
		return numClass;
	}
	
	public int getPaintColor() {
		return paintColor;
	}
	
	public HashMap<String, Boolean> getOptionMap() {
		return optionMap;
	}
	
	public void setNumClass(int numClass) {
		this.numClass = numClass;
	}
	
	@Override
	public String toString() {
		
		return "Color " + paintColor + " Options :" + optionMap;
	}
	
	public String getKeyMap() {
		StringBuffer res = new StringBuffer();
		for (String key : optionMap.keySet())
			res.append(key + " ");
		return res.toString();
	}
	
	public String getValueMap() {
		StringBuffer res = new StringBuffer();
		for (Boolean value: optionMap.values())
			res.append(value + " ");
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
	    return Objects.hashCode(this.paintColor);
	}
}
