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

package com.clover.sdk.v3.inventory;

import com.clover.sdk.GenericClient;

/**
 * This is an auto-generated Clover data object.
 * <p>
 * <h3>Fields</h3>
 * <ul>
 * <li>{@link #getId id}</li>
 * <li>{@link #getName name}</li>
 * <li>{@link #getRate rate}</li>
 * <li>{@link #getIsDefault isDefault}</li>
 * <li>{@link #getItems items}</li>
 * </ul>
 * <p>
 * @see com.clover.sdk.v3.inventory.IInventoryService
 */
@SuppressWarnings("all")
public final class TaxRate implements android.os.Parcelable, com.clover.sdk.v3.Validator, com.clover.sdk.JSONifiable {

  public java.lang.String getId() {
    return genClient.cacheGet(CacheKey.id);
  }

  public java.lang.String getName() {
    return genClient.cacheGet(CacheKey.name);
  }

  public java.lang.Long getRate() {
    return genClient.cacheGet(CacheKey.rate);
  }

  public java.lang.Boolean getIsDefault() {
    return genClient.cacheGet(CacheKey.isDefault);
  }

  /**
   * Items associated with this tax rate
   */
  public java.util.List<com.clover.sdk.v3.base.Reference> getItems() {
    return genClient.cacheGet(CacheKey.items);
  }



  private enum CacheKey implements com.clover.sdk.ValueExtractorEnum<TaxRate> {
    id {
      @Override
      public Object extractValue(TaxRate instance) {
        return instance.genClient.extractOther("id", java.lang.String.class);
      }
    },
    name {
      @Override
      public Object extractValue(TaxRate instance) {
        return instance.genClient.extractOther("name", java.lang.String.class);
      }
    },
    rate {
      @Override
      public Object extractValue(TaxRate instance) {
        return instance.genClient.extractOther("rate", java.lang.Long.class);
      }
    },
    isDefault {
      @Override
      public Object extractValue(TaxRate instance) {
        return instance.genClient.extractOther("isDefault", java.lang.Boolean.class);
      }
    },
    items {
      @Override
      public Object extractValue(TaxRate instance) {
        return instance.genClient.extractListRecord("items", com.clover.sdk.v3.base.Reference.JSON_CREATOR);
      }
    },
    ;
  }

  private GenericClient<TaxRate> genClient = new GenericClient<TaxRate>(this);

  /**
   * Constructs a new empty instance.
   */
  public TaxRate() { }

