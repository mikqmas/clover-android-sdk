package com.clover.common2.payments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.clover.sdk.v1.Intents;
import com.clover.sdk.v3.apps.AppTracking;
import com.clover.sdk.v3.payments.Payment;
import com.clover.sdk.v3.payments.ServiceChargeAmount;
import com.clover.sdk.v3.payments.TaxableAmountRate;
import com.clover.sdk.v3.payments.VaultedCard;

import java.util.ArrayList;
import java.util.List;

public class PayIntent implements Parcelable {

  public enum TransactionType {
    PAYMENT(Intents.TRANSACTION_TYPE_PAYMENT),
    CREDIT(Intents.TRANSACTION_TYPE_CREDIT),
    AUTH(Intents.TRANSACTION_TYPE_AUTH),
    DATA(Intents.TRANSACTION_TYPE_CARD_DATA),
    BALANCE_INQUIRY(Intents.TRANSACTION_TYPE_BALANCE_INQUIRY),
    PAYMENT_REVERSAL(Intents.TRANSACTION_TYPE_MANUAL_REVERSAL_PAYMENT),
    CREDIT_REVERSAL(Intents.TRANSACTION_TYPE_MANUAL_REVERSAL_REFUND),
    ;

    public final String intentValue;

    TransactionType(String intentValue) {
      this.intentValue = intentValue;
    }

    public static TransactionType getForValue(String intentValue) {
      for (TransactionType type : TransactionType.values()) {
        if (type.intentValue.equals(intentValue)) {
          return type;
        }
      }
      return null;
    }
  }

  public static class Builder {
    private String action;
    private Long amount;
    private Long tippableAmount;
    private Long tipAmount;
    private Long taxAmount;
    private String orderId;
    private String paymentId;
    private String employeeId;
    private TransactionType transactionType;
    private ArrayList<TaxableAmountRate> taxableAmountRates;
    private ServiceChargeAmount serviceChargeAmount;
    private boolean isDisableCashBack;
    private boolean isTesting;
    private int cardEntryMethods;
    private String voiceAuthCode;
    private String postalCode;
    private String streetAddress;
    private boolean isCardNotPresent;
    private String cardDataMessage;
    private boolean remotePrint;
    private String transactionNo;
    private boolean isForceSwipePinEntry;
    private boolean disableRestartTransactionWhenFailed;
    // Can be set to the properly formatted uuid for a payment (
    private String externalPaymentId;
    private VaultedCard vaultedCard;
    private Boolean allowOfflinePayment;
    private Boolean approveOfflinePaymentWithoutPrompt;
    private Boolean requiresRemoteConfirmation;
    private AppTracking applicationTracking;

