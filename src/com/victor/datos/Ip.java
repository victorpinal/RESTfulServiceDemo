package com.victor.datos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ip {
	
	private int id;
	private String ip;
	private String name;
	
	public Ip() {
		this(0,"127.0.0.1","");
	}
	
	public Ip(int id, String ip, String name) {
		this.id = id;
		this.ip = ip;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " - " + ip;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Ip ip_class = (Ip) o;

		return !(ip != null ? !ip.equals(ip_class.ip) : ip_class.ip != null);
				//&& (name != null ? name.equals(ip_class.name) : ip_class.name == null);
	}

	@Override
	public int hashCode() {
		return (ip != null ? ip.hashCode() : 0) + (name != null ? name.hashCode() : 0);
	}
	
}