  /**
   * Constructs a new instance from the given JSON String.
   */
  public TaxRate(String json) throws IllegalArgumentException {
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
  public TaxRate(org.json.JSONObject jsonObject) {
    genClient.setJsonObject(jsonObject);
  }

  /**
   * Constructs a new instance that is a deep copy of the source instance. It does not copy the bundle or changelog.
   */
  public TaxRate(TaxRate src) {
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
    genClient.validateLength(getId(), 13);

    genClient.validateNull(getName(), "name");
    genClient.validateLength(getName(), 127);

    genClient.validateNull(getRate(), "rate");
    if (getRate() != null && ( getRate() < 0 || getRate() > 100000000)) throw new IllegalArgumentException("Invalid value for 'getRate()'");
  }

  /** Checks whether the 'id' field is set and is not null */
  public boolean isNotNullId() {
    return genClient.cacheValueIsNotNull(CacheKey.id);
  }

  /** Checks whether the 'name' field is set and is not null */
  public boolean isNotNullName() {
    return genClient.cacheValueIsNotNull(CacheKey.name);
  }

  /** Checks whether the 'rate' field is set and is not null */
  public boolean isNotNullRate() {
    return genClient.cacheValueIsNotNull(CacheKey.rate);
  }

  /** Checks whether the 'isDefault' field is set and is not null */
  public boolean isNotNullIsDefault() {
    return genClient.cacheValueIsNotNull(CacheKey.isDefault);
  }

  /** Checks whether the 'items' field is set and is not null */
  public boolean isNotNullItems() {
    return genClient.cacheValueIsNotNull(CacheKey.items);
  }

  /** Checks whether the 'items' field is set and is not null and is not empty */
  public boolean isNotEmptyItems() { return isNotNullItems() && !getItems().isEmpty(); }


  /** Checks whether the 'id' field has been set, however the value could be null */
  public boolean hasId() {
    return genClient.cacheHasKey(CacheKey.id);
  }

  /** Checks whether the 'name' field has been set, however the value could be null */
  public boolean hasName() {
    return genClient.cacheHasKey(CacheKey.name);
  }

  /** Checks whether the 'rate' field has been set, however the value could be null */
  public boolean hasRate() {
    return genClient.cacheHasKey(CacheKey.rate);
  }

  /** Checks whether the 'isDefault' field has been set, however the value could be null */
  public boolean hasIsDefault() {
    return genClient.cacheHasKey(CacheKey.isDefault);
  }

  /** Checks whether the 'items' field has been set, however the value could be null */
  public boolean hasItems() {
    return genClient.cacheHasKey(CacheKey.items);
  }


  /**
   * Sets the field 'id'.
   */
  public TaxRate setId(java.lang.String id) {
    return genClient.setOther(id, CacheKey.id);
  }

  /**
   * Sets the field 'name'.
   */
  public TaxRate setName(java.lang.String name) {
    return genClient.setOther(name, CacheKey.name);
  }

  /**
   * Sets the field 'rate'.
   */
  public TaxRate setRate(java.lang.Long rate) {
    return genClient.setOther(rate, CacheKey.rate);
  }

  /**
   * Sets the field 'isDefault'.
   */
  public TaxRate setIsDefault(java.lang.Boolean isDefault) {
    return genClient.setOther(isDefault, CacheKey.isDefault);
  }

  /**
   * Sets the field 'items'.
   *
   * Nulls in the given List are skipped. List parameter is copied, so it will not reflect any changes, but objects inside it will.
   */
  public TaxRate setItems(java.util.List<com.clover.sdk.v3.base.Reference> items) {
    return genClient.setArrayRecord(items, CacheKey.items);
  }


  /** Clears the 'id' field, the 'has' method for this field will now return false */
  public void clearId() {
    genClient.clear(CacheKey.id);
  }
  /** Clears the 'name' field, the 'has' method for this field will now return false */
  public void clearName() {
    genClient.clear(CacheKey.name);
  }
  /** Clears the 'rate' field, the 'has' method for this field will now return false */
  public void clearRate() {
    genClient.clear(CacheKey.rate);
  }
  /** Clears the 'isDefault' field, the 'has' method for this field will now return false */
  public void clearIsDefault() {
    genClient.clear(CacheKey.isDefault);
  }
  /** Clears the 'items' field, the 'has' method for this field will now return false */
  public void clearItems() {
    genClient.clear(CacheKey.items);
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
  public TaxRate copyChanges() {
    TaxRate copy = new TaxRate();
    copy.mergeChanges(this);
    copy.resetChangeLog();
    return copy;
  }

  /**
   * Copy all the changed fields from the given source to this instance.
   */
  public void mergeChanges(TaxRate src) {
    if (src.genClient.getChangeLog() != null) {
      genClient.mergeChanges(new TaxRate(src).getJSONObject(), src.genClient);
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

  public static final android.os.Parcelable.Creator<TaxRate> CREATOR = new android.os.Parcelable.Creator<TaxRate>() {
    @Override
    public TaxRate createFromParcel(android.os.Parcel in) {
      TaxRate instance = new TaxRate(com.clover.sdk.v3.JsonParcelHelper.ObjectWrapper.CREATOR.createFromParcel(in).unwrap());
      instance.genClient.setBundle(in.readBundle(getClass().getClassLoader()));
      instance.genClient.setChangeLog(in.readBundle());
      return instance;
    }

    @Override
    public TaxRate[] newArray(int size) {
      return new TaxRate[size];
    }
  };

  public static final com.clover.sdk.JSONifiable.Creator<TaxRate> JSON_CREATOR = new com.clover.sdk.JSONifiable.Creator<TaxRate>() {
    @Override
    public TaxRate create(org.json.JSONObject jsonObject) {
      return new TaxRate(jsonObject);
    }
  };

  public interface Constraints {

    public static final boolean ID_IS_REQUIRED = false;
    public static final long ID_MAX_LEN = 13;

    public static final boolean NAME_IS_REQUIRED = true;
    public static final long NAME_MAX_LEN = 127;

    public static final boolean RATE_IS_REQUIRED = true;
    public static final long RATE_MIN = 0;
    public static final long RATE_MAX = 100000000;

    public static final boolean ISDEFAULT_IS_REQUIRED = false;

    public static final boolean ITEMS_IS_REQUIRED = false;

  }

}