    public Builder intent(Intent intent) {
      action = intent.getAction();

      if (intent.hasExtra(Intents.EXTRA_AMOUNT)) {
        amount = intent.getLongExtra(Intents.EXTRA_AMOUNT, 0L);
      }
      if (intent.hasExtra(Intents.EXTRA_TIPPABLE_AMOUNT)) {
        tippableAmount = intent.getLongExtra(Intents.EXTRA_TIPPABLE_AMOUNT, -1L);
      }

      transactionType = TransactionType.getForValue(intent.getStringExtra(Intents.EXTRA_TRANSACTION_TYPE));
      if (transactionType == null) {
        transactionType = TransactionType.PAYMENT;
      }

      if (intent.getExtras().containsKey(Intents.EXTRA_ORDER_ID)) {
        orderId = intent.getStringExtra(Intents.EXTRA_ORDER_ID);
      }
      if(intent.getExtras().containsKey(Intents.EXTRA_PAYMENT_ID)){
        paymentId = intent.getStringExtra(Intents.EXTRA_PAYMENT_ID);
      }

      if (intent.getExtras().containsKey(Intents.EXTRA_EMPLOYEE_ID)) {
        employeeId = intent.getStringExtra(Intents.EXTRA_EMPLOYEE_ID);
      }
      tipAmount = intent.hasExtra(Intents.EXTRA_TIP_AMOUNT) ? intent.getLongExtra(Intents.EXTRA_TIP_AMOUNT, 0L) : null;
      taxAmount = intent.getLongExtra(Intents.EXTRA_TAX_AMOUNT, 0L);

      taxableAmountRates = intent.getParcelableArrayListExtra(Intents.EXTRA_TAXABLE_AMOUNTS);
      serviceChargeAmount = intent.getParcelableExtra(Intents.EXTRA_SERVICE_CHARGE_AMOUNT);
      isDisableCashBack = intent.getBooleanExtra(Intents.EXTRA_DISABLE_CASHBACK, false);
      isTesting = intent.getBooleanExtra(Intents.EXTRA_IS_TESTING, false);
      cardEntryMethods = intent.getIntExtra(Intents.EXTRA_CARD_ENTRY_METHODS, Intents.CARD_ENTRY_METHOD_ALL);
      voiceAuthCode = intent.getStringExtra(Intents.EXTRA_VOICE_AUTH_CODE);
      postalCode = intent.getStringExtra(Intents.EXTRA_AVS_POSTAL_CODE);
      streetAddress = intent.getStringExtra(Intents.EXTRA_AVS_STREET_ADDRESS);
      isCardNotPresent = intent.getBooleanExtra(Intents.EXTRA_CARD_NOT_PRESENT, false);
      cardDataMessage = intent.getStringExtra(Intents.EXTRA_CARD_DATA_MESSAGE);
      remotePrint = intent.getBooleanExtra(Intents.EXTRA_REMOTE_PRINT, false);
      transactionNo = intent.getStringExtra(Intents.EXTRA_TRANSACTION_NO);
      isForceSwipePinEntry = intent.getBooleanExtra(Intents.EXTRA_FORCE_SWIPE_PIN_ENTRY, false);
      disableRestartTransactionWhenFailed = intent.getBooleanExtra(
          Intents.EXTRA_DISABLE_RESTART_TRANSACTION_WHEN_FAILED, false);
      externalPaymentId = intent.getStringExtra(
          Intents.EXTRA_EXTERNAL_PAYMENT_ID);
      vaultedCard = intent.getParcelableExtra(Intents.EXTRA_VAULTED_CARD);

      if(intent.getExtras().containsKey(Intents.EXTRA_ALLOW_OFFLINE_ACCEPTANCE)) {
        allowOfflinePayment = (Boolean) intent.getExtras().get(Intents.EXTRA_ALLOW_OFFLINE_ACCEPTANCE);
      }
      if(intent.getExtras().containsKey(Intents.EXTRA_OFFLINE_NO_PROMPT)) {
        approveOfflinePaymentWithoutPrompt = (Boolean) intent.getExtras().get(Intents.EXTRA_OFFLINE_NO_PROMPT);
      }
      if(intent.getExtras().containsKey(Intents.EXTRA_REQUIRES_REMOTE_CONFIRMATION)) {
        requiresRemoteConfirmation = (Boolean) intent.getExtras().get(Intents.EXTRA_REQUIRES_REMOTE_CONFIRMATION);
      }
      if(intent.getExtras().containsKey(Intents.EXTRA_APP_TRACKING_ID)) {
        applicationTracking = intent.getParcelableExtra(Intents.EXTRA_APP_TRACKING_ID);
      }

      return this;
    }

    public Builder payment(Payment payment) {
      this.amount = payment.getAmount();
      this.tipAmount = payment.getTipAmount();
      this.taxAmount = payment.getTaxAmount();
      this.employeeId = payment.getEmployee().getId();
      this.transactionNo = payment.hasCardTransaction() ? payment.getCardTransaction().getTransactionNo() : null;

      return this;
    }

