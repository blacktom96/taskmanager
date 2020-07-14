package jp.taskmanager.tasksmanager.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="taskmanager")
public class TaskManagerModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer takusuID = null;    //タクス
	
	@Column(name="taitoru")
	private String taitoru;  //内容
	
	@Column(name="naiyou")
	private String naiYou;
	
	@Column(name="yoteibi")
	private String yoteiBi;
	
	@Column(name="kanryobi")
	private String kanryoBi;
	
	@Column(name="joukyou")
	private int jouKyou;
	
	@Column(name="yuusenjuni")
	private String yuusenjuni;
	
	@Column(name="torokubi")
	private String torokubi;
	
	@Column(name="kaijo")
	private int kaijo;
	
	@Column(name="henkobi")
	private String henkobi;


	public Integer getTakusuID() {
		return takusuID;
	}

	public void setTakusuID(Integer takusuID) {
		this.takusuID = takusuID;
	}

	public String getTaitoru() {
		return taitoru;
	}

	public void setTaitoru(String taitoru) {
		this.taitoru = taitoru;
	}


	public String getNaiYou() {
		return naiYou;
	}

	public void setNaiYou(String naiYou) {
		this.naiYou = naiYou;
	}

	public String getYoteiBi() {
		return yoteiBi;
	}

	public void setYoteiBi(String yoteiBi) {
		this.yoteiBi = yoteiBi;
	}

	public String getKanryoBi() {
		return kanryoBi;
	}

	public void setKanryoBi(String kanryoBi) {
		this.kanryoBi = kanryoBi;
	}

	

	public int getJouKyou() {
		return jouKyou;
	}

	public void setJouKyou(int jouKyou) {
		this.jouKyou = jouKyou;
	}

	public String getYuusenjuni() {
		return yuusenjuni;
	}

	public void setYuusenjuni(String yuusenjuni) {
		this.yuusenjuni = yuusenjuni;
	}

	public String getTorokubi() {
		return torokubi;
	}

	public void setTorokubi(String torokubi) {
		this.torokubi = torokubi;
	}

	public int getKaijo() {
		return kaijo;
	}

	public void setKaijo(int kaijo) {
		this.kaijo = kaijo;
	}

	public String getHenkobi() {
		return henkobi;
	}

	public void setHenkobi(String henkobi) {
		this.henkobi = henkobi;
	}

	@Override
	public String toString() {
		return "TaskManagerModel [takusuID=" + takusuID + ", taitoru=" + taitoru + ", naiYou=" + naiYou + ", yoteiBi="
				+ yoteiBi + ", kanryoBi=" + kanryoBi + ", jouKyou=" + jouKyou + ", yuusenjuni=" + yuusenjuni
				+ ", torokubi=" + torokubi + ", kaijo=" + kaijo + ", henkobi=" + henkobi + "]";
	}

	

}
