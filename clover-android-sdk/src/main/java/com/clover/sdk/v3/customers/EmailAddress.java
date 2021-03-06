/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */


/*
 * Copyright (C) 2016 Clover Network, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clover.sdk.v3.customers;

import com.clover.sdk.GenericClient;

/**
 * This is an auto-generated Clover data object.
 * <p>
 * <h3>Fields</h3>
 * <ul>
 * <li>{@link #getId id}</li>
 * <li>{@link #getEmailAddress emailAddress}</li>
 * <li>{@link #getVerifiedTime verifiedTime}</li>
 * </ul>
 */
@SuppressWarnings("all")
public final class EmailAddress implements android.os.Parcelable, com.clover.sdk.v3.Validator, com.clover.sdk.JSONifiable {

  public java.lang.String getId() {
    return genClient.cacheGet(CacheKey.id);
  }

  public java.lang.String getEmailAddress() {
    return genClient.cacheGet(CacheKey.emailAddress);
  }

  public java.lang.Long getVerifiedTime() {
    return genClient.cacheGet(CacheKey.verifiedTime);
  }



  private enum CacheKey implements com.clover.sdk.ValueExtractorEnum<EmailAddress> {
    id {
      @Override
      public Object extractValue(EmailAddress instance) {
        return instance.genClient.extractOther("id", java.lang.String.class);
      }
    },
    emailAddress {
      @Override
      public Object extractValue(EmailAddress instance) {
        return instance.genClient.extractOther("emailAddress", java.lang.String.class);
      }
    },
    verifiedTime {
      @Override
      public Object extractValue(EmailAddress instance) {
        return instance.genClient.extractOther("verifiedTime", java.lang.Long.class);
      }
    },
    ;
  }

  private GenericClient<EmailAddress> genClient = new GenericClient<EmailAddress>(this);

  /**
   * Constructs a new empty instance.
   */
  public EmailAddress() { }

  /**
   * Constructs a new instance from the given JSON String.
   */
  public EmailAddress(String json) throws IllegalArgumentException {
    try {
      genClient.setJsonObject(new org.json.JSONObject(json));
    } catch (org.json.JSONException e) {
      throw new IllegalArgumentException("invalid json", e);
    }
  }

  /**
   * Construct a new instance backed by the given JSONObject, the parameter is not copied so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public EmailAddress(org.json.JSONObject jsonObject) {
    genClient.setJsonObject(jsonObject);
  }

  /**
   * Constructs a new instance that is a deep copy of the source instance. It does not copy the bundle or changelog.
   */
  public EmailAddress(EmailAddress src) {
    if (src.genClient.getJsonObject() != null) {
      genClient.setJsonObject(com.clover.sdk.v3.JsonHelper.deepCopy(src.genClient.getJSONObject()));
    }
  }

  /**
   * Returns the internal JSONObject backing this instance, the return value is not a copy so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public org.json.JSONObject getJSONObject() {
    return genClient.getJSONObject();
  }

  @Override
  public void validate() {

    genClient.validateNull(getEmailAddress(), "emailAddress");
  }

  /** Checks whether the 'id' field is set and is not null */
  public boolean isNotNullId() {
    return genClient.cacheValueIsNotNull(CacheKey.id);
  }

  /** Checks whether the 'emailAddress' field is set and is not null */
  public boolean isNotNullEmailAddress() {
    return genClient.cacheValueIsNotNull(CacheKey.emailAddress);
  }

  /** Checks whether the 'verifiedTime' field is set and is not null */
  public boolean isNotNullVerifiedTime() {
    return genClient.cacheValueIsNotNull(CacheKey.verifiedTime);
  }


  /** Checks whether the 'id' field has been set, however the value could be null */
  public boolean hasId() {
    return genClient.cacheHasKey(CacheKey.id);
  }

