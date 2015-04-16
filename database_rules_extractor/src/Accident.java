import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Accident {
	private Date date;
	private String borough;
	private String zipcode;
	private int persons_injured;
	private int persons_killed;
	private int peds_injured;
	private int peds_killed;
	private int cyc_injured;
	private int cyc_killed;
	private int moto_injured;
	private int moto_killed;
	private String[] contributing_factors = new String[]{"", "", "", "", ""};
	private String[] vehicle_types  = new String[]{"", "", "", "", ""};
	
	public Accident (String[] s){
		setDate(s[0], s[1]);
		setBorough(s[2]);
		setZipcode(s[3]);
		setPersonsInjured(s[10]);
		setPersonsKilled(s[11]);
		setPedestriansInjured(s[12]);
		setPedestriansKilled(s[13]);
		setCyclistsInjured(s[14]);
		setCyclistsKilled(s[15]);
		setMotoristsInjured(s[16]);
		setMotoristsKilled(s[17]);
		setFactors(s[18], s[19], s[20], s[21], s[22]);
		setVehicles(s[24], s[25], s[26], s[27], s[28]);
	}
	
	public void setDate(String a, String b){
		String date = a + " " + b;
		DateFormat f = new SimpleDateFormat("MM/DD/yy HH:mm", Locale.ENGLISH);
		try {
			this.date = f.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setBorough(String s){
		this.borough = s;
	}
	
	public void setZipcode(String s){
		this.zipcode = s;
	}
	
	public void setPersonsInjured(String s){
		int i = Integer.parseInt(s);
		this.persons_injured = i;
	}
	
	public void setPersonsKilled(String s){
		int i = Integer.parseInt(s);
		this.persons_killed = i;
	}
	
	public void setPedestriansInjured(String s){
		int i = Integer.parseInt(s);
		this.peds_injured = i;
	}
	
	public void setPedestriansKilled(String s){
		int i = Integer.parseInt(s);
		this.peds_killed = i;
	}
	
	public void setCyclistsInjured(String s){
		int i = Integer.parseInt(s);
		this.cyc_injured = i;
	}
	
	public void setCyclistsKilled(String s){
		int i = Integer.parseInt(s);
		this.cyc_killed = i;
	}
	
	public void setMotoristsInjured(String s){
		int i = Integer.parseInt(s);
		this.moto_injured = i;
	}
	
	public void setMotoristsKilled(String s){
		int i = Integer.parseInt(s);
		this.moto_killed = i;
	}

	public void setFactors(String a, String b, String c, String d, String e){
		if(!a.isEmpty()) contributing_factors[0] = a;
		if(!b.isEmpty()) contributing_factors[1] = b;
		if(!c.isEmpty()) contributing_factors[2] = c;
		if(!d.isEmpty()) contributing_factors[3] = d;
		if(!e.isEmpty()) contributing_factors[4] = e;
	}
	
	public void setVehicles(String a, String b, String c, String d, String e){
		if(!a.isEmpty()) vehicle_types[0] = a;
		if(!b.isEmpty()) vehicle_types[1] = b;
		if(!c.isEmpty()) vehicle_types[2] = c;
		if(!d.isEmpty()) vehicle_types[3] = d;
		if(!e.isEmpty()) vehicle_types[4] = e;
	}
	
	public Object[] toRow(){
		Object[] obj = new Object[] {this.date, this.borough, this.zipcode, this.persons_injured, this.persons_killed,
				this.peds_injured, this.peds_killed, this.cyc_injured, this.cyc_killed, this.moto_injured, this.moto_killed,
				this.contributing_factors[0], this.contributing_factors[1], this.contributing_factors[2],
				this.contributing_factors[3], this.contributing_factors[4],
				this.vehicle_types[0], this.vehicle_types[1], this.vehicle_types[2], this.vehicle_types[3], this.vehicle_types[4]};
		return obj;
	}
}