    public Builder payIntent(PayIntent payIntent) {
      this.action = payIntent.action;
      this.amount = payIntent.amount;
      this.tippableAmount = payIntent.tippableAmount;
      this.tipAmount = payIntent.tipAmount;
      this.taxAmount = payIntent.taxAmount;
      this.orderId = payIntent.orderId;
      this.paymentId = payIntent.paymentId;
      this.employeeId = payIntent.employeeId;
      this.transactionType = payIntent.transactionType;
      this.taxableAmountRates = payIntent.taxableAmountRateList;
      this.serviceChargeAmount = payIntent.serviceChargeAmount;
      this.isDisableCashBack = payIntent.isDisableCashBack;
      this.isTesting = payIntent.isTesting;
      this.cardEntryMethods = payIntent.cardEntryMethods;
      this.voiceAuthCode = payIntent.voiceAuthCode;
      this.postalCode = payIntent.postalCode;
      this.streetAddress = payIntent.streetAddress;
      this.isCardNotPresent = payIntent.isCardNotPresent;
      this.cardDataMessage = payIntent.cardDataMessage;
      this.remotePrint = payIntent.remotePrint;
      this.transactionNo = payIntent.transactionNo;
      this.isForceSwipePinEntry = payIntent.isForceSwipePinEntry;
      this.disableRestartTransactionWhenFailed = payIntent.disableRestartTransactionWhenFailed;
      this.externalPaymentId = payIntent.externalPaymentId;
      this.vaultedCard = payIntent.vaultedCard;
      this.allowOfflinePayment = payIntent.allowOfflinePayment;
      this.approveOfflinePaymentWithoutPrompt = payIntent.approveOfflinePaymentWithoutPrompt;
      this.requiresRemoteConfirmation = payIntent.requiresRemoteConfirmation;
      this.applicationTracking = payIntent.applicationTracking;

      return this;
    }

    public Builder action(String action) {
      this.action = action;
      return this;
    }

    public Builder amount(long amount) {
      this.amount = amount;
      return this;
    }

    public Builder tippableAmount(long tippableAmount) {
      this.tippableAmount = tippableAmount;
      return this;
    }

    public Builder taxAmount(long taxAmount) {
      this.taxAmount = taxAmount;
      return this;
    }

    public Builder employeeId(String employeeId) {
      this.employeeId = employeeId;
      return this;
    }

    public Builder tipAmount(long tipAmount) {
      this.tipAmount = tipAmount;
      return this;
    }

    public Builder transactionType(TransactionType transactionType) {
      this.transactionType = transactionType;
      return this;
    }

    public Builder cardEntryMethods(int cardEntryMethods) {
      this.cardEntryMethods = cardEntryMethods;
      return this;
    }

    public Builder cardDataMessage(String cardDataMessage) {
      this.cardDataMessage = cardDataMessage;
      return this;
    }

    public Builder taxableAmountRates(List<TaxableAmountRate> taxableAmountRates) {
      this.taxableAmountRates = new ArrayList<TaxableAmountRate>(taxableAmountRates);
      return this;
    }

    public Builder serviceChargeAmount(ServiceChargeAmount serviceChargeAmount) {
      this.serviceChargeAmount = serviceChargeAmount;
      return this;
    }

    public Builder orderId(String orderId) {
      this.orderId = orderId;
      return this;
    }

    public Builder paymentId(String paymentId){
      this.paymentId = paymentId;
      return this;
    }

    public Builder remotePrint(boolean remotePrint) {
      this.remotePrint = remotePrint;
      return this;
    }

    public Builder disableCashback(boolean disableCashBack) {
      this.isDisableCashBack = disableCashBack;
      return this;
    }

    public Builder transactionNo(String transactionNo) {
      this.transactionNo = transactionNo;
      return this;
    }

    public Builder forceSwipePinEntry(boolean isForceSwipePinEntry) {
      this.isForceSwipePinEntry = isForceSwipePinEntry;
      return this;
    }

    public Builder disableRestartTransactionWhenFailed(boolean disableRestartTransactionWhenFailed) {
      this.disableRestartTransactionWhenFailed = disableRestartTransactionWhenFailed;
      return this;
    }

    public Builder externalPaymentId(String externalPaymentId) {
      this.externalPaymentId = externalPaymentId;
      return this;
    }

    public Builder vaultedCard(VaultedCard vaultedCard) {
      this.vaultedCard = vaultedCard;
      return this;
    }

    public Builder allowOfflinePayment(Boolean allowOfflinePayment) {
      this.allowOfflinePayment = allowOfflinePayment;
      return this;
    }

    public Builder approveOfflinePaymentWithoutPrompt(Boolean approveOfflinePaymentWithoutPrompt) {
      this.approveOfflinePaymentWithoutPrompt = approveOfflinePaymentWithoutPrompt;
      return this;
    }

    public Builder requiresRemoteConfirmation(Boolean requiresRemoteConfirmation) {
      this.requiresRemoteConfirmation = requiresRemoteConfirmation;
      return this;
    }

    public Builder applicationTracking(AppTracking applicationTracking) {
      this.applicationTracking = applicationTracking;
      return this;
    }


