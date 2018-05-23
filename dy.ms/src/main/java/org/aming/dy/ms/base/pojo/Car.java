package org.aming.dy.ms.base.pojo;

/**
 * Description: car
 * User: daming
 * Date: 2018-05-23
 * Time: 21:24
 * Description:
 */
public class Car {

	private String carid;
	private String cartype;
	private String dirverid;
	private String usagetype;
	private String buytime;
	private String capacity;
	private String capunit;

	public String getCarid() {
		return carid;
	}

	public Car setCarid(String carid) {
		this.carid = carid;
		return this;
	}

	public String getCartype() {
		return cartype;
	}

	public Car setCartype(String cartype) {
		this.cartype = cartype;
		return this;
	}

	public String getDirverid() {
		return dirverid;
	}

	public Car setDirverid(String dirverid) {
		this.dirverid = dirverid;
		return this;
	}

	public String getUsagetype() {
		return usagetype;
	}

	public Car setUsagetype(String usagetype) {
		this.usagetype = usagetype;
		return this;
	}

	public String getBuytime() {
		return buytime;
	}

	public Car setBuytime(String buytime) {
		this.buytime = buytime;
		return this;
	}

	public String getCapacity() {
		return capacity;
	}

	public Car setCapacity(String capacity) {
		this.capacity = capacity;
		return this;
	}

	public String getCapunit() {
		return capunit;
	}

	public Car setCapunit(String capunit) {
		this.capunit = capunit;
		return this;
	}
}
