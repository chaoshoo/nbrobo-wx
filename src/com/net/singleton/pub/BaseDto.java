package com.net.singleton.pub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <p>
 * <h2>BaseDtoIs allDTOClass abstract base class</h2>
 * </p>
 * 
 * <p>
 * Log tracking function，RealizedtoStringMethod，Realize the serialization function（Serializable）。
 * </p>
 * 
 * <p>
 * DTOClass can containPOobject，DTOObjects should not appear in theDaolayer，Can appear inServiceLayer and other on
 * layer，General inServiceLayer assemblyDTOobject。
 * </p>
 * 
 * <p>
 * DTOClass can be passed to theActionFormclass，Also asActionFormClass attribute usage，In order to reduce
 * Not necessaryget/setMethod call。
 * </p>
 * 
 *
 */
public abstract class BaseDto implements Serializable {

	//日志对象
	private transient Log log = null;

	/**structureDTOobject*/
	public BaseDto() {
		log = LogFactory.getLog(this.getClass());
//		if (SysConfig.isDebug() && getLog().isDebugEnabled()) {
//			getLog().debug("Creating " + this.getClass().getName());
//		}
	}
	
	/**releaseDTOobject*/
	protected void finalize() {
//		if (SysConfig.isDebug() && getLog().isDebugEnabled()) {
//			getLog().debug("Finalizing " + this.getClass().getName());
//		}
	}
	
    /**Get log objects*/
	protected Log getLog() {
		return log;
	}


	/**Whether the two instances are equal*/
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		return EqualsBuilder.reflectionEquals(this, o);
	}
	

    /**takeDTOObject into a string*/
	public String toString() {
    	return ToStringBuilder.reflectionToString(this,
    			ToStringStyle.MULTI_LINE_STYLE);
    }

}