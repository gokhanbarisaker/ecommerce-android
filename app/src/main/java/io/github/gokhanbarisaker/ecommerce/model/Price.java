package io.github.gokhanbarisaker.ecommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price implements Parcelable {

    // == Variables ================================================================================

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel in) {
            return new Price(in);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
    @SerializedName("current")
    @Expose
    private Double current;
    @SerializedName("original")
    @Expose
    private Double original;


    // == Constructors & factory methods ===========================================================
    @SerializedName("currency")
    @Expose
    private String currency;

    public Price() {

    }


    // == Accessors ================================================================================

    protected Price(Parcel in) {
        current = in.readByte() == 0x00 ? null : in.readDouble();
        original = in.readByte() == 0x00 ? null : in.readDouble();
        currency = in.readString();
    }

    public static Price random() {
        Price price = new Price();

        price.currency = "TL";
        price.original = 50.;
        price.current = 25.;

        return price;
    }

    /**
     * @return The current
     */
    public Double getCurrent() {
        return current;
    }

    /**
     * @param current The current
     */
    public void setCurrent(Double current) {
        this.current = current;
    }

    /**
     * @return The original
     */
    public Double getOriginal() {
        return original;
    }

    /**
     * @param original The original
     */
    public void setOriginal(Double original) {
        this.original = original;
    }

    /**
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (current == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(current);
        }
        if (original == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(original);
        }
        dest.writeString(currency);
    }
}