    public Builder cardNotPresent(boolean cardNotPresent) {
      this.isCardNotPresent = cardNotPresent;
      return this;
    }

    public PayIntent build() {
      return new PayIntent(action, amount, tippableAmount, tipAmount, taxAmount, orderId, paymentId, employeeId,
          transactionType, taxableAmountRates, serviceChargeAmount, isDisableCashBack, isTesting, cardEntryMethods,
          voiceAuthCode, postalCode, streetAddress, isCardNotPresent, cardDataMessage, remotePrint, transactionNo,
          isForceSwipePinEntry, disableRestartTransactionWhenFailed, externalPaymentId, vaultedCard, allowOfflinePayment,
          approveOfflinePaymentWithoutPrompt, requiresRemoteConfirmation, applicationTracking);
    }
  }

  public final String action;
  public final Long amount;
  public final Long tippableAmount;
  public final Long tipAmount;
  public final Long taxAmount;
  public final String orderId;
  public final String paymentId;
  public final String employeeId;
  public final TransactionType transactionType;
  public final ArrayList<TaxableAmountRate> taxableAmountRateList;
  public final ServiceChargeAmount serviceChargeAmount;
  public final boolean isDisableCashBack;
  public final boolean isTesting;
  public final int cardEntryMethods;
  public final String voiceAuthCode;
  public final String postalCode;
  public final String streetAddress;
  public final boolean isCardNotPresent;
  public final String cardDataMessage;
  public final boolean remotePrint;
  public final String transactionNo;
  public final boolean isForceSwipePinEntry;
  public final boolean disableRestartTransactionWhenFailed;
  public final String externalPaymentId;
  public final VaultedCard vaultedCard;
  public final Boolean allowOfflinePayment;
  public final Boolean approveOfflinePaymentWithoutPrompt;
  public final Boolean requiresRemoteConfirmation;
  public final AppTracking applicationTracking;

  private PayIntent(String action, Long amount, Long tippableAmount,
                    Long tipAmount, Long taxAmount, String orderId, String paymentId, String employeeId,
                    TransactionType transactionType, ArrayList<TaxableAmountRate> taxableAmountRateList,
                    ServiceChargeAmount serviceChargeAmount, boolean isDisableCashBack, boolean isTesting,
                    int cardEntryMethods, String voiceAuthCode, String postalCode, String streetAddress,
                    boolean isCardNotPresent, String cardDataMessage, boolean remotePrint, String transactionNo,
                    boolean isForceSwipePinEntry, boolean disableRestartTransactionWhenFailed, String externalPaymentId,
                    VaultedCard vaultedCard, Boolean allowOfflinePayment, Boolean approveOfflinePaymentWithoutPrompt,
                    Boolean requiresRemoteConfirmation, AppTracking applicationTracking) {

    this.action = action;
    this.amount = amount;
    this.tippableAmount = tippableAmount;
    this.tipAmount = tipAmount;
    this.taxAmount = taxAmount;
    this.orderId = orderId;
    this.paymentId = paymentId;
    this.employeeId = employeeId;
    this.transactionType = transactionType;
    this.taxableAmountRateList = taxableAmountRateList;
    this.serviceChargeAmount = serviceChargeAmount;
    this.isDisableCashBack = isDisableCashBack;
    this.isTesting = isTesting;
    this.cardEntryMethods = cardEntryMethods;
    this.voiceAuthCode = voiceAuthCode;
    this.postalCode = postalCode;
    this.streetAddress = streetAddress;
    this.isCardNotPresent = isCardNotPresent;
    this.cardDataMessage = cardDataMessage;
    this.remotePrint = remotePrint;
    this.transactionNo = transactionNo;
    this.isForceSwipePinEntry = isForceSwipePinEntry;
    this.disableRestartTransactionWhenFailed = disableRestartTransactionWhenFailed;
    this.externalPaymentId = externalPaymentId;
    this.vaultedCard = vaultedCard;
    this.allowOfflinePayment = allowOfflinePayment;
    this.approveOfflinePaymentWithoutPrompt = approveOfflinePaymentWithoutPrompt;
    this.requiresRemoteConfirmation = requiresRemoteConfirmation;
    this.applicationTracking = applicationTracking;
  }

