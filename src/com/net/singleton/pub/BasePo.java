package com.net.singleton.pub;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * <h2>BasePoIs allPOClass abstract base class</h2>
 * </p>
 * 
 * <p>
 * Implements the primary key field for the database table、timestamp field、Access operation of state field，Realize the log tracking
 * function，Realizedequals, hashCode, toStringMethod，Realize the serialization function
 * （Serializable）。
 * </p>
 * 
 * <p>
 * BasePoAbstract base class requires that each database table has a single primary key field：ID Number(20)，time stamp
 * field：STAMP Date，State field：STATUS Number(20)，Primary key field of database table
 * It should be of no practical significance of the serial number，The time stamp field should be updated every time the data is saved.，not for the time being
 * There should be other implications，The status field should have at least two states：0-delete，1-normal。
 * </p>
 * 
 * <p>
 * RealizationPOClass，Must fromBasePoBase class inheritance，Named asXxx（Don't have toPoEnding），And configured in
 * HibernateMapping file，File nameXxx.hbm.xml，eachPOMapping documents require configuration
 * Primary key、time stamp、State property。POClass and related files in the same directory mapping。Can use a simple one
 * To many、Multiple to one Association，If you use one to many associations，Please useBagLabel configuration。
 * </p>
 * 
 * <p>
 * RealizationPOClass，The following data types can be used to add the basic properties：String usejava.lang.Stringclass，
 * Date usejava.util.Date，Integer usejava.lang.Integerperhapsjava.lang.Long，
 * Floating point usagejava.math.BigDecimal。
 * </p>
 * 
 * <p>
 * RealizationPOClass，Do not have to achieveSerializableInterface，becauseBasePoBase class has been implemented。Also do not have to
 * RedefineidProperties andstampattribute，Do not need to rewriteequals, hashCode, toStringMethod，
 * Log objects are not defined，If you need to write a log，Direct callBasePoBase classgetLog()Method obtained
 * have toLogObject can。
 * </p>
 * 
 * <p>
 * POThe life cycle of an object should beDaoUnified management of layers，Include new、Save、Query and other operations，Should not be in
 * DaoThe upper layer of the layer is createdPOobject，Out of commissionnew Xxx()The way to createPO，If you want to createPO，be
 * Must call the correspondingDaoThecreate()Method，In order to obtain the initial value of the key，Such as primary key。Delete from database
 * exceptPOtime，Should not be deleted directly，And it should be implemented by setting the state.。
 * </p>
 * 
 * <p>
 * stayDaoLayer queryPOObject time，POObject is in a persistent state（andSessionRelation），Must call
 * evict()Make methodPOIn off tube condition（andSessionBe divorced from），Before it can be passed on toService
 * Layer or other upper layer。DaoLayer savePOObject after，Also need to callevict()Method。
 * </p>
 * 
 * <p>
 * POObject can not containDTOobject，IfPONeed to be assembled intoDTO，Should be inServiceLayer assembly，and
 * Should not be inDaoperhapsActionLayer write assembly logic code。
 * </p>
 * 
 * <p>
 * POClass can be passed to theActionFormclass，Also asActionFormClass attribute usage，In order to reduce
 * Not necessaryget/setMethod call。
 * </p>
 * 
 */
public abstract class BasePo extends BaseDto {

	//主键类的元数据
	private Class pkClazz = null;
	
	//表示PO实例对象是否是新建的
	private boolean newer = false;
	
	//主键属性：ID
	private Long id;

	//主键属性：PK
	private BasePk pk;

	//状态属性：STATUS
	private String state;

	//状态时间属性
	private Date stateDate;
	
	//时间戳
	private Timestamp stamp;
	
	public BasePo() {
	}
	
	public BasePo(Class pkClazz) {
		setPkClazz(pkClazz);
	}
	
	/**Create a primary key attribute：PK*/
	public BasePk newPk() {
		if (getPkClazz() == null) {
			return null;
		}
		
		BasePk p = null;
		try {
			p = (BasePk) getPkClazz().newInstance();
		} catch (Throwable t) {
			Exception eb = new Exception("Failed to create a joint primary key instance", t);
			getLog().error(eb.getMessage());
		}
		return p;
	}
	
	/**Primary key attribute：PK*/
	public BasePk getPk() {
		return pk;
	}

	/**Set the primary key attribute：PK*/
	public void setPk(BasePk pk) {
		this.pk = pk;
	}

	/**Primary key attribute：ID*/
	public Long getId() {
		return id;
	}

	/**Set the primary key attribute：ID*/
	public void setId(Long id) {
		this.id = id;
	}

	/**Obtained state property：STATUS*/
	public String getState() {
		return state;
	}

	/**Set state properties：STATUS*/
	public void setState(String state) {
		this.state = state;
	}

	/**Status time attribute：STATUS_DATE*/
	public Date getStateDate() {
		return stateDate;
	}

	/**Set state time properties：STATUS_DATE*/
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	/**ExpressPOInstance object is new*/
    public boolean isNewer() {
		return newer;
	}

	/**ConfigPOInstance object is new*/
	public void setNewer(boolean newer) {
		this.newer = newer;
	}

	/**Entity state of database data：To void，value："10X"*/
	public String getStateDiscard() {
		return "10X";
	}

	/**Entity state of database data：normal，value："10A"*/
	public String getStateNormal() {
		return "10A";
	}

	/**Metadata for primary key class*/
	protected Class getPkClazz() {
		return pkClazz;
	}

	/**Sets the metadata for the primary key class*/
	protected void setPkClazz(Class pkClazz) {
		this.pkClazz = pkClazz;
	}

	public Timestamp getStamp() {
		return stamp;
	}

	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}

}