  /** Checks whether the 'emailAddress' field has been set, however the value could be null */
  public boolean hasEmailAddress() {
    return genClient.cacheHasKey(CacheKey.emailAddress);
  }

  /** Checks whether the 'verifiedTime' field has been set, however the value could be null */
  public boolean hasVerifiedTime() {
    return genClient.cacheHasKey(CacheKey.verifiedTime);
  }


  /**
   * Sets the field 'id'.
   */
  public EmailAddress setId(java.lang.String id) {
    return genClient.setOther(id, CacheKey.id);
  }

  /**
   * Sets the field 'emailAddress'.
   */
  public EmailAddress setEmailAddress(java.lang.String emailAddress) {
    return genClient.setOther(emailAddress, CacheKey.emailAddress);
  }

  /**
   * Sets the field 'verifiedTime'.
   */
  public EmailAddress setVerifiedTime(java.lang.Long verifiedTime) {
    return genClient.setOther(verifiedTime, CacheKey.verifiedTime);
  }


  /** Clears the 'id' field, the 'has' method for this field will now return false */
  public void clearId() {
    genClient.clear(CacheKey.id);
  }
  /** Clears the 'emailAddress' field, the 'has' method for this field will now return false */
  public void clearEmailAddress() {
    genClient.clear(CacheKey.emailAddress);
  }
  /** Clears the 'verifiedTime' field, the 'has' method for this field will now return false */
  public void clearVerifiedTime() {
    genClient.clear(CacheKey.verifiedTime);
  }

  /**
   * Returns true if this instance has any changes.
   */
  public boolean containsChanges() {
    return genClient.containsChanges();
  }

  /**
   * Reset the log of changes made to this instance, calling copyChanges() after this would return an empty instance.
   */
  public void resetChangeLog() {
    genClient.resetChangeLog();
  }

  /**
   * Create a copy of this instance that contains only fields that were set after the constructor was called.
   */
  public EmailAddress copyChanges() {
    EmailAddress copy = new EmailAddress();
    copy.mergeChanges(this);
    copy.resetChangeLog();
    return copy;
  }

  /**
   * Copy all the changed fields from the given source to this instance.
   */
  public void mergeChanges(EmailAddress src) {
    if (src.genClient.getChangeLog() != null) {
      genClient.mergeChanges(new EmailAddress(src).getJSONObject(), src.genClient);
    }
  }

  /**
   * Gets a Bundle which can be used to get and set data attached to this instance. The attached Bundle will be
   * parcelled but not jsonified.
   */
  public android.os.Bundle getBundle() {
    return genClient.getBundle();
  }

  @Override
  public String toString() {
    return genClient.toString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(android.os.Parcel dest, int flags) {
    genClient.writeToParcel(dest, flags);
  }

  public static final android.os.Parcelable.Creator<EmailAddress> CREATOR = new android.os.Parcelable.Creator<EmailAddress>() {
    @Override
    public EmailAddress createFromParcel(android.os.Parcel in) {
      EmailAddress instance = new EmailAddress(com.clover.sdk.v3.JsonParcelHelper.ObjectWrapper.CREATOR.createFromParcel(in).unwrap());
      instance.genClient.setBundle(in.readBundle(getClass().getClassLoader()));
      instance.genClient.setChangeLog(in.readBundle());
      return instance;
    }

    @Override
    public EmailAddress[] newArray(int size) {
      return new EmailAddress[size];
    }
  };

  public static final com.clover.sdk.JSONifiable.Creator<EmailAddress> JSON_CREATOR = new com.clover.sdk.JSONifiable.Creator<EmailAddress>() {
    @Override
    public EmailAddress create(org.json.JSONObject jsonObject) {
      return new EmailAddress(jsonObject);
    }
  };

  public interface Constraints {

    public static final boolean ID_IS_REQUIRED = false;

    public static final boolean EMAILADDRESS_IS_REQUIRED = true;

    public static final boolean VERIFIEDTIME_IS_REQUIRED = false;

  }

}