  public String toLogMessage() {
    StringBuilder s = new StringBuilder();
    s
        .append("Transaction details:\n")
        .append("\tOrder ID: ").append(orderId).append("\n")
        .append("\tPayment ID: ").append(paymentId).append("\n")
        .append("\tEmployee ID: ").append(employeeId).append("\n")
        .append("\tAmount: ").append(amount).append("\n")
        .append("\tTransaction type: ").append(transactionType).append("\n");

    return s.toString();
  }

  public void addTo(Intent intent) {
    intent.setExtrasClassLoader(PayIntent.class.getClassLoader());

    if (action != null) {
      intent.setAction(action);
    }
    if (amount != null) {
      intent.putExtra(Intents.EXTRA_AMOUNT, amount);
    }
    if (tippableAmount != null) {
      intent.putExtra(Intents.EXTRA_TIPPABLE_AMOUNT, tippableAmount);
    }
    if (tipAmount != null) {
      intent.putExtra(Intents.EXTRA_TIP_AMOUNT, tipAmount);
    }
    if (taxAmount != null) {
      intent.putExtra(Intents.EXTRA_TAX_AMOUNT, taxAmount);
    }
    if (orderId != null) {
      intent.putExtra(Intents.EXTRA_ORDER_ID, orderId);
    }
    if (paymentId != null) {
      intent.putExtra(Intents.EXTRA_PAYMENT_ID, paymentId);
    }
    if (employeeId != null) {
      intent.putExtra(Intents.EXTRA_EMPLOYEE_ID, employeeId);
    }
    if (transactionType != null) {
      intent.putExtra(Intents.EXTRA_TRANSACTION_TYPE, transactionType.intentValue);
    }
    if (serviceChargeAmount != null) {
      intent.putExtra(Intents.EXTRA_SERVICE_CHARGE_AMOUNT, serviceChargeAmount);
    }
    if (taxableAmountRateList != null) {
      intent.putParcelableArrayListExtra(Intents.EXTRA_TAXABLE_AMOUNTS, taxableAmountRateList);
    }
    intent.putExtra(Intents.EXTRA_DISABLE_CASHBACK, isDisableCashBack);
    intent.putExtra(Intents.EXTRA_IS_TESTING, isTesting);
    intent.putExtra(Intents.EXTRA_CARD_ENTRY_METHODS, cardEntryMethods);
    if (voiceAuthCode != null) {
      intent.putExtra(Intents.EXTRA_VOICE_AUTH_CODE, voiceAuthCode);
    }
    intent.putExtra(Intents.EXTRA_CARD_NOT_PRESENT, isCardNotPresent);
    if (streetAddress != null) {
      intent.putExtra(Intents.EXTRA_AVS_STREET_ADDRESS, streetAddress);
    }
    if (postalCode != null) {
      intent.putExtra(Intents.EXTRA_AVS_POSTAL_CODE, postalCode);
    }
    if (cardDataMessage != null) {
      intent.putExtra(Intents.EXTRA_CARD_DATA_MESSAGE, cardDataMessage);
    }
    intent.putExtra(Intents.EXTRA_REMOTE_PRINT, remotePrint);
    if (transactionNo != null) {
      intent.putExtra(Intents.EXTRA_TRANSACTION_NO, transactionNo);
    }
    intent.putExtra(Intents.EXTRA_FORCE_SWIPE_PIN_ENTRY, isForceSwipePinEntry);

    intent.putExtra(Intents.EXTRA_DISABLE_RESTART_TRANSACTION_WHEN_FAILED, disableRestartTransactionWhenFailed);

    intent.putExtra(Intents.EXTRA_EXTERNAL_PAYMENT_ID, externalPaymentId);

    intent.putExtra(Intents.EXTRA_VAULTED_CARD, vaultedCard);

    if (allowOfflinePayment != null) {
      intent.putExtra(Intents.EXTRA_ALLOW_OFFLINE_ACCEPTANCE, allowOfflinePayment);
    }
    if (approveOfflinePaymentWithoutPrompt != null) {
      intent.putExtra(Intents.EXTRA_OFFLINE_NO_PROMPT, approveOfflinePaymentWithoutPrompt);
    }
    if (requiresRemoteConfirmation != null) {
      intent.putExtra(Intents.EXTRA_REQUIRES_REMOTE_CONFIRMATION, requiresRemoteConfirmation);
    }
    if (applicationTracking != null) {
      intent.putExtra(Intents.EXTRA_APP_TRACKING_ID, applicationTracking);
    }
  }

