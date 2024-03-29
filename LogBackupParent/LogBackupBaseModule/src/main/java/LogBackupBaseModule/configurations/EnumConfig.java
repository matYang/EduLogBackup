package LogBackupBaseModule.configurations;

public final class EnumConfig {

	public static enum AccountStatus{
        activated(0),deactivated(1),deleted(2);
        public int code;
        AccountStatus(int code){
            this.code = code;
        }
        private final static AccountStatus[] map = AccountStatus.values();
        public static AccountStatus fromInt(int n){
            return map[n];
        }
    }
	
	public static enum CreditStatus{
        usable(0),expired(1),used(2);
        public int code;
        CreditStatus(int code){
            this.code = code;
        }
        private final static CreditStatus[] map = CreditStatus.values();
        public static CreditStatus fromInt(int n){
            return map[n];
        }
    }
	
	public static enum CouponStatus{
        usable(0),expired(1),used(2), inactive(3);
        public int code;
        CouponStatus(int code){
            this.code = code;
        }
        private final static CouponStatus[] map = CouponStatus.values();
        public static CouponStatus fromInt(int n){
            return map[n];
        }
    }
	
	
	public static enum CouponOrigin{
        registration(0), invitation(1), admin(2);
        public int code;
        CouponOrigin(int code){
            this.code = code;
        }
        private final static CouponOrigin[] map = CouponOrigin.values();
        public static CouponOrigin fromInt(int n){
            return map[n];
        }
    }
	
	public static enum TransactionType{
        cashback(0),deposit(1),withdraw(2),invitation(3);
        public int code;
        TransactionType(int code){
            this.code = code;
        }
        private final static TransactionType[] map = TransactionType.values();
        public static TransactionType fromInt(int n){
            return map[n];
        }
    }
	
	public static enum BookingStatus{
        awaiting(0),confirmed(1),cancelled(2),failed(3),delivered(4), noShow(5),late(6), registered(7), paid(8), noPay(9), started(10), refunded(11), succeeded(12), consolidated(13);
        public int code;
        BookingStatus(int code){
            this.code = code;
        }
        private final static BookingStatus[] map = BookingStatus.values();
        public static BookingStatus fromInt(int n){
            return map[n];
        }
    }
	
	public static enum BookingType{
		offline(0),online(1);
        public int code;
        BookingType(int code){
            this.code = code;
        }
        private final static BookingType[] map = BookingType.values();
        public static BookingType fromInt(int n){
            return map[n];
        }
	}
		
	public static enum ServiceFeeStatus{
		shouldCharge(0),hasCharged(1),refundCharge(2), noCharge(3), consolidated(4), naive(5);
        public int code;
        ServiceFeeStatus(int code){
            this.code = code;
        }
        private final static ServiceFeeStatus[] map = ServiceFeeStatus.values();
        public static ServiceFeeStatus fromInt(int n){
            return map[n];
        }
	}

	public static enum CommissionStatus{
		shouldCharge(0),hasCharged(1),refundCharge(2), noCharge(3), consolidated(4), naive(5);
        public int code;
        CommissionStatus(int code){
            this.code = code;
        }
        private final static CommissionStatus[] map = CommissionStatus.values();
        public static CommissionStatus fromInt(int n){
            return map[n];
        }
	}
	
	public static enum Privilege{
        root(0),mamagement(1),routine(2);
        public int code;
        Privilege(int code){
            this.code = code;
        }
        private final static Privilege[] map = Privilege.values();
        public static Privilege fromInt(int n){
            return map[n];
        }
    }
	
	public static enum SMSEvent{
		user_cellVerification(0), user_changePassword(1), user_forgetPassword(2), partner_forgetPassword(3), partner_changePassword(4), user_bookingConfirmed(5), user_bookingFailed(6), user_invitee(7), user_inviter(8), user_inviterConsolidation(9);
		public int code;
		SMSEvent(int code){
			this.code = code;
		}
		private final static SMSEvent[] map = SMSEvent.values();
		public static SMSEvent fromInt(int n){
			return map[n];
		}
	}
	
	public static enum CourseStatus{
		openEnroll(0),deactivated(1),consolidated(2);
        public int code;
        CourseStatus(int code){
            this.code = code;
        }
        private final static CourseStatus[] map = CourseStatus.values();
        public static CourseStatus fromInt(int n){
            return map[n];
        }
    }	
	
	public static enum PartnerQualification{
		verified(0),unverified(1);
        public int code;
        PartnerQualification(int code){
            this.code = code;
        }
        private final static PartnerQualification[] map = PartnerQualification.values();
        public static PartnerQualification fromInt(int n){
            return map[n];
        }
    }	

}
