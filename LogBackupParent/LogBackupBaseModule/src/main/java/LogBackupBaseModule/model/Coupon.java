package LogBackupBaseModule.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import LogBackupBaseModule.common.DateUtility;
import LogBackupBaseModule.common.DebugLog;
import LogBackupBaseModule.configurations.EnumConfig.CouponOrigin;
import LogBackupBaseModule.configurations.EnumConfig.CouponStatus;
import LogBackupBaseModule.interfaces.PseudoModel;
import LogBackupBaseModule.validation.ValidationException;





public class Coupon implements PseudoModel, Serializable{
	
	private static final long serialVersionUID = 7L;

	public static final long expireThreshould = 15811200000l;
	
	private long couponId;	
	private int userId;
	private int amount;
	private int originalAmount;
	private Calendar creationTime;
	private Calendar expireTime;
	private CouponStatus status;
	private CouponOrigin origin;
	
	//SQL Construction
	public Coupon(long couponId, int userId,
			int amount, Calendar creationTime, Calendar expireTime,
			CouponStatus status,CouponOrigin origin,int originalAmount) {
		super();
		this.couponId = couponId;		
		this.userId = userId;
		this.amount = amount;
		this.creationTime = creationTime;
		this.expireTime = expireTime;
		this.status = status;
		this.origin = origin;
		this.originalAmount = originalAmount;
	}

	//normal construction
	public Coupon(int userId, int amount){
		super();
		this.couponId = -1;		
		this.userId = userId;
		this.amount = amount;
		this.originalAmount = amount;
		this.expireTime = DateUtility.getTimeFromLong(DateUtility.getCurTime() + expireThreshould);
		this.status = CouponStatus.usable;
		this.origin = CouponOrigin.registration;
		this.creationTime = DateUtility.getCurTimeInstance();
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Calendar getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Calendar expireTime) {
		this.expireTime = expireTime;
	}

	public CouponStatus getStatus() {
		return status;
	}

	public void setStatus(CouponStatus status) {
		this.status = status;
	}

	public CouponOrigin getOrigin() {
		return origin;
	}


	public void setOrigin(CouponOrigin origin) {
		this.origin = origin;
	}


	public int getOriginalAmount() {
		return originalAmount;
	}


	public Calendar getCreationTime() {
		return creationTime;
	}

	public Coupon deepCopy() throws IOException, ClassNotFoundException{
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        
        oos.writeObject(this);
        oos.close();
        
        final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final Coupon clone = (Coupon) ois.readObject();
        
        return clone;
	}
	
	public JSONObject toJSON() throws ValidationException{
		JSONObject jsonSearchRepresentation = new JSONObject();
		try{
			jsonSearchRepresentation.put("couponId", this.couponId);			
			jsonSearchRepresentation.put("userId", this.userId);
			jsonSearchRepresentation.put("amount", this.amount);
			jsonSearchRepresentation.put("originalAmount", this.originalAmount);
			jsonSearchRepresentation.put("creationTime", DateUtility.castToAPIFormat(this.creationTime));	
			jsonSearchRepresentation.put("expireTime", DateUtility.castToAPIFormat(this.expireTime));
			jsonSearchRepresentation.put("status", this.status.code);
			jsonSearchRepresentation.put("couponOrigin", this.origin.code);
			
		} catch (JSONException e) {
			DebugLog.d(e);
			throw new ValidationException("信息数据格式转换失败");
		}
		return jsonSearchRepresentation;
	}
	
	public boolean equals(Coupon c){
		if (c == null){
			return false;
		}
		return this.couponId == c.getCouponId() && 				
				this.userId == c.getUserId() && 
				this.status.code == c.getStatus().code &&
				this.origin.code == c.getOrigin().code &&
				this.creationTime.getTime().toString().equals(c.getCreationTime().getTime().toString()) &&
				this.expireTime.getTime().toString().equals(c.getExpireTime().getTime().toString()) && 
				this.amount == c.getAmount() && this.originalAmount == c.getOriginalAmount();
	}
	
}