  @Override
  public String toString() {
    return "PayIntent{" +
        "action='" + action + '\'' +
        ", amount=" + amount +
        ", tippableAmount=" + tippableAmount +
        ", tipAmount=" + tipAmount +
        ", taxAmount=" + taxAmount +
        ", orderId='" + orderId + '\'' +
        ", paymentId='" + paymentId + '\'' +
        ", employeeId='" + employeeId + '\'' +
        ", transactionType=" + transactionType +
        ", taxableAmountRateList=" + taxableAmountRateList +
        ", serviceChargeAmount=" + serviceChargeAmount +
        ", isDisableCashBack=" + isDisableCashBack +
        ", isTesting=" + isTesting +
        ", cardEntryMethods=" + cardEntryMethods +
        ", voiceAuthCode='" + voiceAuthCode + '\'' +
        ", postalCode='" + postalCode + '\'' +
        ", streetAddress='" + streetAddress + '\'' +
        ", isCardNotPresent=" + isCardNotPresent +
        ", cardDataMessage='" + cardDataMessage + '\'' +
        ", remotePrint=" + remotePrint +
        ", transactionNo='" + transactionNo + '\'' +
        ", isForceSwipePinEntry=" + isForceSwipePinEntry +
        ", disableRestartTransactionWhenFailed=" + disableRestartTransactionWhenFailed +
        ", externalPaymentId='" + externalPaymentId + '\'' +
        ", vaultedCard=" + vaultedCard + '\'' +
        ", allowOfflinePayment=" + allowOfflinePayment + '\'' +
        ", approveOfflinePaymentWithoutPrompt=" + approveOfflinePaymentWithoutPrompt +
        ", requiresRemoteConfirmation=" + requiresRemoteConfirmation +
        ", applicationTracking=" + applicationTracking +
        '}';
  }

  // Parcelable interface impl
  private static final String BUNDLE_KEY_ACTION = "a";

  @Override
  public int describeContents() {
    return 0;
  }

  public void writeToParcel(Parcel out, int flags) {
    // create a bundle
    final Bundle bundle = new Bundle(PayIntent.class.getClassLoader());

    bundle.putString(BUNDLE_KEY_ACTION, action);

    if (amount != null) {
      bundle.putLong(Intents.EXTRA_AMOUNT, amount);
    }
    if (tippableAmount != null) {
      bundle.putLong(Intents.EXTRA_TIPPABLE_AMOUNT, tippableAmount);
    }
    if (tipAmount != null) {
      bundle.putLong(Intents.EXTRA_TIP_AMOUNT, tipAmount);
    }
    if (taxAmount != null) {
      bundle.putLong(Intents.EXTRA_TAX_AMOUNT, taxAmount);
    }
    if (orderId != null) {
      bundle.putString(Intents.EXTRA_ORDER_ID, orderId);
    }
    if (paymentId != null) {
      bundle.putString(Intents.EXTRA_PAYMENT_ID, paymentId);
    }
    if (employeeId != null) {
      bundle.putString(Intents.EXTRA_EMPLOYEE_ID, employeeId);
    }
    if (transactionType != null) {
      bundle.putString(Intents.EXTRA_TRANSACTION_TYPE, transactionType.intentValue);
    }
    if (serviceChargeAmount != null) {
      bundle.putParcelable(Intents.EXTRA_SERVICE_CHARGE_AMOUNT, serviceChargeAmount);
    }
    if (taxableAmountRateList != null) {
      bundle.putParcelableArrayList(Intents.EXTRA_TAXABLE_AMOUNTS, taxableAmountRateList);
    }
    bundle.putBoolean(Intents.EXTRA_DISABLE_CASHBACK, isDisableCashBack);
    bundle.putBoolean(Intents.EXTRA_IS_TESTING, isTesting);
    bundle.putInt(Intents.EXTRA_CARD_ENTRY_METHODS, cardEntryMethods);
    if (voiceAuthCode != null) {
      bundle.putString(Intents.EXTRA_VOICE_AUTH_CODE, voiceAuthCode);
    }
    bundle.putBoolean(Intents.EXTRA_CARD_NOT_PRESENT, isCardNotPresent);
    if (streetAddress != null) {
      bundle.putString(Intents.EXTRA_AVS_STREET_ADDRESS, streetAddress);
    }
    if (postalCode != null) {
      bundle.putString(Intents.EXTRA_AVS_POSTAL_CODE, postalCode);
    }
    if (cardDataMessage != null) {
      bundle.putString(Intents.EXTRA_CARD_DATA_MESSAGE, cardDataMessage);
    }
    bundle.putBoolean(Intents.EXTRA_REMOTE_PRINT, remotePrint);
    if (transactionNo != null) {
      bundle.putString(Intents.EXTRA_TRANSACTION_NO, transactionNo);
    }
    bundle.putBoolean(Intents.EXTRA_FORCE_SWIPE_PIN_ENTRY, isForceSwipePinEntry);

    bundle.putBoolean(Intents.EXTRA_DISABLE_RESTART_TRANSACTION_WHEN_FAILED, disableRestartTransactionWhenFailed);

    if (externalPaymentId != null) {
      bundle.putString(Intents.EXTRA_EXTERNAL_PAYMENT_ID, externalPaymentId);
    }

    if (allowOfflinePayment != null) {
      bundle.putBoolean(Intents.EXTRA_ALLOW_OFFLINE_ACCEPTANCE, allowOfflinePayment);
    }

    if (approveOfflinePaymentWithoutPrompt != null) {
      bundle.putBoolean(Intents.EXTRA_OFFLINE_NO_PROMPT, approveOfflinePaymentWithoutPrompt);
    }

    if (requiresRemoteConfirmation != null) {
      bundle.putBoolean(Intents.EXTRA_REQUIRES_REMOTE_CONFIRMATION, requiresRemoteConfirmation);
    }

    if (applicationTracking != null) {
      bundle.putParcelable(Intents.EXTRA_APP_TRACKING_ID, applicationTracking);
    }

    // write out
    out.writeBundle(bundle);
  }

  public static final Parcelable.Creator<PayIntent> CREATOR = new Parcelable.Creator<PayIntent>() {
    public PayIntent createFromParcel(Parcel in) {
      final Bundle bundle = in.readBundle(PayIntent.class.getClassLoader());

      final PayIntent.Builder builder = new PayIntent.Builder();

      builder.action(bundle.getString(BUNDLE_KEY_ACTION));

      if (bundle.containsKey(Intents.EXTRA_AMOUNT)) {
        builder.amount( bundle.getLong(Intents.EXTRA_AMOUNT) );
      }
      if (bundle.containsKey(Intents.EXTRA_TIPPABLE_AMOUNT)) {
        builder.tippableAmount(bundle.getLong(Intents.EXTRA_TIPPABLE_AMOUNT));
      }
      if (bundle.containsKey(Intents.EXTRA_TIP_AMOUNT)) {
        builder.tipAmount(bundle.getLong(Intents.EXTRA_TIP_AMOUNT));
      }
      if (bundle.containsKey(Intents.EXTRA_TAX_AMOUNT)) {
        builder.taxAmount(bundle.getLong(Intents.EXTRA_TAX_AMOUNT));
      }
      if (bundle.containsKey(Intents.EXTRA_ORDER_ID)) {
        builder.orderId(bundle.getString(Intents.EXTRA_ORDER_ID));
      }
      if(bundle.containsKey(Intents.EXTRA_PAYMENT_ID)){
        builder.paymentId(bundle.getString(Intents.EXTRA_PAYMENT_ID));
      }
      if (bundle.containsKey(Intents.EXTRA_EMPLOYEE_ID)) {
        builder.employeeId(bundle.getString(Intents.EXTRA_EMPLOYEE_ID));
      }
      if (bundle.containsKey(Intents.EXTRA_TRANSACTION_TYPE)) {
        builder.transactionType(TransactionType.getForValue(bundle.getString(Intents.EXTRA_TRANSACTION_TYPE)));
      }
      if (bundle.containsKey(Intents.EXTRA_SERVICE_CHARGE_AMOUNT)) {
        final Parcelable serviceChargeAmount = bundle.getParcelable(Intents.EXTRA_SERVICE_CHARGE_AMOUNT);
        if (serviceChargeAmount instanceof ServiceChargeAmount) {
          builder.serviceChargeAmount((ServiceChargeAmount)serviceChargeAmount);
        }
      }
      if (bundle.containsKey(Intents.EXTRA_TAXABLE_AMOUNTS)) {
        final ArrayList<TaxableAmountRate> taxableAmounts = bundle.getParcelableArrayList(Intents.EXTRA_TAXABLE_AMOUNTS);
        if (taxableAmounts != null && taxableAmounts.size() > 0) {
          builder.taxableAmountRates(taxableAmounts);
        }
      }
      builder.disableCashback(bundle.getBoolean(Intents.EXTRA_DISABLE_CASHBACK));
      builder.isTesting = bundle.getBoolean(Intents.EXTRA_IS_TESTING);
      builder.cardEntryMethods(bundle.getInt(Intents.EXTRA_CARD_ENTRY_METHODS));
      if (bundle.containsKey(Intents.EXTRA_VOICE_AUTH_CODE)) {
        builder.voiceAuthCode = bundle.getString(Intents.EXTRA_VOICE_AUTH_CODE);
      }
      builder.isCardNotPresent = bundle.getBoolean(Intents.EXTRA_CARD_NOT_PRESENT);
      if (bundle.containsKey(Intents.EXTRA_AVS_STREET_ADDRESS)) {
        builder.streetAddress = bundle.getString(Intents.EXTRA_AVS_STREET_ADDRESS);
      }
      if (bundle.containsKey(Intents.EXTRA_AVS_POSTAL_CODE)) {
        builder.postalCode = bundle.getString(Intents.EXTRA_AVS_POSTAL_CODE);
      }
      if (bundle.containsKey(Intents.EXTRA_CARD_DATA_MESSAGE)) {
        builder.cardDataMessage(bundle.getString(Intents.EXTRA_CARD_DATA_MESSAGE));
      }
      builder.remotePrint = bundle.getBoolean(Intents.EXTRA_REMOTE_PRINT);
      if (bundle.containsKey(Intents.EXTRA_TRANSACTION_NO)) {
        builder.transactionNo(bundle.getString(Intents.EXTRA_TRANSACTION_NO));
      }
      builder.isForceSwipePinEntry = bundle.getBoolean(Intents.EXTRA_FORCE_SWIPE_PIN_ENTRY);
      builder.disableRestartTransactionWhenFailed(
          bundle.getBoolean(Intents.EXTRA_DISABLE_RESTART_TRANSACTION_WHEN_FAILED));
      if (bundle.containsKey(Intents.EXTRA_EXTERNAL_PAYMENT_ID)) {
        builder.externalPaymentId(bundle.getString(Intents.EXTRA_EXTERNAL_PAYMENT_ID));
      }

      if (bundle.containsKey(Intents.EXTRA_VAULTED_CARD)) {
        Parcelable parcelable = bundle.getParcelable(Intents.EXTRA_VAULTED_CARD);
        if (parcelable instanceof VaultedCard) {
          builder.vaultedCard((VaultedCard)parcelable);
        }
      }

      if (bundle.containsKey(Intents.EXTRA_ALLOW_OFFLINE_ACCEPTANCE)) {
        builder.allowOfflinePayment((Boolean)bundle.get(Intents.EXTRA_ALLOW_OFFLINE_ACCEPTANCE));
      }
      if (bundle.containsKey(Intents.EXTRA_OFFLINE_NO_PROMPT)) {
        builder.approveOfflinePaymentWithoutPrompt((Boolean)bundle.get(Intents.EXTRA_OFFLINE_NO_PROMPT));
      }
      if (bundle.containsKey(Intents.EXTRA_REQUIRES_REMOTE_CONFIRMATION)) {
        builder.requiresRemoteConfirmation((Boolean)bundle.get(Intents.EXTRA_REQUIRES_REMOTE_CONFIRMATION));
      }
      if (bundle.containsKey(Intents.EXTRA_APP_TRACKING_ID)) {
        final Parcelable applicationTracking = bundle.getParcelable(Intents.EXTRA_APP_TRACKING_ID);
        if (applicationTracking instanceof AppTracking) {
          builder.applicationTracking((AppTracking)applicationTracking);
        }
      }
      // build
      return builder.build();
    }

    public PayIntent[] newArray(int size) {
      return new PayIntent[size];
    }
  };
}
